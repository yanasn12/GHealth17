package GUI;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import controllers.CustomerControl;
import controllers.InvoiceControl;
import controllers.PurchasePlanControl;
import controllers.SubscriptionControl;
import entities.Customer;
import entities.Invoice;
import entities.PurchasePlan;
import entities.Subscription;
import javax.swing.JTextArea;


public class ViewSaleScreen extends JFrame{
	private static final long serialVersionUID = 3999547951061420862L;
	private JFrame PrevWindow;
	private JTextField textField;
	private JFrame mainframe;
	private JTextArea textArea = new JTextArea();
	
	public ViewSaleScreen(JFrame PW) {
		this.mainframe=this;
		getContentPane().setBackground(SystemColor.activeCaption);
		setTitle("View Sale");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 330, 437);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblInvoiceid = new JLabel("Invoice ID:");
		lblInvoiceid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInvoiceid.setBounds(10, 11, 105, 21);
		panel.add(lblInvoiceid);
		
		textField = new JTextField();
		textField.setBounds(86, 12, 234, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblInvoiceDescription = new JLabel("Invoice Description:");
		lblInvoiceDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInvoiceDescription.setBounds(10, 43, 161, 14);
		panel.add(lblInvoiceDescription);
		
		textArea.setEditable(false);
		textArea.setBounds(10, 68, 310, 358);
		textArea.setLineWrap(true);

		panel.add(textArea);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 459, 330, 46);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(231, 12, 89, 23);
		panel_1.add(btnReturn);
		
		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Invoice inv = new Invoice();
				inv = InvoiceControl.getInvoiceById(Integer.parseInt(textField.getText()));
				if(inv==null)
				{
					JOptionPane.showMessageDialog(mainframe, "No Such Invoice In the Database", "Error Getting Invoice", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else{
					InvoiceControl.setActive(inv);
					Customer cus = new Customer();
					PurchasePlan pp = new PurchasePlan();
					Subscription sub = new Subscription();
					
					cus = CustomerControl.getCustomerById(InvoiceControl.getActive().getCustomerID());
					pp = PurchasePlanControl.getPurchasePlanById(InvoiceControl.getActive().getPurchasePlanID());
					sub = SubscriptionControl.getSubscriptionById(InvoiceControl.getActive().getSubscriptionID());
					
					InvoiceControl.getActive().setCustomer(cus);
					InvoiceControl.getActive().setPurchasePlan(pp);
					InvoiceControl.getActive().setSubscription(sub);
					
					textArea.setText("Invoice Information:\n"+InvoiceControl.getActive().toString()+"\n\nCustomer Information:\n"+InvoiceControl.getActive().getCustomer().toString()+"\n\nPurchase Plan Info:\n"+InvoiceControl.getActive().getPurchasePlan().toString()+"\n\nSubscription Info:\n"+InvoiceControl.getActive().getSubscription().toString());
				}

			}
		});
		btnGet.setBounds(10, 12, 89, 23);
		panel_1.add(btnGet);
		this.setPrevWindow(PW);
		this.setSize(370, 555);
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
