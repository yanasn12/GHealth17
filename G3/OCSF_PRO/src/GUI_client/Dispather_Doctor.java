package GUI_client;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import Controllers.Dispatcher;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Dispather_Doctor extends JPanel {
	public static JButton btnSelect;
	/**
	 * Create the panel.
	 */
	public Dispather_Doctor() {
		
		setLayout(new MigLayout("", "[][][grow][][][grow]", "[][grow][][][][][][][][][]"));
		setBackground(Color.WHITE);


				
				btnSelect = new JButton("Select");
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnSelect.setVisible(true);		
				
				
				
				
				add(btnSelect, "cell 5 10,alignx right");

	}

}
