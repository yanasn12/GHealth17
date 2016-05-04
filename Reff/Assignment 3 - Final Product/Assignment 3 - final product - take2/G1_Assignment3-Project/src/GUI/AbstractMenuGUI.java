package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Dimension;



public class AbstractMenuGUI extends JFrame{
	
	final static int buttonNum = 12;
	private JFrame PrevWindow;
	public JButton[] functionButton = new JButton[buttonNum];
	protected JFrame mainframe;
	JPanel panel_1 = new JPanel();
	JPanel panel = new JPanel();
	
	public AbstractMenuGUI(JFrame loginGUI) {
		this.PrevWindow = loginGUI;
		this.mainframe = this;
		
		
		for(int i=0;i<buttonNum;i++)
		{
			functionButton[i] = new JButton("Func  "+i);
			functionButton[i].setPreferredSize(new Dimension(150, 40));
		}
		
		getContentPane().setBackground(Color.ORANGE);
		getContentPane().setLayout(null);
		
		panel.setBackground(new Color(255,204,102));
		panel.setBounds(158, 213, 185, 49);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnLogout = new JButton("Exit Menu");
		btnLogout.setBounds(37, 11, 113, 27);
		panel.add(btnLogout);
		
		panel_1.setBackground(new Color(255, 204, 102));
		panel_1.setBounds(10, 10, 484, 192);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		
		
		for(int i=0;i<buttonNum;i++)
		{
			panel_1.add(functionButton[i]);
			functionButton[i].setVisible(true);
			functionButton[i].setEnabled(false);
		}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(520,310);
		this.setVisible(true);
		this.setTitle("Menu");
	}
	private static final long serialVersionUID = 3474185206519297905L;
	
	public void setButtonNames(String str[])
	{
		int i;
		int min = Math.min(buttonNum, str.length);
		
		for(i=0;i<min;i++)
			functionButton[i].setText(str[i]);
		for(;i<buttonNum;i++)
			functionButton[i].setText(""+i);
	}
}
