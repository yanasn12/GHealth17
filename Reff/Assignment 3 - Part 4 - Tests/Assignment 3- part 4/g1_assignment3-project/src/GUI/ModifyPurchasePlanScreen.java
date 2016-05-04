package GUI;

import javax.swing.JFrame;
import java.awt.Color;
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

import controllers.PurchasePlanControl;
import entities.PurchasePlan;

import javax.swing.JTextArea;

public class ModifyPurchasePlanScreen extends JFrame{

	private static final long serialVersionUID = -9015584970561800540L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNew = new JButton("New");
	private JButton btnReturn = new JButton("Return");	
	private JButton btnGet = new JButton("Get");
	private JButton btnSet = new JButton("Set");	
	private JFrame PrevWindow;
	private JTextArea textArea = new JTextArea();
	private JFrame mainframe;
	
	public ModifyPurchasePlanScreen(JFrame PPGUI) {
		this.mainframe=mainframe;
		this.PrevWindow=PPGUI;
		setTitle("Modify Purchse Plan");
		getContentPane().setBackground(new Color(218, 112, 214));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(221, 160, 221));
		panel.setBounds(10, 220, 364, 50);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnReturn.setBounds(265, 14, 89, 23);
		panel.add(btnReturn);
		
		btnNew.setBounds(10, 14, 65, 23);
		panel.add(btnNew);
		
		btnGet.setBounds(86, 14, 65, 23);
		panel.add(btnGet);
		
		btnSet.setBounds(161, 14, 65, 23);
		btnSet.setEnabled(false);
		panel.add(btnSet);
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//New Was Pressed
				textField_1.setEnabled(true);
				textField_1.setText("");
				textField_2.setEnabled(true);
				textField_2.setText("");
				textArea.setEnabled(true);
				textArea.setText("");
				
				List<PurchasePlan> pp = new LinkedList<PurchasePlan>();
				pp = PurchasePlanControl.getAllPurchasePlans();
				textField.setText(""+pp.size());
				textField.setEnabled(false);
				btnSet.setEnabled(true);
				
				PurchasePlanControl.loadNewActive();
			}
		});
		
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Set Was Pressed
				PurchasePlanControl.getActive().setPlanName(textField_1.getText());
				//PurchasePlanControl.getActive().setDiscount(Double.parseDouble(textField_2.getText()));
				double dis =Double.parseDouble(textField_2.getText());
				if(dis>1.0 || dis<0.0)
				{
					JOptionPane.showMessageDialog(mainframe, "Discount has to be between 0 and 1", "Number Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				PurchasePlanControl.getActive().setAgreementDetails(textArea.getText());
				if(PurchasePlanControl.getActive().getPurchasePlanID()==-1)
				{
					PurchasePlanControl.getActive().setPurchasePlanID(Integer.parseInt(textField.getText()));
					System.out.println(PurchasePlanControl.getActive().toString());
					PurchasePlanControl.createPurchasePlan(PurchasePlanControl.getActive());
				}
				else{
					PurchasePlanControl.evictPurchasePlan(PurchasePlanControl.getActive().getPurchasePlanID());
				}
				btnSet.setEnabled(false);
				textField_1.setEnabled(false);
				textField_2.setEnabled(false);
				textArea.setEnabled(false);
				textField.setEnabled(true);
				btnGet.setEnabled(true);
				btnNew.setEnabled(true);
			}
		});
		
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get Was Pressed
				String text = textField.getText();
				int id;
				try{
				id = Integer.parseInt (text);
				}
				catch(NumberFormatException ne)
				{
					JOptionPane.showMessageDialog(mainframe, "String cannot be an ID", "Number Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				List<PurchasePlan> ppl = new LinkedList<PurchasePlan>();
				ppl=PurchasePlanControl.getAllPurchasePlans();
				PurchasePlan pt = null;
				
				for(PurchasePlan p : ppl)
					if(p.getPurchasePlanID()==id)
					{
						PurchasePlanControl.setActive(p);
						pt=p;
					}
				if(pt!=null)
				{
				textField_1.setText(PurchasePlanControl.getActive().getPlanName());
				textField_2.setText(""+PurchasePlanControl.getActive().getDiscount());
				textArea.setText(PurchasePlanControl.getActive().getAgreementDetails());
				}
				else
				{
					JOptionPane.showMessageDialog(mainframe, "No Such PurchasePlan ID in the database", "ID Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				textField_1.setEnabled(true);
				textField_2.setEnabled(true);
				textArea.setEnabled(true);
				btnNew.setEnabled(false);
				btnSet.setEnabled(true);
			}
		});
		
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Return Was Pressed
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(221, 160, 221));
		panel_1.setBounds(10, 12, 364, 197);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Purchase Plan ID:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 11, 124, 22);
		panel_1.add(label);
		
		JLabel lblPurchasePlanName = new JLabel("Purchase Plan Name:");
		lblPurchasePlanName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPurchasePlanName.setBounds(10, 43, 141, 22);
		panel_1.add(lblPurchasePlanName);
		
		JLabel lblPurchasePlanDiscount = new JLabel("Purchase Plan Discount:");
		lblPurchasePlanDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPurchasePlanDiscount.setBounds(10, 76, 155, 22);
		panel_1.add(lblPurchasePlanDiscount);
		
		JLabel lblAgreementDetails = new JLabel("Agreement Details:");
		lblAgreementDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAgreementDetails.setBounds(10, 109, 155, 22);
		panel_1.add(lblAgreementDetails);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(170, 79, 141, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(170, 46, 141, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(170, 14, 141, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textArea.setEnabled(false);
		textArea.setBounds(10, 136, 344, 50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel_1.add(textArea);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(400, 320);
	}
}
