package PasswordManager.copy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;


public class Registration extends JFrame {
	//Fields (Attributes)
	private PassManagerDAO pmd;
	private JTextField newUsernameTXT;
	private JPasswordField newPasswordTXT;
	private String username;
	private String password;
	
	private byte[] salt;
	private totpReg totpPanel; 
	public Registration(totpReg totpPanel) {
		this.totpPanel = totpPanel;
		getContentPane().setBackground(new Color(102, 102, 204));
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(106, 81, 516, 290);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel newUsername = new JLabel("New Username");
		newUsername.setFont(new Font("Times New Roman", Font.BOLD, 30));
		newUsername.setBounds(35, 54, 219, 41);
		panel.add(newUsername);
		
		JLabel newPassword = new JLabel("New Password");
		newPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
		newPassword.setBounds(35, 121, 208, 41);
		panel.add(newPassword);
		
		newUsernameTXT = new JTextField();
		newUsernameTXT.setBounds(262, 58, 226, 45);
		panel.add(newUsernameTXT);
		newUsernameTXT.setColumns(10);
		
		newPasswordTXT = new JPasswordField();
		newPasswordTXT.setBounds(262, 125, 226, 45);
		panel.add(newPasswordTXT);
		
		JButton btnRegistration = new JButton("Register New Account");
		btnRegistration.setFont(new Font("Times New Roman", Font.BOLD, 19));
		 btnRegistration.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	             //Create instance of the DAO class
	             pmd = new PassManagerDAO(); 
	             
	             //Get username from textfield (making sure it is not blank!)
	             if(newUsernameTXT.getText().equals("")) {
	             JOptionPane.showMessageDialog(null, "Please enter a username!");
	             }else {
	             username = newUsernameTXT.getText();
	             }
	             //generate the salt for this new user
	             salt = Security.generateSalt(); 
                 //Get the password from password field (making sure it is not blank!)
	             if(newPasswordTXT.getPassword().equals("") || newPasswordTXT.getPassword().length < 8) {
	             JOptionPane.showMessageDialog(null, "Please enter a password that is more than 8 characters long!");	
	             }else {
	             char[] passwordChars = newPasswordTXT.getPassword();
	             password = new String(passwordChars);	 
	             }
	             // Hash the user's password with the generated salt
	             byte[] hashedPassword = Security.hashPasswordWithSalt(password, salt); 
	             boolean InsertManagerUser = pmd.insertManagerUser(username, hashedPassword,salt );
	             //Store the new user's credentials into the MySQl Database
	             
	            if(InsertManagerUser){
	            	  //Create the totp secret key for the new user	            
		             String secretkey =Authentication.generateSecretKey();
		             //get the userid for the user
		             String username =  newUsernameTXT.getText();
		             int userid = pmd.getUserIDfromUsername(username);
		             pmd.insert2FAkey(userid,secretkey);
		             //get a derived key for encryption/decryption for user
		             byte[] key = Security.generateKey();
		             pmd.insertKey(userid, key);
	            	 JOptionPane.showMessageDialog(null, "You have been registered successfully!");
	            	 //Open 2FA registration panel
			            totpReg  totpPanel = new totpReg();
			             totpPanel.setVisible(true); // Show the totpReg panel
			            setVisible(false);
	             }else {
	            	JOptionPane.showMessageDialog(null, "You have not been registered! Try again!");
	            }
	             
	            }
	        }); 
		btnRegistration.setBounds(262, 207, 226, 55);
		panel.add(btnRegistration);
		
		JLabel lblNewLabel = new JLabel("Thanks for using this app!");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblNewLabel.setBounds(35, 213, 197, 33);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Registration Window");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(193, 30, 392, 37);
		getContentPane().add(lblNewLabel_1);
	}
	
	//Getter methods
	public String getUsername() {
	return username;
	}

}
