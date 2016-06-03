package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI_client.Main;
import jdbc.mysqlConnection;

import java.awt.event.InputMethodListener;
import java.util.ArrayList;
import java.awt.event.InputMethodEvent;
import javax.swing.JButton;

public class labs_Server_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel_1;
	private String quaryLab;
	public ArrayList<String> list = new ArrayList<String>();
	private Gui_Server_result window;
	/**
	 * Create the panel.
	 */
	public labs_Server_Gui() {
		setLayout(new MigLayout("", "[][grow][grow]", "[][][][]"));
		
		JLabel lblLabNumber = new JLabel("Lab Number");
		add(lblLabNumber, "cell 0 0");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}});
		textField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				
			}
		});
		textField.setColumns(10);
		
		
		
		JLabel lblLabName = new JLabel("Lab Name");
		add(lblLabName, "cell 0 2,alignx trailing");
			
		textField_1 = new JTextField();
		add(textField_1, "cell 1 2,growx");
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}});
		textField_1.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
				

			}
		});
		textField_1.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quaryLab="lab_num,"+textField.getText().toString();
				quaryLab=quaryLab+",lab_name,"+textField_1.getText().toString();
				ServerGui.textline=ServerGui.textline+quaryLab.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);

			}
		});
		add(btnOk, "cell 2 3,grow");

		

		
		
		
				
}

}
