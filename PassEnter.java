package PasswordManager.copy;
import javax.swing.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
public class PassEnter extends JPanel{
	private JTextField websiteTXT;
	private JTextField usernameTXT;
	private JTextField username1TXT;
	private JTextField passwordTXT;
	public PassEnter() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 153));
		panel.setBorder(new TitledBorder(null, "Enter Passwords", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(55, 57, 765, 491);
		add(panel);
		panel.setLayout(null);
		
		JLabel Website = new JLabel("Website");
		Website.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Website.setBounds(66, 147, 100, 50);
		panel.add(Website);
		
		JLabel Username = new JLabel("Username");
		Username.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Username.setBounds(66, 226, 158, 50);
		panel.add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Password.setBounds(67, 334, 140, 50);
		panel.add(Password);
		
		websiteTXT = new JTextField();
		websiteTXT.setBounds(267, 147, 309, 47);
		panel.add(websiteTXT);
		websiteTXT.setColumns(10);
		
		usernameTXT = new JTextField();
		usernameTXT.setColumns(10);
		usernameTXT.setBounds(267, 232, 309, 47);
		panel.add(usernameTXT);
		
		JButton enterpassBtn = new JButton("Enter Password");
		enterpassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Insert the user's new password into the MySQL database
				String website = websiteTXT.getText();
				String username = usernameTXT.getText();
				String password = passwordTXT.getText();
                String appUsername = username1TXT.getText();
				PassManagerDAO po = new PassManagerDAO();
				//Get the user id from database
			    int userid= po.getUserIDfromUsername(appUsername);
				// Encrypt the password being entered
			    byte[] key=  po.retrievekey(userid);
			    SecretKey secretKey = new SecretKeySpec(key, 0, key.length, "AES");
			   String encryptedPass = Security.encrypt(password, secretKey);
			   if(po.insertPasswords(userid ,website, username, encryptedPass)) {
			   JOptionPane.showMessageDialog(null,"Credentials inserted successfully!");
			   //Clear all fields upon successful entry of credentials.
			     websiteTXT.setText("");
			     usernameTXT.setText("");
			     passwordTXT.setText("");
			     username1TXT.setText("");	
			   }else {
			   JOptionPane.showMessageDialog(null,"Error!Credentials not entered! Try Again!");
			   }
			}
		});
		enterpassBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		enterpassBtn.setBounds(267, 419, 309, 50);
		panel.add(enterpassBtn);
		
		username1TXT = new JTextField();
		username1TXT.setColumns(10);
		username1TXT.setBounds(282, 31, 309, 47);
		panel.add(username1TXT);
		
		JLabel username1 = new JLabel("Username (for this app)");
		username1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		username1.setBounds(10, 25, 262, 50);
		panel.add(username1);
		
		JLabel Enter1 = new JLabel("Enter New Passwords");
		Enter1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Enter1.setBounds(297, 108, 279, 28);
		panel.add(Enter1);
		
		passwordTXT = new JTextField();
		passwordTXT.setColumns(10);
		passwordTXT.setBounds(267, 337, 309, 47);
		panel.add(passwordTXT);
		
		JLabel lblNewLabel = new JLabel("Enter Passwords");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(355, 11, 228, 35);
		add(lblNewLabel);
	}
}
