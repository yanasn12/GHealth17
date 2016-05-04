package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import controllers.DiscountFeedbackReportControl;
import controllers.OrderControl;
import entities.DiscountFeedbackReport;
import entities.Order;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DiscountFeedbackGenerationScreen extends JFrame {
		private DiscountFeedbackReport dfrep;
		private JFrame prevScreen;
	public DiscountFeedbackGenerationScreen(JFrame prevScreen, DiscountFeedbackReport dfr) {
		getContentPane().setBackground(new Color(211, 211, 211));
		getContentPane().setLayout(null);
		this.dfrep=dfr;
		this.prevScreen=prevScreen;
		JLabel lblPeriodicDiscountId = new JLabel("Periodic Discount ID:");
		lblPeriodicDiscountId.setBounds(54, 33, 123, 22);
		getContentPane().add(lblPeriodicDiscountId);
		
		textField = new JTextField();
		textField.setBounds(176, 34, 145, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(23, 89, 89, 23);
		getContentPane().add(btnReturn);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int cnt=0;
				int discID=Integer.parseInt(textField.getText());
				if(discID==0)
				{
					JOptionPane.showMessageDialog(null, "invalid Discount ID");
					return;
				}
				dfrep.setPeriodicDiscountID(discID);
				for(Order or: OrderControl.getAllOrders())
				{
					if(or.getPeriodicDiscountID()==discID)
						cnt++;
				}
				dfrep.setOverallOrders(cnt);
				DiscountFeedbackReportControl.createDiscountFeedbackReport(dfrep);
				JOptionPane.showMessageDialog(null, "Generation Successfull! , ReportID:"+dfrep.getReportID());
				getPrevScreen().setVisible(true);
				dispose();
			}
		});
		btnGenerate.setBounds(322, 89, 89, 23);
		getContentPane().add(btnGenerate);
		this.setSize(452, 163);
		this.setVisible(true);
	}

	public JFrame getPrevScreen() {
		return prevScreen;
	}
	private static final long serialVersionUID = 1L;
	private JTextField textField;
}
