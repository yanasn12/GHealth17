package Gui_server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.mysqlConnection;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Gui_Server_result extends JFrame {
	private ArrayList<String> list = new ArrayList<String>();
	private JPanel contentPane;
	// Instance attributes used in this example
	private	JPanel topPanel;
	private	JList listbox;
	private  String [] commendInProgress;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_Server_result frame = new Gui_Server_result();
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
	public Gui_Server_result() {
		this.setTitle("result");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		System.out.println(ServerGui.textline);
		commendInProgress= (ServerGui.textline).split(":");
		list=mysqlConnection.ActionMode(ServerGui.textline);
		ServerGui.textline="";
		if(!(commendInProgress[0].toUpperCase()=="PULL" || commendInProgress[0].toUpperCase()=="PULLBYKEY"))
		{
			JLabel lblResult = new JLabel("Result:");
			contentPane.add(lblResult, BorderLayout.NORTH);



				// Create a panel to hold all other components
				topPanel = new JPanel();
				topPanel.setLayout( new BorderLayout() );
				getContentPane().add( topPanel );

				// Create some items to add to the list
				String[] stockArr = new String[list.size()];
				stockArr = list.toArray(stockArr);
				if(stockArr.length==0)
					dispose();
				
				// Create a new listbox control
				listbox = new JList(stockArr);
				topPanel.add( listbox, BorderLayout.NORTH );

		}
		else
			JOptionPane.showMessageDialog(null,"Commened was carried out"+ ServerGui.textline, "Result",JOptionPane.PLAIN_MESSAGE);

		}
	 
	


}
	

