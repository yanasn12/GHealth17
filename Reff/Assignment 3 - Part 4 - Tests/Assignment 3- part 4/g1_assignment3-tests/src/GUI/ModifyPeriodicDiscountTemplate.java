package GUI;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import controllers.PeriodicDiscountsControl;
import entities.PeriodicDiscountTemplate;
import javax.swing.JSpinner;

import javax.swing.JSpinner;

public class ModifyPeriodicDiscountTemplate extends JFrame{
	
	private static final long serialVersionUID = -6839904437153140584L;
	private JFrame prevWindow;
	private JFrame mainframe;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JSpinner spinner;
	private JSpinner spinner_5 ;
	private JSpinner spinner_2;
	private JSpinner spinner_1;
	private JSpinner spinner_3;
	private JSpinner spinner_4;
	
	private JButton btnNew = new JButton("New");
	private	JButton btnGet = new JButton("Get");
	private JButton btnSet = new JButton("Set");
	
	public ModifyPeriodicDiscountTemplate(JFrame pw) {
		setTitle("Periodic Discount Template Modification");
		getContentPane().setBackground(SystemColor.activeCaption);
		this.setPrevWindow(pw);
		this.setMainframe(this);
		
		SpinnerModel hour =new SpinnerNumberModel(00,00,23, 1);  
		SpinnerModel min =new SpinnerNumberModel(00,00,59, 1); 
		SpinnerModel sec =new SpinnerNumberModel(00,00,59, 1); 
		
		SpinnerModel hour1 =new SpinnerNumberModel(00,00,23, 1);  
		SpinnerModel min1 =new SpinnerNumberModel(00,00,59, 1); 
		SpinnerModel sec1 =new SpinnerNumberModel(00,00,59, 1); 

		spinner = new JSpinner(hour1);
		spinner_1 = new JSpinner(min1);
		spinner_2 = new JSpinner(sec1);
		spinner_3 = new JSpinner(sec);
		spinner_4 = new JSpinner(min);
		spinner_5 = new JSpinner(hour);
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 339, 181);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPeriodicDiscountTemplate = new JLabel("Template ID:");
		lblPeriodicDiscountTemplate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPeriodicDiscountTemplate.setBounds(10, 11, 100, 21);
		panel.add(lblPeriodicDiscountTemplate);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiscount.setBounds(10, 43, 100, 21);
		panel.add(lblDiscount);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartTime.setBounds(10, 74, 77, 21);
		panel.add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndTime.setBounds(10, 102, 77, 21);
		panel.add(lblEndTime);
		
		JLabel lblMinimalFuelAmount = new JLabel("Minimal Fuel Amount:");
		lblMinimalFuelAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinimalFuelAmount.setBounds(10, 132, 126, 21);
		panel.add(lblMinimalFuelAmount);
		
