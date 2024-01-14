package PasswordManager.copy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;



public class MainWindow extends JFrame{
    private LoginWindow lw;
	private JFrame frame;
    private JTabbedPane tabbedPane;
    private PasswordView pv;
    private static PassManagerDAO pd;
    private totpLogin tl;
    private PassEnter pe;
   
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		tabbedPane = new JTabbedPane();
		lw = new LoginWindow();
		pv = new PasswordView();
		pe = new PassEnter();
		tl = new totpLogin();
		tabbedPane.addTab("Password Viewer", pv);
		tabbedPane.addTab("Enter Passwords", pe);
		
		 getContentPane().add(tabbedPane);
		initialize();
		addComponentsToFrame();	 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 839, 639);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	 private void addComponentsToFrame() {
	        getContentPane().add(tabbedPane, BorderLayout.CENTER);
	       
	    }
	 public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
												
						MainWindow window = new MainWindow();
						window.lw.setVisible(true);					
						window.frame.setVisible(false);
						 // Adding ActionListener for the login button using the loginFrame instance created in MainWindow
		                window.lw.getLoginBtn().addActionListener(e -> {
		                String username = window.lw.getUsername();
		                String password = window.lw.getPassword();
 
		                 Authentication  a = new Authentication();
		                boolean isAuthenticated = a.validateUserCredentials(username, password);
                     
		                window.lw.setVisible(true); // Show the login frame
		                if (isAuthenticated) {
		                	JOptionPane.showMessageDialog(null, "Successful Login!");
		                    
		                   
		                    window.tl.setVisible(true);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
		                }
		                window.lw.dispose();
		            });
		                //Action listener for the totp login window
		                window.tl.getABTN().addActionListener(e -> {
			                String tcode = window.tl.getTotp();
			                String username = window.tl.getUsername();
	 
			                 Authentication  a = new Authentication();
			                 pd = new PassManagerDAO();
		                        int userid = pd.getUserIDfromUsername(username);
				                String secret = pd.retrieve2FAkey(userid);
	                        boolean totpAuthenticated = a.verifyOTP(tcode, secret);
			                window.tl.setVisible(true); // Show the login frame
			                if (totpAuthenticated) {
			                	/*
			                	 * Upon successful authentication of the totp code, close the toto login window and
			                	 * then open the main application for the authenticated user.
			                	 */
			                	JOptionPane.showMessageDialog(null, "Successful Authentication!");
			                	window.tl.dispose();
			                	window.setVisible(true);
			                	window.pv.setUID(userid);
			                    window.pv.table_load(userid);
			                    byte[] key = PasswordView.getKey(userid);
			                    window.pv.setKey(key);
			                    
			                } else {
			                    JOptionPane.showMessageDialog(null, "Invalid TOTP code!");
			                }
			            });
		                		
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}
