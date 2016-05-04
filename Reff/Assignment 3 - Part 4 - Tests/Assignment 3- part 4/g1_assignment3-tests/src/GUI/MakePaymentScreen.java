package GUI;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import entities.*;
import controllers.*;

public class MakePaymentScreen extends JFrame{

	private static final long serialVersionUID = -3901311539851597857L;
	
	private JFrame PrevWindow;
	private JTextField priceTextBox;
	private JTextField invoiceTextField;
	private JComboBox paymentComboBox;
	Invoice toBePaid;
	List<PaymentInfo> paymentMethods;
	
	/**
	 * Creates a new MakePaymentScreen
	 * @param PW the window that will serve as the previous open window once this window is closed.
	 * @param tbp the Invoice to be paid
	 * @param mode the work mode of the Screen, possible values MakePaymentScreen.INVOICE and MakePaymentScreen.HOUSE_FUEL
	 */
	public MakePaymentScreen(JFrame PW, Invoice tbp)
	{
		this.toBePaid = tbp;
		
		InvoiceControl.setActive(toBePaid);
		InvoiceControl.calcActiveSumDeep();
		paymentMethods = toBePaid.getCustomer().getPaymentInfos();
		
		getContentPane().setBackground(new Color(46, 139, 87));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(60, 179, 113));
		panel.setBounds(10, 11, 404, 187);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblOrigPrice = new JLabel("Price:");
		lblOrigPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrigPrice.setBounds(10, 41, 95, 20);
		panel.add(lblOrigPrice);
		
		priceTextBox = new JTextField();
		priceTextBox.setEditable(false);
		priceTextBox.setBounds(115, 42, 154, 20);
		priceTextBox.setText(""+toBePaid.getTotalPrice());
		panel.add(priceTextBox);
		priceTextBox.setColumns(10);
		
		invoiceTextField = new JTextField();
		invoiceTextField.setEditable(false);
		invoiceTextField.setBounds(115, 14, 153, 20);
		invoiceTextField.setText(""+toBePaid.getInvoiceID());
		panel.add(invoiceTextField);
		invoiceTextField.setColumns(10);
		
		JLabel lblInvoicenumber = new JLabel("InvoiceID:");
		lblInvoicenumber.setBounds(10, 17, 81, 14);
		panel.add(lblInvoicenumber);
		
		paymentComboBox = new JComboBox();
		paymentComboBox.setBounds(118, 93, 276, 20);
		panel.add(paymentComboBox);
		
		for (PaymentInfo item : paymentMethods)
		{
			paymentComboBox.addItem(item.getName());
		}
		
		JLabel lblPaymentmethod = new JLabel("PaymentMethod:");
		lblPaymentmethod.setBounds(10, 96, 95, 14);
		panel.add(lblPaymentmethod);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(60, 179, 113));
		panel_1.setBounds(10, 209, 404, 41);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (PrevWindow.getClass() == InvoiceGUI.class)
					((InvoiceGUI)PrevWindow).activate();
				else
					PrevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(316, 8, 78, 25);
		panel_1.add(btnReturn);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				payWithGUIdata();
			}
		});
		btnPay.setBounds(10, 9, 68, 23);
		panel_1.add(btnPay);
		setTitle("Make Payment");
		setPrevWindow(PW);
		
		this.setSize(440,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	public JFrame getPrevWindow() {
		return PrevWindow;
	}
	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}

	/**
	 * Sends the give invoice with the payment method chosen in the combo box to payment.
	 * If payment is successful updates DB and creates receipt.
	 */
	public void payWithGUIdata ()
	{
		PaymentInfo currentMethod = paymentMethods.get(paymentComboBox.getSelectedIndex());
		InvoiceControl.setActive(toBePaid);
		InvoiceControl.payActive(currentMethod);
		
		JOptionPane.showMessageDialog(null,currentMethod.getPayResult());
		
		if (PrevWindow.getClass() == InvoiceGUI.class)
			((InvoiceGUI)PrevWindow).activate();
		else if (PrevWindow.getClass() == HouseFuelScreen.class)
		{
			((HouseFuelScreen) PrevWindow).loadOrders();
			PrevWindow.setVisible(true);
		}
		dispose();
	}
}