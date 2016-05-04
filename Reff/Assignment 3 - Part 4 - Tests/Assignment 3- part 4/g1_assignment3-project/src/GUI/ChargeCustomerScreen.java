package GUI;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChargeCustomerScreen extends JFrame{
	private static final long serialVersionUID = -2043444801858004115L;
	private JFrame PrevWindow;
	public ChargeCustomerScreen(JFrame PW) {
		setPrevWindow(PW);
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 320, 206);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 228, 319, 47);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(213, 11, 96, 25);
		panel_1.add(btnReturn);
		setTitle("Customer Charge");
		this.setSize(355, 325);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	public JFrame getPrevWindow() {
		return PrevWindow;
	}
	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}
	

}
