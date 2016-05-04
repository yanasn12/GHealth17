package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import controllers.FuelTypeControl;
import controllers.LoginCont;
import controllers.StockControl;
import entities.FuelReplenish;
import entities.FuelStock;
import entities.FuelType;
import entities.Worker;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ModifyStockLevelsScreen extends JFrame{
	
	private static final long serialVersionUID = 8863026803172287041L;
	private JFrame PrevWindow;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnGet = new JButton("Get");
	private JButton btnSet = new JButton("Set");
	private ModifyStockLevelsScreen mainframe;
	private JComboBox comboBox;
	LinkedList<FuelType> fuels;
	private JTextField textFieldStock;
	private FuelStock curr;
	
	public ModifyStockLevelsScreen(JFrame PW) {
		this.mainframe=this;
		curr = null;
		fuels = new LinkedList<FuelType>();
		
		getContentPane().setBackground(new Color(0, 102, 153));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 11, 409, 162);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFuelTypeId = new JLabel("Fuel Type:");
		lblFuelTypeId.setBounds(10, 12, 89, 16);
		lblFuelTypeId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblFuelTypeId);
		
		JLabel lblMin = new JLabel("Minimum Warnning Level:");
		lblMin.setBounds(10, 85, 156, 16);
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblMin);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 84, 145, 20);
		textField_2.setEnabled(false);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMaximumWarnningLevel = new JLabel("Maximum Warnning Level:");
		lblMaximumWarnningLevel.setBounds(10, 112, 156, 16);
		lblMaximumWarnningLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblMaximumWarnningLevel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(173, 111, 145, 20);
		textField_3.setEnabled(false);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(172, 11, 146, 20);
		panel.add(comboBox);
		
		JLabel lblCurrentStockLevel = new JLabel("Current Stock Level:");
		lblCurrentStockLevel.setBounds(10, 60, 156, 14);
		panel.add(lblCurrentStockLevel);
		
		textFieldStock = new JTextField();
		textFieldStock.setEditable(false);
		textFieldStock.setBounds(173, 53, 145, 20);
		panel.add(textFieldStock);
		textFieldStock.setColumns(10);
		
		for (FuelType f : FuelTypeControl.getAllFuelTypes())
		{
			if (f.getFuelTypeID() != FuelTypeControl.HOUSE_FUEL_ID)
			{
				fuels.add(f);
				comboBox.addItem(f.getFuelName());
			}
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(10, 184, 409, 47);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(310, 11, 89, 25);
		panel_1.add(btnReturn);
		
		
		//get was pressed
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setEnabled(true);
				textField_3.setEnabled(true);
				btnSet.setEnabled(true);
				FuelStock fs = null;
				
				List<FuelStock> fl = new LinkedList<FuelStock>();
				fl=StockControl.getAllFuelStocks();
				
				for(FuelStock f : fl)
				{
					if(f.getStationID()==(((Worker)LoginCont.getCurrUser()).getLocationID()) 
							&& f.getFuelTypeID()==fuels.get(comboBox.getSelectedIndex()).getFuelTypeID())
					{
						fs=f;
						break;
					}
				}
				
				if(fs==null)
				{
					comboBox.setEnabled(true);
					textField_2.setEnabled(false);
					textField_3.setEnabled(false);
					JOptionPane.showMessageDialog(mainframe, "No such FuelTypeID/StationID comination in the database", "Number Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else{
					textField_2.setText(""+fs.getMinStockLevel());
					textField_3.setText(""+fs.getMaxStockLevel());
					textFieldStock.setText(""+fs.getStockAmount());
					curr = fs;
				}
				//fs=StockControl.getFuelStockById(Integer.parseInt(textField.getText()), Integer.parseInt(textField_1.getText()));

			}
		});
		btnGet.setBounds(10, 12, 75, 23);
		panel_1.add(btnGet);
		
		//SET WAS PRESSED//
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.setEnabled(true);
				textField_2.setEnabled(false);
				textField_3.setEnabled(false);
				btnSet.setEnabled(false);
				
				int minLev,maxLev;
				minLev=Integer.parseInt(textField_2.getText());
				maxLev=Integer.parseInt(textField_3.getText());
				if(minLev<0 || maxLev<0 || maxLev < minLev)
				{
					JOptionPane.showMessageDialog(mainframe, "Invalid level values !", "Number Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				curr.setMinStockLevel(minLev);
				curr.setMaxStockLevel(maxLev);
				StockControl.updateFuelStock(curr.getFuelTypeID(), curr.getStationID());		
			}
		});
		
		btnSet.setEnabled(false);
		btnSet.setBounds(93, 12, 75, 23);
		panel_1.add(btnSet);
		
		JButton btnReplenishes = new JButton("Replenishes");
		btnReplenishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FuelReplenish repl = null;
				if (curr == null)
					return;
				for (FuelReplenish f : curr.getFuelReplenishes())
				{
					if (f.getStatus() != StockControl.RECEIVED)
					{
						repl = f;
						break;
					}
				}
				if (repl != null)
				{
					new FuelReplenishScreen(mainframe, repl);
					setVisible (false);
				}
				else
					JOptionPane.showMessageDialog(mainframe, "This stock has no active replenship orders at the moment.");
			}
		});
		btnReplenishes.setBounds(178, 12, 122, 23);
		panel_1.add(btnReplenishes);
		setTitle("Modify Fuel Stock Levels");
		setPrevWindow(PW);
		
		this.setVisible(true);
		this.setSize(445, 280);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	public JFrame getPrevWindow() {
		return PrevWindow;
	}
	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}
	
	public void updateAmount (int amount)
	{
		textFieldStock.setText(""+amount);
	}
}
