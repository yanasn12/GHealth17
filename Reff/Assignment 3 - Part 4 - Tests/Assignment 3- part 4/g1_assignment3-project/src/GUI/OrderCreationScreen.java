package GUI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class OrderCreationScreen extends JFrame {
	
	private static final long serialVersionUID = -8903472257256958375L;

	public OrderCreationScreen() {
		getContentPane().setLayout(null);
		
		JLabel lblFuelOrderNumber = new JLabel("Fuel Order Number ");
		lblFuelOrderNumber.setBounds(10, 11, 401, 24);
		getContentPane().add(lblFuelOrderNumber);
		
		JLabel lblFuelType = new JLabel("Fuel Type:");
		lblFuelType.setBounds(10, 46, 86, 20);
		getContentPane().add(lblFuelType);
		
		JButton btnSubmit = new JButton("Order");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSubmit.setBounds(161, 212, 89, 23);
		getContentPane().add(btnSubmit);
	}
	
	public static void main (String args[])
	{
		OrderCreationScreen frame = new OrderCreationScreen();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
