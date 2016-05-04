package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controllers.LoginCont;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestGUI extends JFrame{

	private static final long serialVersionUID = 470967729088806880L;
	private JTextField textField;
	private JFrame prevWindow;
	
	public TestGUI(JFrame m) {
		this.prevWindow=m;
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(59, 55, 77, 22);
		getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(219, 56, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnGetCus = new JButton("Test");
		btnGetCus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				LoginCont.sendMail("g1.myfuel@gmail.com","16499461", "sergeikov95@gmail.com", "Test Sub", "Message");
				//System.out.println(TestCont.handleTestingGUI(getID()).toString());
				prevWindow.setVisible(true);
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		btnGetCus.setBounds(159, 203, 89, 23);
		getContentPane().add(btnGetCus);
		setSize(350, 350);
		setVisible(true);
		
	}
	
	
	
	public int getID()
	{
		return Integer.parseInt(textField.getText());  
	}
}
