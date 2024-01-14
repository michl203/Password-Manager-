package PasswordManager.copy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
public class totpLogin extends JFrame {
	private JTextField totpTXT;
	private JButton authenticateBtn;
	private JTextField usernameTXT2;
	public totpLogin() {
		setBackground(new Color(102, 102, 102));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBorder(new TitledBorder(null, "Two Factor Authentication Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(64, 52, 559, 383);
		add(panel);
		panel.setLayout(null);
		
		totpTXT = new JTextField();
		totpTXT.setBounds(98, 175, 351, 52);
		panel.add(totpTXT);
		totpTXT.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the TOTP code you see in the app");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(59, 55, 435, 24);
		panel.add(lblNewLabel_1);
		
	    authenticateBtn = new JButton("Authenticate");
		authenticateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             Authentication a = new Authentication();
             //if
              
            }
        });
		authenticateBtn.setFont(new Font("Times New Roman", Font.BOLD, 35));
		authenticateBtn.setBounds(98, 238, 351, 63);
		panel.add(authenticateBtn);
		
		JLabel lblNewLabel_2 = new JLabel("Enter your username again");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(84, 111, 269, 29);
		panel.add(lblNewLabel_2);
		
		usernameTXT2 = new JTextField();
		usernameTXT2.setBounds(363, 111, 186, 29);
		panel.add(usernameTXT2);
		usernameTXT2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Authentication");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(219, 11, 235, 33);
		add(lblNewLabel);
	}
	public JButton getABTN() {
		return authenticateBtn;
	}
	public String getTotp() {
		return totpTXT.getText();
	}
	public String getUsername() {
		return usernameTXT2.getText();
	}
}
