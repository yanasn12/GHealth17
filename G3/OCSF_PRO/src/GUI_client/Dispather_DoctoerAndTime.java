package GUI_client;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Dispather_DoctoerAndTime extends JPanel {

	/**
	 * Create the panel.
	 */
	public Dispather_DoctoerAndTime() {
		setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][]"));
		
		JLabel lblReffrals = new JLabel("Reffrals:");
		add(lblReffrals, "cell 3 1,alignx trailing");
		
		JComboBox comboBox_2 = new JComboBox();
		add(comboBox_2, "cell 4 1,growx");
		
		JLabel lblDoctors = new JLabel("Doctors:");
		add(lblDoctors, "cell 3 3,alignx trailing");
		setBackground(Color.WHITE);

		JComboBox comboBox = new JComboBox();
		add(comboBox, "cell 4 3,growx");
		
		JLabel lblDatesTimes = new JLabel("Dates & Times");
		add(lblDatesTimes, "cell 3 6,alignx trailing");
		
		JComboBox comboBox_1 = new JComboBox();
		add(comboBox_1, "cell 4 6,growx");

	}

}
