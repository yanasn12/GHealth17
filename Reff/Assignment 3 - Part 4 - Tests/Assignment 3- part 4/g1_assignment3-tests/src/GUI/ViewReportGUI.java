package GUI;
import javax.swing.JFrame;

import entities.Report;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewReportGUI extends JFrame {
	private JTextField textFieldDate;
	private JTextField textFieldID;
	private JTable tableGeneral;
	private JTable tableData;
	private JTextArea textAreaDescription;
	private JLabel textTitle;
	private JScrollPane scrollPaneDataTable;
	private JScrollPane scrollPaneGeneralData;
	
	JFrame prevWindow;
	Report opened;
	
	public ViewReportGUI(JFrame prev, Report opened) {
			
		this.prevWindow  = prev;
		this.opened = opened;
		
		getContentPane().setBackground(new Color(51, 102, 153));
		setTitle("Report View");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setBounds(10, 11, 300, 489);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblReportTitle = new JLabel("Report Title:");
		lblReportTitle.setBounds(10, 11, 71, 14);
		panel.add(lblReportTitle);
		
		textTitle = new JLabel("<TITLE>");
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		textTitle.setBounds(78, 11, 212, 14);
		panel.add(textTitle);
		
		JLabel lblReportDate = new JLabel("Report Date:");
		lblReportDate.setBounds(10, 68, 71, 14);
		panel.add(lblReportDate);
		
		JLabel lblReportid = new JLabel("ReportID:");
		lblReportid.setBounds(10, 43, 71, 14);
		panel.add(lblReportid);
		
		textFieldDate = new JTextField();
		textFieldDate.setBackground(new Color(153, 204, 255));
		textFieldDate.setEditable(false);
		textFieldDate.setBounds(88, 65, 99, 20);
		panel.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		textFieldID = new JTextField();
		textFieldID.setBackground(new Color(153, 204, 255));
		textFieldID.setEditable(false);
		textFieldID.setBounds(88, 40, 99, 20);
		panel.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblReportDescription = new JLabel("Report Description:");
		lblReportDescription.setBounds(10, 96, 142, 14);
		panel.add(lblReportDescription);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setEditable(false);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setBackground(new Color(153, 204, 255));
		textAreaDescription.setBounds(10, 121, 280, 346);
		panel.add(textAreaDescription);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 153, 204));
		panel_1.setBounds(330, 11, 514, 489);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		scrollPaneGeneralData = new JScrollPane();
		scrollPaneGeneralData.setBounds(10, 11, 494, 43);
		panel_1.add(scrollPaneGeneralData);
		
		tableGeneral = new JTable();
		tableGeneral.setBackground(new Color(153, 204, 255));
		tableGeneral.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Empty"
			}
		));
		scrollPaneGeneralData.setViewportView(tableGeneral);
		
		scrollPaneDataTable = new JScrollPane();
		scrollPaneDataTable.setBounds(10, 65, 494, 413);
		panel_1.add(scrollPaneDataTable);
		
		tableData = new JTable();
		tableData.setBackground(new Color(153, 204, 255));
		tableData.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Empty"
			}
		));
		scrollPaneDataTable.setViewportView(tableData);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 153, 204));
		panel_2.setBounds(10, 511, 834, 40);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose ();
			}
		});
		btnReturn.setBounds(676, 11, 78, 23);
		panel_2.add(btnReturn);
		
		setActiveReport(opened);
		
		setSize (870,600);
		setVisible(true);
	}
	
	public void setActiveReport (Report report)
	{
		boolean widenTable = false;
		String[][] table;
		String[] colTitles;
		
		this.textTitle.setText(report.getReportTitle());
		this.textFieldID.setText(""+report.getReportID());
		this.textFieldDate.setText(report.getReportDate());
		this.textAreaDescription.setText(report.getReportDesc());
		
		table = report.getGeneralReportData();
		if (table.length <= 0)
			return;
		
		if (table[0][0].equals("#Split@data"))
		{
			scrollPaneGeneralData.setBounds(10, 11, 494, 156);
			scrollPaneDataTable.setBounds(10, 178, 494, 300);
			table = cutTitleRow (table);
			widenTable = true;
		}
		colTitles = table[0];
		
		tableGeneral.setModel(new DefaultTableModel(
				cutTitleRow(table),
				colTitles
				)
			{
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		
		table = report.getReportTable();
		if (table.length > 0)
			colTitles = table[0];
		else
			colTitles = new String[0];
		
		tableData.setModel(new DefaultTableModel(
				cutTitleRow(table),
				colTitles
				) 
			{
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		
		if (widenTable)
		{
			tableData.getColumnModel().getColumn(1).setPreferredWidth(40);
			tableData.getColumnModel().getColumn(2).setPreferredWidth(300);
		}
	}
	
	public String[][] cutTitleRow (String[][] table)
	{
		String[][] newTable;
		if (table.length-1 <= 0)
		{
			newTable = new String [1][table[0].length];
			for (int j = 0; j < newTable[0].length; j++)
				newTable[0][j] = "";
		}
		else
		{
			newTable = new String [table.length-1][table[0].length];
		
			for (int i = 0; i < newTable.length; i++)
				for (int j = 0; j < newTable[i].length; j++)
					newTable[i][j] = table[i+1][j];
		}
		return newTable;
	}
}
