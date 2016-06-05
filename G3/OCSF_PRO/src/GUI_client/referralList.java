package GUI_client;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class referralList extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					referralList frame = new referralList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public referralList() {
		setBounds(100, 100, 516, 524);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 478, 473);
		getContentPane().add(panel);
		
		JButton button = new JButton("eyes\t - Dr. Levi                20/5/16                  eye test");
		button.setBounds(0, 331, 448, 67);
		panel.add(button);
		
		JButton button_1 = new JButton("Cardiology- Dr.Cohen       2/6/16                blood test");
		button_1.setBounds(0, 132, 448, 67);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Orthpede- Dr.Uziel              5/6/16                    urine test");
		button_2.setBounds(0, 237, 448, 67);
		panel.add(button_2);

	}
}
