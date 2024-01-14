package PasswordManager.copy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
public class totpReg extends JFrame {
	private JTextField usernameTXT;
	private JTextField codeTxt;



	public totpReg() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBackground(new Color(0, 102, 255));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(34, 92, 658, 105);
		panel.setBackground(new Color(102, 102, 153));
		panel.setBorder(new TitledBorder(null, "Two-Factor Authentication Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Secure Your Data! Please download the Google Authenticator App!");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(10, 27, 640, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter the code below into the app!");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(126, 59, 423, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Two Factor Authentication");
		lblNewLabel.setBounds(177, 32, 429, 42);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		getContentPane().add(lblNewLabel);
		

		usernameTXT = new JTextField();
		usernameTXT.setBounds(251, 211, 177, 35);
		getContentPane().add(usernameTXT);
		usernameTXT.setColumns(10);
		JButton Register = new JButton("Register");
		Register.setBounds(32, 348, 166, 59);
		Register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String username = usernameTXT.getText();
             //Retrieve a user's secret key
               PassManagerDAO pdao = new PassManagerDAO();
               int userid = pdao.getUserIDfromUsername(username);
               String secretKey = pdao.retrieve2FAkey(userid);
               codeTxt.setText(secretKey);             
            }
        });
		Register.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(Register);
		
		
		JLabel lblNewLabel_2 = new JLabel("Enter your username");
		lblNewLabel_2.setBounds(32, 208, 209, 35);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		getContentPane().add(lblNewLabel_2);
		
		JLabel code = new JLabel("Code:");
		code.setBounds(34, 286, 89, 35);
		code.setFont(new Font("Times New Roman", Font.BOLD, 30));
		code.setForeground(new Color(0, 0, 0));
		getContentPane().add(code);
		
		codeTxt = new JTextField();
		codeTxt.setBounds(133, 288, 220, 42);
		getContentPane().add(codeTxt);
		codeTxt.setColumns(10);
		
		JButton btnHelp = new JButton("Need Help?");
		btnHelp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Please download the Google Authenticator App. Once downloaded"
        + "click on plus and enter in the name of the password app (you choose it) and the code on the"
        + "app screen that appears after you enter your username."
        + "Then the Google Authenticator app should start producing time based codes for 2FA!");        
            }
        });
		btnHelp.setBounds(237, 348, 167, 59);
		btnHelp.setFont(new Font("Times New Roman", Font.BOLD, 20));
		getContentPane().add(btnHelp);
		
		
	}
}
