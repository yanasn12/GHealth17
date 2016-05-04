package GUI;


import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import controllers.DiscountTableControl;
import controllers.LoginCont;
import entities.DiscountTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * GUI window to Show the current discount table - if the user has the authority
 * (user type 8,0) another panel will be built with the pending discount table to approve
 * pressing push will tabk the numbers in the showing discount table and push it to pendin to change discount table
 * after pressing apply the pennding discount changes will be move to the discount table thas changing the discounts.
 */


public class ModifyDiscountTableScreen extends JFrame{
	
	private static final long serialVersionUID = -7235227200108554746L;
	private JFrame mainframe;
	private JFrame prevWindow;
	private JButton btnApply = new JButton("Apply");
	private	JButton btnReturn = new JButton("Return");
	private	JButton btnPushChanges = new JButton("Push");
	private JLabel lblPendingChanges = new JLabel("Pending Changes:");
	private JTextField txtCash;
	private JTextField txtMonthlySimple;
	private JTextField txtMonthlyMultiple;
	private JTextField txtMonthlyFull;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	
	public ModifyDiscountTableScreen(JFrame pw) {
		this.setPrevWindow(pw);
		this.setMainframe(this);
		setTitle("Modify Discounts");
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 194, 402, 46);
		getContentPane().add(panel);
		panel.setLayout(null);
		//return was pressed
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevWindow.setVisible(true);
				dispose();
			}
		});
		

		btnReturn.setBounds(294, 11, 89, 24);
		panel.add(btnReturn);
		//push was pressed
		btnPushChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double[] discountTableChanges = new double[4];
				try{
				discountTableChanges[0]=Double.parseDouble(textField.getText());
				discountTableChanges[1]=Double.parseDouble(textField_1.getText());
				discountTableChanges[2]=Double.parseDouble(textField_2.getText());
				discountTableChanges[3]=Double.parseDouble(textField_3.getText());
				}
				catch(NumberFormatException err)
				{
					JOptionPane.showMessageDialog(mainframe, "Strings can not be discounts", "Number Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(discountTableChanges[0] <0.0 || discountTableChanges[0]>1.0 ||discountTableChanges[1] <0.0 || discountTableChanges[1]>1.0 ||discountTableChanges[2] <0.0 || discountTableChanges[2]>1.0 ||discountTableChanges[3] <0.0 || discountTableChanges[3]>1.0 )
				{
					JOptionPane.showMessageDialog(mainframe, "Discounts must be between 0-1", "Number Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				DiscountTable dt = null;
				dt = DiscountTableControl.getDiscountTableById(0);
				double[] dtc = DiscountTableControl.getChanged();
				for(int i=0;i<4;i++)
					dtc[i]=discountTableChanges[i];
				
				dt.setDiscountTableChanges(dtc);
				DiscountTableControl.updateDiscountTable(0);
				DiscountTableControl.evictDiscountTable(0);
				
				if(LoginCont.getCurrUser().getUserTypeID()==8 || LoginCont.getCurrUser().getUserTypeID()==0)
				{
				textField_5.setText(""+discountTableChanges[0]);
				textField_7.setText(""+discountTableChanges[1]);
				textField_9.setText(""+discountTableChanges[2]);
				textField_11.setText(""+discountTableChanges[3]);
				}
			}
		});
		
		btnPushChanges.setBounds(10, 12, 108, 23);
		panel.add(btnPushChanges);
		
		double[] discounts = DiscountTableControl.getAllDiscounts();
		double[] pendingChanges = DiscountTableControl.getChanged();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 32, 402, 59);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtCash = new JTextField();
		txtCash.setText("Global Discount");
		txtCash.setEnabled(false);
		txtCash.setEditable(false);
		txtCash.setHorizontalAlignment(SwingConstants.CENTER);
		txtCash.setBounds(10, 11, 96, 20);
		panel_1.add(txtCash);
		txtCash.setColumns(10);
		
		txtMonthlySimple = new JTextField();
		txtMonthlySimple.setEnabled(false);
		txtMonthlySimple.setEditable(false);
		txtMonthlySimple.setText("Monthly Simple");
		txtMonthlySimple.setHorizontalAlignment(SwingConstants.CENTER);
		txtMonthlySimple.setBounds(104, 11, 96, 20);
		panel_1.add(txtMonthlySimple);
		txtMonthlySimple.setColumns(10);
		
		txtMonthlyMultiple = new JTextField();
		txtMonthlyMultiple.setHorizontalAlignment(SwingConstants.CENTER);
		txtMonthlyMultiple.setText("Monthly Multiple");
		txtMonthlyMultiple.setEnabled(false);
		txtMonthlyMultiple.setEditable(false);
		txtMonthlyMultiple.setBounds(199, 11, 96, 20);
		panel_1.add(txtMonthlyMultiple);
		txtMonthlyMultiple.setColumns(10);
		
		txtMonthlyFull = new JTextField();
		txtMonthlyFull.setText("Monthly Full");
		txtMonthlyFull.setHorizontalAlignment(SwingConstants.CENTER);
		txtMonthlyFull.setEditable(false);
		txtMonthlyFull.setEnabled(false);
		txtMonthlyFull.setBounds(294, 11, 96, 20);
		panel_1.add(txtMonthlyFull);
		txtMonthlyFull.setColumns(10);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(10, 29, 96, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(104, 29, 96, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(199, 29, 96, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(294, 29, 96, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField.setText(""+discounts[0]);
		textField_1.setText(""+discounts[1]);
		textField_2.setText(""+discounts[2]);
		textField_3.setText(""+discounts[3]);
		
		if(LoginCont.getCurrUser().getUserTypeID()==8 || LoginCont.getCurrUser().getUserTypeID()==0)
		{
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(10, 124, 402, 59);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setText("Global Discount");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(10, 11, 96, 20);
		panel_2.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(10, 29, 96, 20);
		panel_2.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("Monthly Simple");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(104, 11, 96, 20);
		panel_2.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(104, 29, 96, 20);
		panel_2.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText("Monthly Multiple");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEnabled(false);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(199, 11, 96, 20);
		panel_2.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(199, 29, 96, 20);
		panel_2.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("Monthly Full");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setEnabled(false);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(294, 11, 96, 20);
		panel_2.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(294, 29, 96, 20);
		panel_2.add(textField_11);
			
		lblPendingChanges.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPendingChanges.setBounds(10, 102, 125, 22);
		getContentPane().add(lblPendingChanges);
		
		textField_5.setText(""+pendingChanges[0]);
		textField_7.setText(""+pendingChanges[1]);
		textField_9.setText(""+pendingChanges[2]);
		textField_11.setText(""+pendingChanges[3]);
		
		btnApply.setBounds(128, 12, 108, 23);
		
		panel.add(btnApply);
		}
		//apply was pressed
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DiscountTable dt = null;
				dt = DiscountTableControl.getDiscountTableById(0);
				dt.approveDiscountAt(0);
				dt.approveDiscountAt(1);
				dt.approveDiscountAt(2);
				dt.approveDiscountAt(3);
				DiscountTableControl.updateDiscountTable(0);
				DiscountTableControl.evictDiscountTable(0);
				
				textField.setText(textField_5.getText());
				textField_1.setText(textField_7.getText());
				textField_2.setText(textField_9.getText());
				textField_3.setText(textField_11.getText());
			}
		});
		

		JLabel lblCurrentDiscountTable = new JLabel("Current Discount Table:");
		lblCurrentDiscountTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCurrentDiscountTable.setBounds(10, 6, 152, 27);
		getContentPane().add(lblCurrentDiscountTable);
		
        
		this.setSize(435,295);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		
		
		
	}

	public JFrame getMainframe() {
		return mainframe;
	}

	public void setMainframe(JFrame mainframe) {
		this.mainframe = mainframe;
	}

	public JFrame getPrevWindow() {
		return prevWindow;
	}

	public void setPrevWindow(JFrame prevWindow) {
		this.prevWindow = prevWindow;
	}
}
