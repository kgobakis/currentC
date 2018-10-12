package database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class signup {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private String password = "";
	private boolean check = false;
	/**
	 * Launch the application.
	 */
    public  void run (){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup window = new signup();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
}

	/**
	 * Create the application.
	 */
	public signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 457, 471);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

//////////////////////-------------------------- USERNAME FIELD -------------------------------///////////////////////////////////	
		textField = new JTextField();
		textField.setBounds(150, 76, 146, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
//////////////////////--------------------------CREATE BUTTON -------------------------------///////////////////////////////////		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!check) {
						JOptionPane.showMessageDialog(null, "Check If Passwords Match. Then Try Again!");
					} else {	
						String first = textField_3.getText();
						String last = textField_4.getText();
						String username = textField.getText();
						Main.createTable();
						Main.insertUser(first, last, username, password);			
						JOptionPane.showMessageDialog(null, "You Have Succesfully Created Your Account!");
						frame.setVisible(false);
						}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreate.setBounds(169, 332, 115, 29);
		frame.getContentPane().add(btnCreate);
//////////////////////--------------------------LABELS OF FIELDS-------------------------------///////////////////////////////////		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(63, 79, 87, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblSingingUp = new JLabel("Singing Up");
		lblSingingUp.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblSingingUp.setBounds(169, 16, 115, 26);
		frame.getContentPane().add(lblSingingUp);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(63, 231, 87, 20);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(63, 269, 87, 20);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(63, 118, 87, 20);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("Repeat Password:");
		lblRepeatPassword.setBounds(27, 163, 135, 20);
		frame.getContentPane().add(lblRepeatPassword);
//////////////////////--------------------------FIRST NAME FIELD -------------------------------///////////////////////////////////		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(150, 228, 146, 26);
		frame.getContentPane().add(textField_3);
//////////////////////--------------------------LAST NAME FIELD -------------------------------///////////////////////////////////		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(150, 266, 146, 26);
		frame.getContentPane().add(textField_4);
//////////////////////--------------------------FIRST PASSWORD FIELD -------------------------------///////////////////////////////////		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 115, 145, 26);
		frame.getContentPane().add(passwordField);
//////////////////////--------------------------SECOND PASSWORD FIELD -------------------------------///////////////////////////////////		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(151, 160, 145, 26);
		frame.getContentPane().add(passwordField_1);
//////////////////////--------------------------CHECK BUTTON -------------------------------///////////////////////////////////		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char [] input = passwordField.getPassword();
				char [] input2 = passwordField_1.getPassword();
				
				
				if(isPasswordCorrect(input, input2)) {
					JOptionPane.showMessageDialog(null, "Passwords Match!");
					password = new String(passwordField.getPassword());
					check = true;
				}else {
					check = false;
					
					JOptionPane.showMessageDialog(null, "Passwords Do Not Match!");
					
				}
			}
		});
		btnCheck.setBounds(181, 194, 93, 23);
		frame.getContentPane().add(btnCheck);
	}
//////////////////////--------------------------METHOD TO CHECK IF PASSWORDS MATCH -------------------------------///////////////////////////////////	
	private static boolean isPasswordCorrect(char[] input, char[] input2) {
	    boolean isCorrect = true;

	    if (input.length != input2.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, input2);
	    }

	    //Zero out the password.
	    Arrays.fill(input2,'0');

	    return isCorrect;
	}
}
