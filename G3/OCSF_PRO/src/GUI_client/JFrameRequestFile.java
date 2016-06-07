package GUI_client;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class JFrameRequestFile extends JPanel {
	private JPanel panel;
	public static JButton button;
	private JButton button_1;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public JFrameRequestFile() {
		setLayout(new MigLayout("", "[144.00px][110.00]", "[33px][][][][][]"));
		
		
		panel = new JPanel();

		panel.setBounds(10, 11, 505, 450);
		add(panel, "cell 0 0,alignx left,aligny top");
		
		textArea = new JTextArea();
		textArea.setText("Select type of file :");
		textArea.setForeground(new Color(0, 128, 0));
		textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(32, 23, 189, 26);
		panel.add(textArea);
		
		button_1 = new JButton("Partial file");
		add(button_1, "flowx,cell 0 3");
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(new Color(153, 255, 102));
		button_1.setBounds(52, 110, 192, 39);
		
		button = new JButton("Full file");
		add(button, "cell 0 4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(new Color(153, 255, 102));
		button.setBounds(52, 60, 192, 39);

	}
}
