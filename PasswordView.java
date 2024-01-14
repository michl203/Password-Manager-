package PasswordManager.copy;
import javax.swing.*;


import java.awt.Color;
import java.awt.Font;
import net.proteanit.sql.DbUtils;
import java.lang.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class PasswordView extends JPanel {
	
	private JTextField passwordTXT;
	private JTextField passidTXT;

	private JTable table;
	private byte[] key;
	private int user_id;
	public PasswordView() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 68, 975, 541);
		panel.setBackground(new Color(102, 102, 153));
		add(panel);
		panel.setLayout(null);
		
		JLabel PasswordID = new JLabel("PasswordID");
		PasswordID.setBounds(57, 44, 126, 40);
		PasswordID.setForeground(new Color(0, 0, 0));
		PasswordID.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(PasswordID);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(57, 123, 126, 40);
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblPassword);
		
		passwordTXT = new JTextField();
		passwordTXT.setBounds(193, 125, 134, 40);
		panel.add(passwordTXT);
		passwordTXT.setColumns(10);
		
		passidTXT = new JTextField();
		passidTXT.setBounds(193, 49, 70, 34);
		passidTXT.setColumns(10);
		panel.add(passidTXT);
		
		JButton btnViewPassword = new JButton("View Password");
		btnViewPassword.setBounds(131, 267, 196, 60);
		btnViewPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnViewPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   PassManagerDAO pw = new PassManagerDAO();
			  String passid = passidTXT.getText();
			  int id = Integer.parseInt(passid);
			  String pass = pw.retrievePass(id);
			  SecretKey sk = new SecretKeySpec(key, 0, key.length, "AES");
			  String passd =  Security.decrypt(pass, sk);
			  
			  
			  passwordTXT.setText(passd);
			  
			}
		});
		panel.add(btnViewPassword);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(131, 417, 196, 54);
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			passwordTXT.setText("");
			passidTXT.setText("");
			  
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(btnClear);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(466, 33, 490, 463);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 490, 463);
		panel_2.add(scrollPane);
		scrollPane.setViewportView(table);
		
		
		JButton updateBTN = new JButton("Update ");
		updateBTN.setBounds(131, 186, 196, 60);
		updateBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  
			PassManagerDAO pw = new PassManagerDAO();
			String passid = passidTXT.getText();
			int id = Integer.parseInt(passid);
		    SecretKey k = new SecretKeySpec(key , 0, key.length, "AES");
		    String updatedPass = passwordTXT.getText();
			String newPassword =Security.encrypt(updatedPass, k);
			pw.updatePassword(newPassword, id);
		   
			  		  
			}
		});
		updateBTN.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(updateBTN);
		
		JLabel lblNewLabel = new JLabel("Passwords");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(662, 11, 112, 14);
		panel.add(lblNewLabel);
		
		JButton btnResetDB = new JButton("Reset Table");
		btnResetDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			user_id = getUID();
			table_load(user_id);
			}
		});
		btnResetDB.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnResetDB.setBounds(131, 338, 196, 60);
		panel.add(btnResetDB);
		
	
	    
	    JLabel Title = new JLabel("Password Viewer");
	    Title.setBounds(388, 25, 262, 32);
	    Title.setFont(new Font("Times New Roman", Font.BOLD, 35));
	    add(Title);	
	}
	
	  public void table_load(int user_ID) {
	       
          try {
         
          Connection connection = PassManagerDAO.getConnection();
          String selectSQL = "SELECT password_id as 'ID', website as 'Website', w_username as 'Username'"
          + ", w_password as 'Password' FROM passwords WHERE user_ID = ?";
          
          PreparedStatement prepareStatement = connection.prepareStatement(selectSQL);
          prepareStatement.setInt(1, user_ID );
          ResultSet rs = prepareStatement.executeQuery();
         
         
          table.setModel(DbUtils.resultSetToTableModel(rs));
         
           } catch (SQLException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this, "Error loading data from the database.");
        }
	  }
          
      public static byte[] getKey(int userid) {
    	  PassManagerDAO pd = new PassManagerDAO();
    	  byte[] key = pd.retrievekey(userid);
    	  return key;
      }
      
      public static String getPass(int userid) {
    	  PassManagerDAO pd = new PassManagerDAO();
    	  String pass = pd.retrievePass(userid);
    	  return pass;
      }
      
      public void setKey(byte[] key) {
    	 this.key = key;
      }
      
      public int getUID() {
    	 return user_id;
      }
      
      public void setUID(int id) {
    	  user_id = id;
      }
  }
	 

