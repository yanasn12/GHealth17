package Gui_server;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class expertise_Gui extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private String quaryExp="";
	/**
	 * Create the panel.
	 */
	public expertise_Gui() {
		setLayout(new MigLayout("", "[][grow][]", "[][][]"));
		
		JLabel lblExpertisecode = new JLabel("expertise_code");
		add(lblExpertisecode, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);

		JLabel lblExpertisename = new JLabel("expertise_name");
		add(lblExpertisename, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quaryExp="expertise_code,"+textField.getText().toString();
				quaryExp=quaryExp+",expertise_name,"+textField_1.getText().toString();

				ServerGui.textline=ServerGui.textline+quaryExp.toString();
				System.out.println(ServerGui.textline);
				
				Gui_Server_result frame = new Gui_Server_result();
				frame.setVisible(true);
			}
		});
		add(btnOk, "cell 2 2");

	}

}
