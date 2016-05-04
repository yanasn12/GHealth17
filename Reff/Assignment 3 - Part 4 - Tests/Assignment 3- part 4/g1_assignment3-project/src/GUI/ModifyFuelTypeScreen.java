package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.JTextField;
import controllers.FuelTypeControl;
import entities.FuelType;

/**
 * This screen allows to modify name and base price for each fuel type.
 *
 */
public class ModifyFuelTypeScreen extends JFrame{
	
	private static final long serialVersionUID = -623482938548409187L;
	private JFrame PrevWindow;
	private JTextField textField;              // Fuel Type ID Field
	private JTextField textField_1;	           // Fuel Type Name Field
	private JTextField textField_2;            // Fuel Base Price Field
	private List<FuelType> fuels;
	
	
	public ModifyFuelTypeScreen(JFrame PW) {
		fuels = FuelTypeControl.getAllFuelTypes();
		
		setTitle("Modify Fuel Type");                              // Screen Name
		getContentPane().setBackground(new Color(51, 102, 153));  // Background Color
		getContentPane().setLayout(null);                
		
		JPanel panel = new JPanel();                               // First JPanel Definition 
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 11, 339, 109);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFuelTypeId = new JLabel("Fuel Type ID:");        // Fuel Type ID Label
		lblFuelTypeId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFuelTypeId.setBounds(10, 11, 92, 21);
		panel.add(lblFuelTypeId);                                   // add it to the panel
		
		textField = new JTextField();                               // text Field box Definition for Fuel Type ID
		textField.setBounds(159, 12, 142, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblFuelName = new JLabel("Fuel Name:");              // Fuel Name Label
		lblFuelName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFuelName.setBounds(10, 43, 92, 21);
		panel.add(lblFuelName);                                     // add it to the panel
		
		textField_1 = new JTextField();                             // text Field box Definition for Fuel Name
		textField_1.setEnabled(false);                              // set in to be unable to changing
		textField_1.setBounds(159, 43, 142, 20);
		panel.add(textField_1);                                     // add it to the panel
		textField_1.setColumns(10);
	
		JLabel lblFuelBasePrice = new JLabel("Fuel Base Price:");   // Fuel Base Price Label     
		lblFuelBasePrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFuelBasePrice.setBounds(10, 74, 92, 21);
		panel.add(lblFuelBasePrice);	                            // add it to the panel
		
		textField_2 = new JTextField();							    // text Field box Definition for Fuel Base Price
		textField_2.setEnabled(false);                              // set in to be unable to changing 
		textField_2.setBounds(159, 74, 142, 20);  
		panel.add(textField_2);                                     // add it to the panel
		textField_2.setColumns(10);
				
		JPanel panel_1 = new JPanel();								// Second JPanel Definition 
		panel_1.setBackground(new Color(153, 204, 255));
		panel_1.setBounds(10, 128, 339, 51);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		final JButton btnGet = new JButton("Get");                  // Button Creation: Get
		btnGet.setBounds(31, 14, 64, 23);
		panel_1.add(btnGet);
		
		final JButton btnSet = new JButton("Set");                  // Button Creation: Set
		btnSet.setEnabled(false);
		btnSet.setBounds(130, 14, 64, 23);
		panel_1.add(btnSet);
		
		JButton btnReturn = new JButton("Return");                   // Button Creation: Return
		btnReturn.setBounds(233, 11, 84, 29);
		panel_1.add(btnReturn);
			
		setPrevWindow(PW);
		
		
		// ===================Action Listeners=================== //
		
		//            Get Was Pressed
		btnGet.addActionListener(new ActionListener() {     
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				boolean fuelFound = false;
				try {
					String text = textField.getText();
					id = Integer.parseInt (text);
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Wrong FuelTypeID !");
					return;
				}
				for (FuelType fuel : fuels)
				{
					if (fuel.getFuelTypeID() == id)
					{
						fuelFound = true;
						FuelTypeControl.setActive(fuel);
						break;
					}
				}
				if (fuelFound == false)
				{
					textField_1.setEnabled(false);
					textField_2.setEnabled(false);
					btnSet.setEnabled(false);
					return;
				}
				textField_1.setText(FuelTypeControl.getActive().getFuelName());
				textField_2.setText(""+FuelTypeControl.getActive().getBasePrice());
				textField_1.setEnabled(true);
				textField_2.setEnabled(true);
				btnSet.setEnabled(true);
				btnGet.setEnabled(true);
			}
		});				
        
		//          Set Was Pressed
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double price = 0;
				try {
					price = Double.parseDouble(textField_2.getText());
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Invalid price entered !");
					return;
				}
				FuelTypeControl.getActive().setFuelName(textField_1.getText());
				FuelTypeControl.getActive().setBasePrice(price);
				FuelTypeControl.evictFuelType(FuelTypeControl.getActive().getFuelTypeID());
	
				btnSet.setEnabled(true);
				btnGet.setEnabled(true);
			}
		});
        //      Return Was Pressed
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevWindow.setVisible(true);
				dispose();
			}
		});
		
		
		this.setVisible(true);
		this.setSize(375,225);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public JFrame getPrevWindow() {
		return PrevWindow;
	}

	public void setPrevWindow(JFrame prevWindow) {
		PrevWindow = prevWindow;
	}

}
