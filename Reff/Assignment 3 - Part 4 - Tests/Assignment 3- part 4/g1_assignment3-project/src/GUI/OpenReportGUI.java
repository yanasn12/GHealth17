package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controllers.ReportControl;
import entities.Report;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class OpenReportGUI extends JFrame {
	private JTextField textFieldReportID;
	JFrame prevWindow;
	List<Report> reports;
	JLabel statusLabel;
	
	public OpenReportGUI(JFrame prev) {
		
		
		this.prevWindow = prev;
		reports = ReportControl.getAllReports();
		
		getContentPane().setBackground(new Color(0, 102, 51));
		setTitle("Open Report");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 102));
		panel.setBounds(10, 11, 276, 96);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblReportid = new JLabel("ReportID");
		lblReportid.setBounds(10, 11, 77, 14);
		panel.add(lblReportid);
		
		textFieldReportID = new JTextField();
		textFieldReportID.setBounds(75, 8, 191, 20);
		panel.add(textFieldReportID);
		textFieldReportID.setColumns(10);
		
		statusLabel = new JLabel("");
		statusLabel.setForeground(new Color(204, 0, 0));
		statusLabel.setBounds(10, 71, 256, 14);
		panel.add(statusLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 102));
		panel_1.setBounds(10, 118, 276, 40);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(177, 11, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					for (Report report : reports)
					{
						if (report.getReportID() == Integer.parseInt(textFieldReportID.getText()))
						{
							new ViewReportGUI(getThisWindow(), report);
							statusLabel.setText("");
							setVisible(false);
							return;
						}
					
					}
					statusLabel.setText("Invalid report number !");
				}
				catch (Exception ex)
				{
					statusLabel.setText("Invalid report number !");
				}
			}
		});
		
		
		btnView.setBounds(10, 11, 89, 23);
		panel_1.add(btnView);
		
		setSize (315,210);
		setVisible(true);
	}

	public JFrame getThisWindow ()
	{
		return this;
	}
}
