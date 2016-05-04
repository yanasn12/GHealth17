package GUI;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controllers.HouseFuelOrderControl;
import controllers.LoginCont;
import controllers.OrderControl;
import controllers.PeriodicDiscountsControl;
import controllers.TrackingControl;
import entities.HouseFuelInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 * This window allows customers to see the progress of their order. It also allows workers to edit the status of the order if it was not marked as delivered yet.
 */
public class HouseFuelTrackingScreen extends JFrame{
	private static final long serialVersionUID = -7430210244741249976L;
	private JTextField textFieldOrderID;
	private JTextField textFieldDeliveryStatus;
	private JTextField textFieldEstimatedTime;
	private JComboBox comboBoxDeliveryStatus;
	private JButton btnEdit;
	private JButton btnStore;
	
	private HouseFuelScreen prevWindow;
	private HouseFuelInfo activeInfo;
	
	/**
	 * Creates a new HouseFuelTrackingScreen.
	 * @param prev the window to which this screen returns.
	 * @param info the HouseFuelInfo to be shown.
	 */
	public HouseFuelTrackingScreen(HouseFuelScreen prev, HouseFuelInfo info) {
		
		prevWindow = prev;
		activeInfo = info;
		if (activeInfo.getDeliveryStatus() != TrackingControl.DELIVERED)
			activeInfo.setEstimatedTimeLeft((int)PeriodicDiscountsControl.getSecsLeft(activeInfo.getHouseFuelOrder().getDeliveryTime())/3600);
		
		setTitle("House Fuel Tracking Screen");
		getContentPane().setBackground(new Color(0, 102, 153));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 191, 304, 46);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.showOrders ();
				prevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(210, 11, 84, 23);
		panel.add(btnReturn);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldDeliveryStatus.setVisible(false);
				comboBoxDeliveryStatus.setVisible(true);
				
				textFieldEstimatedTime.setEditable(true);
				btnEdit.setEnabled(false);
				btnStore.setEnabled(true);
			}
		});
		btnEdit.setBounds(10, 11, 77, 23);
		panel.add(btnEdit);
		
		btnStore = new JButton("Store");
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int estimatedTime = 0;
				
				try {
					estimatedTime = Integer.parseInt(textFieldEstimatedTime.getText());
					if (estimatedTime < 0)
					{
						JOptionPane.showMessageDialog(null,"Wrong estimated time entered !");
						textFieldEstimatedTime.setText(""+activeInfo.getEstimatedTimeLeft());
						return;
					}
						
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Wrong estimated time entered !");
					textFieldEstimatedTime.setText(""+activeInfo.getEstimatedTimeLeft());
					return;
				}
				
				activeInfo.setDeliveryStatus(comboBoxDeliveryStatus.getSelectedIndex());
				activeInfo.setEstimatedTimeLeft(estimatedTime);
				TrackingControl.updateHouseFuelInfo (activeInfo.getHouseFuelInfoID());
				
				if (activeInfo.getDeliveryStatus() == TrackingControl.DELIVERED)
				{
					activeInfo.setEstimatedTimeLeft(0);
					textFieldEstimatedTime.setText("0");
					activeInfo.getHouseFuelOrder().setDeliveryTime(OrderControl.getCurrDateTime());
					HouseFuelOrderControl.updateHouseFuelOrder(activeInfo.getHouseFuelOrderID());
				}
				else
				{
					String newTime = PeriodicDiscountsControl.addHour(OrderControl.getCurrDateTime(), estimatedTime);
					activeInfo.getHouseFuelOrder().setDeliveryTime(newTime);
					HouseFuelOrderControl.updateHouseFuelOrder(activeInfo.getHouseFuelOrderID());
				}
				
				textFieldDeliveryStatus.setText((String) comboBoxDeliveryStatus.getSelectedItem());
				textFieldDeliveryStatus.setVisible(true);
				comboBoxDeliveryStatus.setVisible(false);
				
				textFieldEstimatedTime.setEditable(false);
				btnEdit.setEnabled(true);
				btnStore.setEnabled(false);
			}
		});
		btnStore.setBounds(97, 11, 77, 23);
		panel.add(btnStore);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(10, 11, 304, 169);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOrderid = new JLabel("OrderID:");
		lblOrderid.setBounds(22, 14, 92, 14);
		panel_1.add(lblOrderid);
		
		textFieldOrderID = new JTextField();
		textFieldOrderID.setEditable(false);
		textFieldOrderID.setBounds(137, 11, 124, 20);
		panel_1.add(textFieldOrderID);
		textFieldOrderID.setColumns(10);
		
		JLabel lblDeliveryStatus = new JLabel("Delivery Status:");
		lblDeliveryStatus.setBounds(22, 54, 92, 14);
		panel_1.add(lblDeliveryStatus);
		
		comboBoxDeliveryStatus = new JComboBox();
		comboBoxDeliveryStatus.setModel(new DefaultComboBoxModel(new String[] {"Created", "Accepted", "Sent", "Delivered"}));
		comboBoxDeliveryStatus.setEditable(true);
		comboBoxDeliveryStatus.setBounds(137, 51, 124, 17);
		panel_1.add(comboBoxDeliveryStatus);
		
		textFieldDeliveryStatus = new JTextField();
		textFieldDeliveryStatus.setEditable(false);
		textFieldDeliveryStatus.setBounds(137, 51, 124, 20);
		panel_1.add(textFieldDeliveryStatus);
		textFieldDeliveryStatus.setColumns(10);
		
		JLabel lblEstimatedTimeLeft = new JLabel("Estimated Time left:");
		lblEstimatedTimeLeft.setBounds(22, 93, 124, 14);
		panel_1.add(lblEstimatedTimeLeft);
		
		textFieldEstimatedTime = new JTextField();
		textFieldEstimatedTime.setEditable(false);
		textFieldEstimatedTime.setBounds(137, 90, 124, 20);
		panel_1.add(textFieldEstimatedTime);
		textFieldEstimatedTime.setColumns(10);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(179, 110, 65, 14);
		panel_1.add(lblHours);
		
		comboBoxDeliveryStatus.setSelectedIndex(activeInfo.getDeliveryStatus());
		textFieldDeliveryStatus.setText((String) comboBoxDeliveryStatus.getSelectedItem());
		textFieldEstimatedTime.setText(""+activeInfo.getEstimatedTimeLeft());
		textFieldOrderID.setText(""+activeInfo.getHouseFuelOrderID());
		
		comboBoxDeliveryStatus.setVisible(false);
		btnStore.setEnabled(false);
		
		if (LoginCont.getCurrUser().getUserType().isCustomer() || activeInfo.getDeliveryStatus() == TrackingControl.DELIVERED)
			btnEdit.setEnabled(false);
		
		setSize (340,286);
		setVisible(true);
	}
}
