package Dispatcher;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class Dispather_Gui_buttons extends JPanel {
private JButton button;
	/**
	 * Create the panel.
	 */
	public Dispather_Gui_buttons() {
		setLayout(new MigLayout("", "[]", "[][][][][][][][][][][][]"));
		setBackground(Color.WHITE);
		Icon AppointmentIcon = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\MakeAppointment.png");
		
		JButton button_1 = new JButton("");
		Icon LogOut = new ImageIcon("C:\\Users\\guy\\workspace\\OCSF_PRO_GUY\\src\\javagui\\resources\\logout.png");
		button = new JButton("");
		button.setIcon(AppointmentIcon);
		button.setBackground(Color.BLUE);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		add(button, "cell 0 1,grow");
		button_1 = new JButton("");
		button_1.setIcon(LogOut);
		button_1.setBackground(Color.BLUE);
		add(button_1, "cell 0 500,grow");
		
	}

}