		textField = new JTextField();
		textField.setBounds(163, 12, 166, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(163, 40, 166, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		spinner.setEnabled(false);
		spinner.setBounds(163, 71, 45, 20);
		panel.add(spinner);
		
		spinner_1.setEnabled(false);
		spinner_1.setBounds(218, 71, 45, 20);
		panel.add(spinner_1);
		
		spinner_2.setEnabled(false);
		spinner_2.setBounds(273, 71, 45, 20);
		panel.add(spinner_2);
		
		JLabel label = new JLabel(":");
		label.setBounds(210, 71, 20, 21);
		panel.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(265, 74, 46, 14);
		panel.add(label_1);
		
		spinner_3.setEnabled(false);
		spinner_3.setBounds(273, 102, 45, 20);
		panel.add(spinner_3);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(265, 105, 46, 14);
		panel.add(label_2);
		
		spinner_4.setEnabled(false);
		spinner_4.setBounds(218, 102, 45, 20);
		panel.add(spinner_4);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(210, 102, 20, 21);
		panel.add(label_3);
		
		spinner_5.setEnabled(false);
		spinner_5.setBounds(163, 102, 45, 20);
		panel.add(spinner_5);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(163, 133, 166, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 197, 340, 46);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<PeriodicDiscountTemplate> pptl = new LinkedList<PeriodicDiscountTemplate>();
				pptl=PeriodicDiscountsControl.getAllPeriodicDiscountTemplates();
				int maxPDTID=-1;
				for(PeriodicDiscountTemplate p : pptl)
					if(p.getPeriodicDiscountTemplateID()>maxPDTID)
						maxPDTID=p.getPeriodicDiscountTemplateID();
				
				btnSet.setEnabled(true);
				textField.setEnabled(false);
				textField.setText(""+(maxPDTID+1));
				spinner.setEnabled(true);
				spinner_1.setEnabled(true);
				spinner_2.setEnabled(true);
				spinner_3.setEnabled(true);
				spinner_4.setEnabled(true);
				spinner_5.setEnabled(true);
				
				spinner.setValue(0);
				spinner_1.setValue(0);
				spinner_2.setValue(0);
				spinner_3.setValue(0);
				spinner_4.setValue(0);
				spinner_5.setValue(0);
				
				
				textField_1.setEnabled(true);
				textField_1.setText("");
				textField_2.setText("");
				textField_2.setEnabled(true);
				btnGet.setEnabled(false);
			}
		});
		
		btnNew.setBounds(10, 11, 65, 23);
		panel_1.add(btnNew);
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int templateID;			
				try{
					templateID=Integer.parseInt(textField.getText());
				}
				catch(NumberFormatException er)
				{
					JOptionPane.showMessageDialog(mainframe, "String Can not be an Integer", "Error Getting Template", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				PeriodicDiscountTemplate pdt = null;
				pdt = PeriodicDiscountsControl.getPeriodicDiscountTemplateById(Integer.parseInt(textField.getText()));
				if(pdt == null || pdt.getPeriodicDiscountTemplateID()==0)
				{
					JOptionPane.showMessageDialog(mainframe, "No Such Discount Template In the Database", "Error Getting Template", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				textField.setEnabled(false);
				spinner.setEnabled(true);
				spinner_1.setEnabled(true);
				spinner_2.setEnabled(true);
				spinner_3.setEnabled(true);
				spinner_4.setEnabled(true);
				spinner_5.setEnabled(true);
				textField_1.setEnabled(true);
				textField_2.setEnabled(true);
				btnSet.setEnabled(true);
				
				textField_1.setText(""+pdt.getDiscount());
				textField_2.setText(""+pdt.getMinFuelAmount());
				
				String[] splitedStart = pdt.getStartTime().split(":");
				String[] splitedEnd = pdt.getEndTime().split(":");
				
				spinner.setValue(Integer.parseInt(splitedStart[0]));
				spinner_1.setValue(Integer.parseInt(splitedStart[1]));
				spinner_2.setValue(Integer.parseInt(splitedStart[2]));
				spinner_3.setValue(Integer.parseInt(splitedEnd[2]));
				spinner_4.setValue(Integer.parseInt(splitedEnd[1]));
				spinner_5.setValue(Integer.parseInt(splitedEnd[0]));
				
			}
		});
		
		btnGet.setBounds(85, 11, 65, 23);
		panel_1.add(btnGet);
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double dis;
				double minAmount;
				try{
				 dis = Double.parseDouble(textField_1.getText());
				 minAmount = Double.parseDouble(textField_2.getText());
				}
				catch(NumberFormatException ee)
				{
					JOptionPane.showMessageDialog(mainframe, "String Can not be a Number", "Error Getting Template", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(dis<0.0 || dis>1.0 || minAmount<0.0)
				{
					JOptionPane.showMessageDialog(mainframe, "Discount must me between 0-1 and the minimun amount must be positive", "Number Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				PeriodicDiscountTemplate pdt = null;
				pdt = PeriodicDiscountsControl.getPeriodicDiscountTemplateById(Integer.parseInt(textField.getText()));
				if(pdt == null || pdt.getPeriodicDiscountTemplateID()==0)//if its a new template
				{
					pdt.setDiscount(dis);
					pdt.setPeriodicDiscountTemplateID(Integer.parseInt(textField.getText()));
					String startTime = ""+spinner.getValue()+":"+spinner_1.getValue()+":"+spinner_2.getValue();
					String endTime = ""+spinner_5.getValue()+":"+spinner_4.getValue()+":"+spinner_3.getValue();
					pdt.setStartTime(startTime);
					pdt.setEndTime(endTime);
					pdt.setMinFuelAmount(minAmount);
					PeriodicDiscountsControl.createPeriodicDiscountTemplate(pdt);
					
				}
				else
				{
					pdt.setDiscount(dis);
					String startTime = ""+spinner.getValue()+":"+spinner_1.getValue()+":"+spinner_2.getValue();
					String endTime = ""+spinner_5.getValue()+":"+spinner_4.getValue()+":"+spinner_3.getValue();
					pdt.setStartTime(startTime);
					pdt.setEndTime(endTime);
					pdt.setMinFuelAmount(minAmount);
					PeriodicDiscountsControl.updatePeriodicDiscountTemplate(pdt.getPeriodicDiscountTemplateID());
				}
				textField.setEnabled(true);
				spinner.setEnabled(false);
				spinner_1.setEnabled(false);
				spinner_2.setEnabled(false);
				spinner_3.setEnabled(false);
				spinner_4.setEnabled(false);
				spinner_5.setEnabled(false);
				textField_1.setEnabled(false);
				textField_2.setEnabled(false);
				btnSet.setEnabled(false);
				btnGet.setEnabled(true);
				
			}
		});
		
		btnSet.setEnabled(false);
		btnSet.setBounds(160, 11, 65, 23);
		panel_1.add(btnSet);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prevWindow.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(241, 11, 89, 23);
		panel_1.add(btnReturn);
		
		this.setVisible(true);
		this.setSize(375,290);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	
	
	
	public JFrame getPrevWindow() {
		return prevWindow;
	}

	public void setPrevWindow(JFrame prevWindow) {
		this.prevWindow = prevWindow;
	}

	public JFrame getMainframe() {
		return mainframe;
	}

	public void setMainframe(JFrame mainframe) {
		this.mainframe = mainframe;
	}
}
