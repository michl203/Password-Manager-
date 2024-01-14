package PasswordManager.copy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.amdelamar.jotp.OTP;
import com.amdelamar.jotp.type.Type;
import javax.swing.border.TitledBorder;

import Ceasar_App.RegistrationFrame;
public class LoginWindow extends JFrame{
	private JPasswordField passwordTXT;
	private JPasswordField usernameTXT;
	private JButton LoginBtn;
	private totpReg totpPanel; 
	public LoginWindow() {
		getContentPane().setBackground(new Color(102, 102, 204));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(204, 204, 204));
		panel.setBounds(93, 83, 580, 309);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Username = new JLabel("Username");
		Username.setFont(new Font("Times New Roman", Font.BOLD, 30));
		Username.setBounds(40, 49, 141, 43);
		panel.add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Times New Roman", Font.BOLD, 30));
		Password.setBounds(40, 143, 132, 43);
		panel.add(Password);
		
		passwordTXT = new JPasswordField();
		passwordTXT.setBounds(204, 145, 272, 51);
		panel.add(passwordTXT);
		
		usernameTXT = new JPasswordField();
		usernameTXT.setBounds(204, 51, 272, 51);
		panel.add(usernameTXT);
		
	    LoginBtn = new JButton("Login");
		LoginBtn.setFont(new Font("Tahoma", Font.BOLD, 25));
		LoginBtn.setBounds(204, 235, 272, 43);
		panel.add(LoginBtn);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(305, 29, 150, 36);
		getContentPane().add(lblNewLabel);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(204, 204, 204));
		panel2.setBounds(93, 437, 590, 83);
		getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JLabel registerlbl = new JLabel("New? Register Today!");
		registerlbl.setFont(new Font("Tahoma", Font.BOLD, 25));
		registerlbl.setBounds(10, 22, 280, 37);
		panel2.add(registerlbl);
		
		JButton registerBtn = new JButton("Register");
		 registerBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Create an instance of the RegistrationFrame
	                Registration Registration = new Registration(totpPanel);
	                // Make the registrationFrame visible
	                Registration.setVisible(true);
	            }
	        });
		registerBtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		registerBtn.setBounds(300, 18, 278, 48);
		panel2.add(registerBtn);
	}
	public JButton getLoginBtn() {
		return LoginBtn;
	}
	
	public String getUsername() {
		return usernameTXT.getText();
	}
	
	public String getPassword() {
		return new String(passwordTXT.getPassword());
	}
}
