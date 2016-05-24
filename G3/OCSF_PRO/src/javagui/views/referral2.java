package javagui.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

public class referral2 extends JInternalFrame {
	private JTextField txtBkjbb;


	/**
	 * Create the frame.
	 */
	public referral2() {
		setFrameIcon(new ImageIcon(referral2.class.getResource("/javagui/resources/img16x16/laboratory-icon.png")));
		setTitle("referral");
		setBounds(100, 100, 450, 300);
		
		txtBkjbb = new JTextField();
		txtBkjbb.setText("bkjbb");
		txtBkjbb.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(155)
					.addComponent(txtBkjbb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addComponent(txtBkjbb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(157, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

}
