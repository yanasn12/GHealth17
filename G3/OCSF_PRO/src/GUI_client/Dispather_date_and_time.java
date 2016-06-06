package GUI_client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dispather_date_and_time extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Dispather_date_and_time() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblTimedate = new JLabel("Time&Date");
		lblTimedate.setBounds(166, 37, 77, 14);
		add(lblTimedate);
		
		table = new JTable();
		table.setBounds(46, 93, 360, 145);
		add(table);
		
		JButton btnMakeIt = new JButton("Make It");
		btnMakeIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMakeIt.setBounds(351, 266, 89, 23);
		add(btnMakeIt);

	}
}
