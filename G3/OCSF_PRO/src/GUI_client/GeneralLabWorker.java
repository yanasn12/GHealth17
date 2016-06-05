package GUI_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class GeneralLabWorker extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopReports;
	private JButton btnInsetLabReferral;
	private InsertReferral1 inRef;
//	private WeeklyReport weekRep;
	private JTextField textField;
	private JLabel lblPatientId;
	private JButton btnApply;
	private JComboBox comboBox;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralLabWorker frame = new GeneralLabWorker();
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
	public GeneralLabWorker() {
		initComponent(); //// יצרתי את INIT שבו יש את כל איתחול המרכיבים
		createEvent1();/// הפעלת האירוע- הצגת המסך
		//createEvent2();
	}
	///////// init component of desktop reports
	private void initComponent() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GeneralLabWorker.class.getResource("/javagui/resources/GHealthlogo.png")));
		label.setBounds(113, 25, 242, 68);
		contentPane.add(label);
		
		btnInsetLabReferral = new JButton("Inset lab referral");
		btnInsetLabReferral.setEnabled(false);
		
		
		btnInsetLabReferral.setIcon(new ImageIcon(GeneralLabWorker.class.getResource("/javagui/resources/img16x16/reports.png")));
		btnInsetLabReferral.setHorizontalAlignment(SwingConstants.LEFT);
		btnInsetLabReferral.setForeground(Color.BLACK);
		btnInsetLabReferral.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInsetLabReferral.setBackground(Color.WHITE);
		btnInsetLabReferral.setBounds(10, 133, 192, 39);
		contentPane.add(btnInsetLabReferral);
		
		desktopReports = new JDesktopPane();
		desktopReports.setBackground(Color.WHITE);
		desktopReports.setBounds(212, 133, 693, 640);
		contentPane.add(desktopReports);
		desktopReports.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(79, 99, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(10, 105, 59, 14);
		contentPane.add(lblPatientId);
		
		btnApply = new JButton("apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnApply.setVisible(false);
				textField.setEnabled(false);
				btnInsetLabReferral.setEnabled(true);
			}
		});//end addActionListener
		btnApply.setBounds(185, 98, 76, 23);
		contentPane.add(btnApply);
		
		comboBox = new JComboBox();
		comboBox.setBounds(393, 102, 425, 20);
		contentPane.add(comboBox);
		
		JLabel lblChooseReferral = new JLabel("choose referral");
		lblChooseReferral.setBounds(284, 102, 99, 20);
		contentPane.add(lblChooseReferral);
		
		JButton btnInsetImage = new JButton("Inset image");
		btnInsetImage.setHorizontalAlignment(SwingConstants.LEFT);
		btnInsetImage.setForeground(Color.BLACK);
		btnInsetImage.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInsetImage.setEnabled(false);
		btnInsetImage.setBackground(Color.WHITE);
		btnInsetImage.setBounds(10, 194, 192, 39);
		contentPane.add(btnInsetImage);
		
	}

	private void createEvent1() {
		btnInsetLabReferral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (inRef == null || inRef.isClosed())/////////
					inRef = new InsertReferral1();  ////  לא לשכוח להצהיר על המשתנה כפרטי במחלקה 
				desktopReports.add(inRef);/// הצגה על המסך הקטן
				inRef.show(); // איפשור תצוגה
				
			}
		});
	}
}
		

