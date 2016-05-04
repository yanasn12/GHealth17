package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import controllers.CustomerControl;
import controllers.PurchasePlanControl;
import entities.CarCustomer;
import entities.PurchasePlan;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MatchPlanToCustomerScreen extends JFrame{
	private JFrame PrevWindow;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JFrame mainframe;
	private JButton btnMatch = new JButton("Match");
	private CarCustomer carCustomer = null;
	JButton btnGet = new JButton("Get");
	
	public MatchPlanToCustomerScreen(JFrame PW) {
		this.mainframe=this;
		this.setPrevWindow(PW);
		setTitle("Match Purchase Plan to Customer");
		getContentPane().setBackground(new Color(218, 112, 214));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(221, 160, 221));
		panel.setBounds(10, 10, 297, 67);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerName.setBounds(10, 11, 124, 22);
		panel.add(lblCustomerName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(123, 13, 161, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPurchasePlan = new JLabel("Purchase Plan:");
		lblPurchasePlan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPurchasePlan.setBounds(10, 34, 85, 22);
		panel.add(lblPurchasePlan);
		
		comboBox.setEnabled(false);
		comboBox.setBounds(123, 36, 161, 20);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(221, 160, 221));
		panel_1.setBounds(10, 88, 297, 48);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(186, 11, 89, 23);
		panel_1.add(btnReturn);
		
		//GET WAS PRESSED//
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.removeAllItems();
				
				LinkedList<CarCustomer> cc = new LinkedList<CarCustomer>();
				cc = (LinkedList<CarCustomer>) CustomerControl.getAllCarCustomers();
				CarCustomer c = null;
				for(CarCustomer cl : cc)
				{
					if(cl.getUserName().equals(textField_1.getText()))
					{
							setCarCustomer(cl);
							c = cl;
							break;
					}
				}
				if(c!=null)
				{
					List<PurchasePlan> pp = new LinkedList<PurchasePlan>();
					pp =  PurchasePlanControl.getAllPurchasePlans();
					
					for(PurchasePlan p : pp)
						comboBox.addItem(p.getPlanName());
					comboBox.setEnabled(true);
					btnMatch.setEnabled(true);
				}
				else
				{
					JOptionPane.showMessageDialog(mainframe, "No Such Car Customer In the Database", "Error Getting Customer", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
			}
		});
		btnGet.setBounds(10, 11, 70, 23);
		panel_1.add(btnGet);
		//MATCH WAS PRESSED//
		btnMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnMatch.setEnabled(false);
				comboBox.setEnabled(false);
				List<PurchasePlan> pp = new LinkedList<PurchasePlan>();
				pp =  PurchasePlanControl.getAllPurchasePlans();
				CarCustomer cc = getCarCustomer();
				
				for(PurchasePlan p : pp)
				{
					if(p.getPlanName().equals(comboBox.getSelectedItem()))
						cc.setPurchasePlanID(p.getPurchasePlanID());
				}
//				/System.out.println(c1.carCustoString());
				
				CustomerControl.updateCarCustomer(cc.getUserID());	
				JOptionPane.showMessageDialog(mainframe, "Matching Complete", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnMatch.setEnabled(false);
		btnMatch.setBounds(90, 11, 70, 23);
		panel_1.add(btnMatch);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(331, 186);
	}

	public MatchPlanToCustomerScreen(JFrame PW,String name)
	{
		this(PW);
		this.textField_1.setText(name);
		this.btnGet.doClick();
		this.btnGet.setEnabled(false);
		this.textField_1.setEnabled(false);
	}
	
	
	public JFrame getPrevWindow() {
		return PrevWindow;
	}
	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}

	public void setCarCustomer (CarCustomer cust)
	{
		carCustomer = cust;
	}
	
	public CarCustomer getCarCustomer ()
	{
		return carCustomer;
	}
	
	private static final long serialVersionUID = -4927413237371742221L;
	private JTextField textField_1;
}
