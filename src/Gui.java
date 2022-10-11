
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Gui extends JFrame { // ממשק למשתמש

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPatma = new JLabel("Welcome to our Pizza!");
		lblPatma.setForeground(new Color(0, 51, 153));
		lblPatma.setFont(new Font("David", Font.PLAIN, 20));
		lblPatma.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatma.setBounds(26, 10, 341, 55);
		contentPane.add(lblPatma);

		JLabel jblWorkersTime = new JLabel("Kitchen Workers working time");
		jblWorkersTime.setBounds(25, 75, 180, 33);
		contentPane.add(jblWorkersTime);

		textField = new JTextField();
		textField.setText("1");
		textField.setBounds(66, 117, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNumberOfTech = new JLabel("Number of Pizza Guys");
		lblNumberOfTech.setBounds(236, 81, 190, 20);
		contentPane.add(lblNumberOfTech);

		textField_1 = new JTextField();
		textField_1.setText("2");
		textField_1.setBounds(265, 117, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.setForeground(new Color(51, 51, 153));
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int kitchenWaitingTime;
				try{
					kitchenWaitingTime=Integer.parseInt(textField.getText());
				}
				catch(NumberFormatException e){
					kitchenWaitingTime=2;
				}
				int numOfPizzaGuys;
				try{
					numOfPizzaGuys=Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException e){
					numOfPizzaGuys=1;
				}
				System.out.println("Welcome to our Pizza!");
				String calls = "calls.txt";
				pizza branch = new pizza(calls,kitchenWaitingTime,numOfPizzaGuys);
				Thread t= new Thread  (branch);
				t.start();
			}
		});
		btnStart.setBounds(66, 200, 89, 23);
		contentPane.add(btnStart);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(51, 51, 153));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(265, 200, 89, 23);
		contentPane.add(btnExit);
	}
}