package GUI_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ClinicManegementRrports extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClinicManegementRrports frame = new ClinicManegementRrports();
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
	public ClinicManegementRrports() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClinicManegementRrports.class.getResource("/javagui/resources/report-icon.jpg")));
		setTitle("Clinic maneger reports");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 412);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ClinicManegementRrports.class.getResource("/javagui/resources/GHealthlogo.png")));
		label.setBounds(113, 25, 242, 68);
		contentPane.add(label);
		
		JButton btnViewMonthiy = new JButton("View monthiy report ");
		btnViewMonthiy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewMonthiy.setIcon(new ImageIcon(ClinicManegementRrports.class.getResource("/javagui/resources/img16x16/reports.png")));
		btnViewMonthiy.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewMonthiy.setForeground(new Color(0, 0, 255));
		btnViewMonthiy.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewMonthiy.setBackground(new Color(153, 255, 102));
		btnViewMonthiy.setBounds(10, 183, 192, 39);
		contentPane.add(btnViewMonthiy);
		
		JButton btnNewButton = new JButton("View weekly reports");
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setBackground(new Color(153, 255, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon(ClinicManegementRrports.class.getResource("/javagui/resources/img16x16/reports.png")));
		btnNewButton.setBounds(10, 130, 192, 39);
		contentPane.add(btnNewButton);
	}
}
