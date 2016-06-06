package GUI_client;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dispather_Doctor extends JPanel {
	public static JButton btnSelect;

	/**
	 * Create the panel.
	 */
	public Dispather_Doctor() {
		
		setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][][][][]"));
		setBackground(Color.WHITE);
		
		JLabel lblDoctors = new JLabel("Doctors:");
		add(lblDoctors, "cell 3 1,alignx trailing");
		
				JComboBox comboBox = new JComboBox();
				add(comboBox, "cell 4 1,growx");
				btnSelect = new JButton("Select");
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnSelect.setVisible(true);
				add(btnSelect, "cell 4 9,alignx right");

	}

}
