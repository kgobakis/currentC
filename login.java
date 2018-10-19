package database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class login{

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPasswork;
	 Main instance = new Main();
	/**
	 * Launch the application.
	 */
	public  void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 544, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(140, 86, 201, 26);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPasswork = new JPasswordField();
		pwdPasswork.setBounds(140, 128, 201, 26);
		frame.getContentPane().add(pwdPasswork);
//////////////////////--------------------------TEXT FIELDS -------------------------------///////////////////////////////////		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(45, 89, 88, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(45, 131, 88, 20);
		frame.getContentPane().add(lblPassword);
//////////////////////--------------------------NEW USER BUTTON-------------------------------///////////////////////////////////		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signup sign = new signup();
				sign.run();
			}
		});
		btnNewUser.setBounds(392, 237, 115, 29);
		frame.getContentPane().add(btnNewUser);
		
//////////////////////--------------------------LOGIN BUTTON -------------------------------///////////////////////////////////	
	
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String username = txtUsername.getText();
					String password = new String(pwdPasswork.getPassword());	
					if (Main.userLoginCheck(username, password)) {
						welcome wel = new welcome(); wel.run();
						} else {JOptionPane.showMessageDialog(null, "Either Username or Password Wrong. Please Try Again!");}
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
			}
		});
		btnLogin.setBounds(178, 170, 115, 29);
		frame.getContentPane().add(btnLogin);
//////////////////////--------------------------CURRENTC TEXT -------------------------------///////////////////////////////////	
		JLabel lblCurrentc = new JLabel("currentC");
		lblCurrentc.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblCurrentc.setBounds(190, 0, 126, 50);
		frame.getContentPane().add(lblCurrentc);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setBounds(392, 197, 115, 29);
		frame.getContentPane().add(btnForgotPassword);
	}

}
