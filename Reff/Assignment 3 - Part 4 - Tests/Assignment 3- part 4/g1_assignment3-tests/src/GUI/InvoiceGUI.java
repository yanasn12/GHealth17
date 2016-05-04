package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import entities.*;
import controllers.*;

/**
 * This window allows customers and workers to the existing Invoices. Customers can only see their own Invoices while workers can see all Invoices.
 * Customers can pay a selected Invoice from this screen while workers can edit Invoices from this screen. Shows receipt for any chosen invoice if exists.
 */
public class InvoiceGUI extends JFrame {
		
	private static final long serialVersionUID = 8489512090577737477L;
	static final int TABLE_LENGTH = 6;
	public static final int GET_ACTIVE = 0;
	public static final int GET_ALL = 1;
	
	private JFrame prevScreen;
	private JTable invoiceTable;
	private JButton btnEdit;
	
	List<Invoice> currInvoices;
	boolean isEditMode = false;
	
	/**
	 * Creates a new InvoiceGUI.
	 * @param prevScreen the window to which this screen returns.
	 */
	public InvoiceGUI(JFrame prevScreen) {
		
		this.prevScreen = prevScreen;
		
		
		getContentPane().setBackground(new Color(0, 102, 51));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 102));
		panel.setBounds(10, 11, 516, 420);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 24, 485, 351);
		panel.add(scrollPane);
		
		invoiceTable = new JTable();
		scrollPane.setViewportView(invoiceTable);
		invoiceTable.setBackground(SystemColor.control);
		invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		invoiceTable.setAutoscrolls(true);
		
		JButton btnLoadAll = new JButton("Show All");
		btnLoadAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setInvoices(GET_ALL);
			}
		});
		btnLoadAll.setBounds(21, 386, 89, 23);
		panel.add(btnLoadAll);
		
		JButton btnShowActive = new JButton("Show Active");
		btnShowActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setInvoices(GET_ACTIVE);
			}
		});
		btnShowActive.setBounds(120, 386, 113, 23);
		panel.add(btnShowActive);
		invoiceTable.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent arg0) {
				for (int i = 0; i < invoiceTable.getRowCount(); i++)
				{
					if (invoiceTable.getValueAt(i,4) != null)
					{
						if ((int)invoiceTable.getValueAt(i,4) >= OrderStatus.values().length)
							invoiceTable.getModel().setValueAt (currInvoices.get(i).getStatus(),i,4);
						invoiceTable.getModel().setValueAt (OrderStatus.values()[(int)invoiceTable.getValueAt(i,4)].name(),i,5);
					}
				}
			}
		});
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(new Color(0, 153, 102));
		btnPanel.setBounds(10, 442, 516, 40);
		getContentPane().add(btnPanel);
		btnPanel.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(429, 11, 77, 23);
		btnPanel.add(btnReturn);
		
		JButton btnPaySelected = new JButton("Pay Selected");
		btnPaySelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = invoiceTable.getSelectedRow();
				Invoice paying = currInvoices.get(i);
				if (paying.getStatus() == OrderStatus.PAYMENT_PENDING.ordinal() 
				  ||paying.getStatus() == OrderStatus.MONTHLY_ACTIVE.ordinal() )
				{
					new MakePaymentScreen (getThisWindow(),paying);
					setVisible(false);
				}
			}
		});
		btnPaySelected.setBounds(106, 11, 108, 23);
		btnPanel.add(btnPaySelected);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isEditMode = !isEditMode;
				
				if (isEditMode)
					btnEdit.setText("Store");
				else
				{
					int i = 0;
					boolean canAdd;
					for (Invoice item : currInvoices)
					{
						Invoice bac = new Invoice();
						bac.setCustomerID(item.getCustomerID());
						bac.setDueDate(item.getDueDate());
						bac.setStatus(item.getStatus());
						canAdd = false;
						try
						{
							item.setCustomerID((int)invoiceTable.getModel().getValueAt(i,1));
							item.setDueDate((String)invoiceTable.getModel().getValueAt(i,2));
							item.setStatus((int) invoiceTable.getModel().getValueAt(i,4));
							
							if (PeriodicDiscountsControl.checkDate(item.getDueDate()) == false)
							{
								JOptionPane.showMessageDialog(null,"Invalid date format at row " + (i+1)+" !");
								invoiceTable.getModel().setValueAt(bac.getCustomerID(),i,1);
								invoiceTable.getModel().setValueAt(bac.getDueDate(),i,2);
								invoiceTable.getModel().setValueAt(bac.getStatus(),i,4);
								item.setCustomerID(bac.getCustomerID());
								item.setDueDate(bac.getDueDate());
								item.setStatus(bac.getStatus());
								i++;
								continue;
							}
						}
						catch (Exception e)
						{
							JOptionPane.showMessageDialog(null,"Invalid fields at row " + (i+1)+" !");
							invoiceTable.getModel().setValueAt(bac.getCustomerID(),i,1);
							invoiceTable.getModel().setValueAt(bac.getDueDate(),i,2);
							invoiceTable.getModel().setValueAt(bac.getStatus(),i,4);
							item.setCustomerID(bac.getCustomerID());
							item.setDueDate(bac.getDueDate());
							item.setStatus(bac.getStatus());
							i++;
							continue;
						}
						
						for (Customer customer : CustomerControl.getAllCustomers())
							if (customer.getCustomerID() == item.getCustomerID())
							{
								canAdd = true;
								break;
							}
						if (canAdd == false)
						{
							JOptionPane.showMessageDialog(null,"CustomerID doesnt exist at row " + (i+1)+" !");
							invoiceTable.getModel().setValueAt(bac.getCustomerID(),i,1);
							invoiceTable.getModel().setValueAt(bac.getDueDate(),i,2);
							invoiceTable.getModel().setValueAt(bac.getStatus(),i,4);
							item.setCustomerID(bac.getCustomerID());
							item.setDueDate(bac.getDueDate());
							item.setStatus(bac.getStatus());
							i++;
							continue;
						}
						
						InvoiceControl.updateInvoice(item.getInvoiceID());
						i++;
					}
					InvoiceControl.checkInvoiceData();
					btnEdit.setText("Edit");
				}
			}
		});
		btnEdit.setBounds(24, 11, 72, 23);
		btnPanel.add(btnEdit);
		
		JButton btnViewReceipt = new JButton("View Receipt");
		btnViewReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = invoiceTable.getSelectedRow();
				if (i < 0 || i >= currInvoices.size())
					return;
				
				Invoice inv = currInvoices.get(i);			
				Receipt rec = inv.getReceipt();
				
				if (rec != null)
					JOptionPane.showMessageDialog(null,"ReceiptID:    "+rec.getReceiptID() +
													 "\nInvoiceID:    "+rec.getInvoiceID() +
													 "\nPayment Date: "+rec.getPaymentDate()+
													 "\nOverall Sum:  "+inv.getTotalPrice());
				else
					JOptionPane.showMessageDialog(null,"This invoice has no receipts yet !");
			}
		});
		btnViewReceipt.setBounds(224, 11, 108, 23);
		btnPanel.add(btnViewReceipt);
		setTitle("Invoice Info");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(552,530);
		
		if (LoginCont.getCurrUser().getUserType().isCustomer() == false)
		{
			btnPaySelected.setEnabled(false);
		}
		else
		{
			btnEdit.setEnabled(false);
			btnLoadAll.setEnabled(false);
			btnShowActive.setEnabled(false);
		}
		setInvoices (GET_ACTIVE);
	}

	public JFrame getPrevScreen() {
		return prevScreen;
	}

	public void setPrevScreen(JFrame prevScreen) {
		this.prevScreen = prevScreen;
	}
	
	@SuppressWarnings("serial")
	/**
	 * Sets the invoices that will be shown in the invoice table:
	 * 		All for workers and GET_ALL as type,
	 * 		All Active for workers and GET_ACTIVE as type,
	 * 		Only Invoices for this customer for customers.
	 * @param type the selection criteria of invoices (either all or active)
	 */
	public void setInvoices (int type)
	{
		if (LoginCont.getCurrUser().getUserType().isCustomer() == false)
		{
			if (type == GET_ACTIVE)
				currInvoices = InvoiceControl.getAllActiveInvoices();
			else
				currInvoices = InvoiceControl.getAllInvoices();
		}
		else
		{
			currInvoices = ((Customer)LoginCont.getCurrUser()).getInvoices();
		}
		InvoiceControl.checkInvoiceData();
	
		invoiceTable.setModel(new DefaultTableModel(
			new Object[currInvoices.size()][TABLE_LENGTH] ,
			new String[] {
				"InvoiceID", "CustomerID", "Due Date", "Total Price", "Status", "Status text"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Double.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
				public boolean isCellEditable(int row, int column) {
					if (column == 0 || column == 3 || column == 5)
						return false;
					return isEditMode;
				}
		});
		invoiceTable.getColumnModel().getColumn(0).setResizable(false);
		invoiceTable.getColumnModel().getColumn(0).setPreferredWidth(55);
		invoiceTable.getColumnModel().getColumn(1).setResizable(false);
		invoiceTable.getColumnModel().getColumn(1).setPreferredWidth(65);
		invoiceTable.getColumnModel().getColumn(2).setResizable(false);
		invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		invoiceTable.getColumnModel().getColumn(3).setResizable(false);
		invoiceTable.getColumnModel().getColumn(3).setPreferredWidth(70);
		invoiceTable.getColumnModel().getColumn(4).setResizable(false);
		invoiceTable.getColumnModel().getColumn(4).setPreferredWidth(35);
		invoiceTable.getColumnModel().getColumn(5).setResizable(false);
		invoiceTable.getColumnModel().getColumn(5).setPreferredWidth(150);
		
		int i=0;
		for (Invoice item : currInvoices)
		{
			invoiceTable.getModel().setValueAt(item.getInvoiceID(),i,0);
			invoiceTable.getModel().setValueAt(item.getCustomerID(),i,1);
			invoiceTable.getModel().setValueAt(item.getDueDate(),i,2);
			invoiceTable.getModel().setValueAt(item.getTotalPrice(),i,3);
			invoiceTable.getModel().setValueAt(item.getStatus(),i,4);
			invoiceTable.getModel().setValueAt(OrderStatus.values()[item.getStatus()].name(),i,5);
			i++;
		}
	}
	
	/**
	 * Updates the invoice list and sets this window as active
	 */
	public void activate ()
	{
		setInvoices(GET_ALL);
		this.setVisible(true);
	}
	
	public JFrame getThisWindow ()
	{
		return this;
	}
}
