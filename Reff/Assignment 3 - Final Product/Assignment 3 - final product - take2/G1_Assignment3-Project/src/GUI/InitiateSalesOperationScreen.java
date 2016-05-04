package GUI;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controllers.PeriodicDiscountsControl;
import entities.PeriodicDiscount;
import entities.PeriodicDiscountTemplate;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class InitiateSalesOperationScreen extends JFrame{

	private static final long serialVersionUID = -4746259694239444365L;
	private JFrame PrevWindow;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnInitiate = new JButton("Initiate");
	private JButton btnGetTemplate = new JButton("Get Template");
	private JFrame mainframe;
	private JTextField txtDd;
	private JTextField txtMm;
	private JTextField txtYyyy;
	private JTextField txtDd_1;
	private JTextField txtMm_1;
	private JTextField txtYyyy_1;
	
	public InitiateSalesOperationScreen(JFrame PW) {
		this.mainframe=this;
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 340, 239);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTemplateId = new JLabel("Template ID:");
		lblTemplateId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTemplateId.setBounds(10, 24, 96, 20);
		panel.add(lblTemplateId);
		
		textField = new JTextField();
		textField.setBounds(126, 25, 165, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiscount.setBounds(10, 55, 96, 14);
		panel.add(lblDiscount);
		
		JLabel lbldiscountTemplate = new JLabel("-Discount Template-");
		lbldiscountTemplate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldiscountTemplate.setBounds(100, 0, 140, 20);
		panel.add(lbldiscountTemplate);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(126, 50, 165, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(126, 75, 165, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(126, 100, 165, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(126, 125, 165, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblStartingTime = new JLabel("Starting Time:");
		lblStartingTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartingTime.setBounds(10, 77, 107, 18);
		panel.add(lblStartingTime);
		
		JLabel lblEndingTime = new JLabel("Ending Time:");
		lblEndingTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndingTime.setBounds(10, 103, 96, 17);
		panel.add(lblEndingTime);
		
		JLabel lblMinimumFuel = new JLabel("Minimum Fuel:");
		lblMinimumFuel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinimumFuel.setBounds(10, 128, 96, 17);
		panel.add(lblMinimumFuel);
		
		JLabel PDiscount = new JLabel("-Periodic Discount-");
		PDiscount.setFont(new Font("Tahoma", Font.BOLD, 13));
		PDiscount.setBounds(100, 156, 140, 20);
		panel.add(PDiscount);
		
		JLabel lblStartingDate = new JLabel("Starting Date:");
		lblStartingDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartingDate.setBounds(10, 181, 80, 17);
		panel.add(lblStartingDate);
		
		JLabel lblEndingDate = new JLabel("Ending Date:");
		lblEndingDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndingDate.setBounds(10, 206, 80, 17);
		panel.add(lblEndingDate);
		
		txtDd = new JTextField();
		txtDd.setText("DD");
		txtDd.setHorizontalAlignment(SwingConstants.CENTER);
		txtDd.setEnabled(false);
		txtDd.setBounds(126, 180, 44, 20);
		panel.add(txtDd);
		txtDd.setColumns(10);
		
		txtMm = new JTextField();
		txtMm.setHorizontalAlignment(SwingConstants.CENTER);
		txtMm.setText("MM");
		txtMm.setEnabled(false);
		txtMm.setBounds(175, 180, 44, 20);
		panel.add(txtMm);
		txtMm.setColumns(10);
		
		txtYyyy = new JTextField();
		txtYyyy.setText("YYYY");
		txtYyyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtYyyy.setEnabled(false);
		txtYyyy.setBounds(225, 180, 66, 20);
		panel.add(txtYyyy);
		txtYyyy.setColumns(10);
		
		txtDd_1 = new JTextField();
		txtDd_1.setText("DD");
		txtDd_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtDd_1.setEnabled(false);
		txtDd_1.setBounds(126, 205, 44, 20);
		panel.add(txtDd_1);
		txtDd_1.setColumns(10);
		
		txtMm_1 = new JTextField();
		txtMm_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtMm_1.setText("MM");
		txtMm_1.setEnabled(false);
		txtMm_1.setBounds(175, 205, 44, 20);
		panel.add(txtMm_1);
		txtMm_1.setColumns(10);
		
		txtYyyy_1 = new JTextField();
		txtYyyy_1.setText("YYYY");
		txtYyyy_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtYyyy_1.setEnabled(false);
		txtYyyy_1.setBounds(225, 205, 66, 20);
		panel.add(txtYyyy_1);
		txtYyyy_1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 261, 340, 51);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(230, 11, 100, 29);
		panel_1.add(btnReturn);
		
		
		//GET TEMPLATE WAS PRESSED//
		btnGetTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PeriodicDiscountTemplate pdt = null;
				pdt = PeriodicDiscountsControl.getPeriodicDiscountTemplateById(Integer.parseInt(textField.getText()));
				if(pdt == null || pdt.getPeriodicDiscountTemplateID()==0)
				{
					JOptionPane.showMessageDialog(mainframe, "No Such Discount Template In the Database", "Error Getting Template", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else
				{
					textField_1.setText(""+pdt.getDiscount());
					textField_2.setText(pdt.getStartTime());
					textField_3.setText(""+pdt.getEndTime());
					textField_4.setText(""+pdt.getMinFuelAmount());
					btnInitiate.setEnabled(true);
					
					txtDd.setEnabled(true);
					txtDd_1.setEnabled(true);
					txtMm.setEnabled(true);
					txtMm_1.setEnabled(true);
					txtYyyy_1.setEnabled(true);
					txtYyyy.setEnabled(true);
					
					PeriodicDiscountsControl.setActive(pdt);
				}
				
			}
		});
		btnGetTemplate.setBounds(10, 11, 100, 29);
		panel_1.add(btnGetTemplate);
		
		//INITIATE WAS PRESSED//
		btnInitiate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInitiate.setEnabled(false);
				int d,d1,m,m1,y,y1;
				
				try{
				d=Integer.parseInt(txtDd.getText());
				d1=Integer.parseInt(txtDd_1.getText());
				
				m=Integer.parseInt(txtMm.getText());
				m1=Integer.parseInt(txtMm_1.getText());
				
				y=Integer.parseInt(txtYyyy.getText());
				y1=Integer.parseInt(txtYyyy_1.getText());
				}
				catch(NumberFormatException er)
				{
					JOptionPane.showMessageDialog(mainframe, "Strings Cannot be a date", "Number Format Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(d<1 || d>31 || d1<1 || d1>31 )
				{
					JOptionPane.showMessageDialog(mainframe, "Invalid Day", "Date Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(m<1 || m>12 || m1<1 || m1>12)
				{
					JOptionPane.showMessageDialog(mainframe, "Invalid Mounth", "Date Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String startDate=null;
				String endDate=null;
				
				startDate=""+y+"-"+m+"-"+d;
				endDate=""+y1+"-"+m1+"-"+d1;
				
				List<PeriodicDiscount> pd = new LinkedList<PeriodicDiscount>();
				pd=PeriodicDiscountsControl.getAllPeriodicDiscounts();
				int maxPDID=-1;
				for(PeriodicDiscount p : pd)
					if(p.getPeriodicDiscountID()>maxPDID)
						maxPDID=p.getPeriodicDiscountID();
				PeriodicDiscount np = new PeriodicDiscount();
				
				np.setPeriodicDiscountTemplateID(PeriodicDiscountsControl.getActive().getPeriodicDiscountTemplateID());
				np.setPeriodicDiscountID(maxPDID+1);
				np.setStartDate(startDate);
				np.setEndDate(endDate);
				
				PeriodicDiscountsControl.createPeriodicDiscount(np);
			}
		});
		
		
		btnInitiate.setEnabled(false);
		btnInitiate.setBounds(120, 12, 100, 26);
		panel_1.add(btnInitiate);
		setTitle("Initiate Sales Operation");
		this.setPrevWindow(PW);
		this.setSize(375, 360);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public JFrame getPrevWindow() {
		return PrevWindow;
	}

	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}
}
