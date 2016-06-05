package GUI_client;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MonthlyReports extends JPanel {

	/**
	 * Create the panel.
	 */
	public MonthlyReports() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 209, 48);
		add(panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("Select the desired report :");
		textArea.setForeground(new Color(0, 128, 0));
		textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(10, 11, 189, 26);
		panel.add(textArea);
		
		JButton button = new JButton("Current Monthly Report");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(new Color(153, 255, 102));
		button.setBounds(10, 80, 192, 39);
		add(button);
		
		JButton button_1 = new JButton("Current Monthly Report");
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(new Color(153, 255, 102));
		button_1.setBounds(10, 124, 192, 39);
		add(button_1);
		
		JButton button_2 = new JButton("View weekly reports");
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setForeground(Color.BLUE);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBackground(new Color(153, 255, 102));
		button_2.setBounds(10, 168, 192, 39);
		add(button_2);

	}

}
