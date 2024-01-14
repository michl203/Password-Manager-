package PasswordManager.copy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class PassManagerDAO {
// Holds the JDBC connection object
private Connection con;
private PreparedStatement prepareStatement;
private ResultSet rs;

//Method #1 Create Connection to MySQL Database
public static Connection getConnection() {
    Connection con = null;
	String  dbUrl = "Enter your URL here";
	String uName ="Enter your MySQL username here";
	String  password = "Enter your MySQL password here";

	    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    con = DriverManager.getConnection(dbUrl, uName, password);
		} catch (ClassNotFoundException | SQLException ex) {
	    ex.printStackTrace();}
         return con;
   }  

//Constructor for PassManagerDAO that ensures that a connection is created with each DAO object
public PassManagerDAO() {
	this.con= getConnection();
  }

//Method #2 (for inserting a new users credentials into the user_credential table)
public boolean insertManagerUser(String username, byte[] p, byte[] salt) {
    try {
       

        String insertSql = "INSERT INTO user_credentials (username, user_password, user_salt) VALUES (?,?,?) ";
         prepareStatement = con.prepareStatement(insertSql);
        prepareStatement.setString(1, username);
        prepareStatement.setBytes(2, p); 
        prepareStatement.setBytes(3, salt); 
        
        prepareStatement.executeUpdate();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error. You were not registered. Try Again!");
        e.printStackTrace();
        return false;
    }finally {
   	closeResources(null, prepareStatement, null);
    }
}

//Method #3 for getting the user id based off of a username
public int getUserIDfromUsername(String username) {
	try {
	String retrieveID = "SELECT user_id FROM user_credentials WHERE username =?";
	prepareStatement = con.prepareStatement(retrieveID);
	prepareStatement.setString(1, username);
	 rs = prepareStatement.executeQuery();
	
	 if (rs.next()) {
         return rs.getInt("user_id"); // Return the retrieved UserID
     }
	 } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "User ID was not found!");
	        e.printStackTrace();
	 }finally {
	 closeResources(null, prepareStatement, rs);
     }
	  return -1; // -1 is an error message.
}

//Method #4 getting the salt for a user's password (for login purposes)
public byte[] getUserSalt(String username) {
	try {
	String retrieveID = "SELECT user_salt FROM user_credentials WHERE username =?";
	PreparedStatement ps = con.prepareStatement(retrieveID);
	ps.setString(1, username);
	ResultSet rs = ps.executeQuery();
	
	 if (rs.next()) {
         return rs.getBytes("user_salt"); // Return the retrieved UserID
     }
	 } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "User ID was not found!");
	        e.printStackTrace();
	 }finally {
     closeResources(null, prepareStatement, rs);
	 }
	  return null; // -1 is an error message.
}

//Method #5 getting the password hash for a user.
public byte[] getUserPasswordHashFromDB(int userID) {
    try {
        String selectSql = "SELECT user_password FROM user_credentials WHERE user_id = ?";
        prepareStatement = con.prepareStatement(selectSql);
        prepareStatement.setInt(1, userID);

        rs = prepareStatement.executeQuery();

        if (rs.next()) {
            return rs.getBytes("user_password"); // Return the retrieved PasswordHash
        }
    }catch (SQLException e) {
 	JOptionPane.showMessageDialog(null, "The password was not found!!" );
    e.printStackTrace();
    }finally {
	closeResources(null, prepareStatement, rs);
	}

    return null; // Return null if user not found or error in password retrieval
}

//Method #6 for getting the username based on user id
public String getUsernameByID(int userID) {
	 try {
	        String selectSql = "SELECT username FROM user_credentials WHERE user_id = ?";
	        prepareStatement = con.prepareStatement(selectSql);
	        prepareStatement.setInt(1, userID);

	        rs = prepareStatement.executeQuery();

	        if (rs.next()) {
	            return rs.getString("username"); // Return the retrieved PasswordHash
	        }
	    } catch (SQLException e) {
	 	   JOptionPane.showMessageDialog(null, "The username was not found!!" );
	        e.printStackTrace();
	    }finally {
	    closeResources(null, prepareStatement, rs);
	    }

	    return null; // Return null if user not found or error in password retrieval
}

//Method #7 insert the 2FA keys into the DB
public String insert2FAkey( int userID, String secretkey) {
	 try {
	        String insertSql = "INSERT INTO twofa (user_id,user_key) VALUES (?, ?)";
	        prepareStatement = con.prepareStatement(insertSql);
	        prepareStatement.setInt(1, userID);
	        prepareStatement.setString(2, secretkey);
	        prepareStatement.executeUpdate();

	        
	    } catch (SQLException e) {
	 	   JOptionPane.showMessageDialog(null, "The username was not found!!" );
	        e.printStackTrace();
	    }finally {
	    	closeResources(null, prepareStatement, null);
	    }

	    return null; // Return null if user not found or error in password retrieval
}

//Method #8 for retrieving the 2FA keys from the database
public String retrieve2FAkey(int userid) {
	try {
        String selectSql = "SELECT user_key FROM twofa WHERE user_id =?";
         prepareStatement = con.prepareStatement(selectSql);
         prepareStatement.setInt(1, userid);
         rs =  prepareStatement.executeQuery();
         if (rs.next()) {
         return rs.getString("user_key");
         }
        
    } catch (SQLException e) {
 	   JOptionPane.showMessageDialog(null, "Error! Can't get user id!" );
        e.printStackTrace();
    } finally {
	closeResources(null, prepareStatement, rs);
    }
     return null;
}
//Method #9 for inserting the AES key for symmetric encryption
public byte[] insertKey( int userID, byte[] encryptkey) {
	 try {
	        String insertSql = "INSERT INTO user_key (user_id,user_key) VALUES (?, ?)";
	        prepareStatement = con.prepareStatement(insertSql);
	        prepareStatement.setInt(1, userID);
	        prepareStatement.setBytes(2, encryptkey);
	        prepareStatement.executeUpdate();

	        
	    } catch (SQLException e) {
	 	   JOptionPane.showMessageDialog(null, "Error! Can't insert key!" );
	        e.printStackTrace();
	    } finally {
	    closeResources(null, prepareStatement, null);
		}

	    return null; // Return null if user not found or error in password retrieval
}
//Method #10 for retrieving the AES key.
public byte[] retrievekey(int userid) {
	try {
        String selectSql = "SELECT user_key FROM user_key WHERE user_id =?";
        prepareStatement = con.prepareStatement(selectSql);
        prepareStatement.setInt(1, userid);
       ResultSet rs =  prepareStatement.executeQuery();
       if (rs.next()) {
           return rs.getBytes("user_key");
       }
        
    } catch (SQLException e) {
 	   JOptionPane.showMessageDialog(null, "Error! Can't get key!" );
        e.printStackTrace();
    }finally {
	closeResources(null, prepareStatement, rs);
	}
     return null;
}
//Method #11 for inserting the passwords for a user along with the relevant info (website, username).
public boolean insertPasswords(int userid,String website, String username, String password) {
	 try {
	        String insertSql = "INSERT INTO passwords (user_ID ,website, w_username,w_password) VALUES (?,?, ?, ?)";
	        prepareStatement = con.prepareStatement(insertSql);
	        prepareStatement.setInt(1, userid);
	        prepareStatement.setString(2, website);
	        prepareStatement.setString(3, username);
	        prepareStatement.setString(4, password);
	        prepareStatement.executeUpdate();
            return true;
	        
	    } catch (SQLException e) {
	 	   JOptionPane.showMessageDialog(null, "Error! Can't insert passwords!" );
	       e.printStackTrace();
	       return false;
	    }finally {
	    closeResources(null, prepareStatement, null);
	    }

}
//Method #12 for retrieving the passwords from the database.
public String retrievePass(int passid) {
	try {
        String selectSql = "Select w_password FROM passwords WHERE password_id = ?";
        prepareStatement = con.prepareStatement(selectSql);
        prepareStatement.setInt(1, passid);
        ResultSet rs = prepareStatement.executeQuery();
        
        if (rs.next()) {
            return rs.getString("w_password");
        }
        
    } catch (SQLException e) {
 	   JOptionPane.showMessageDialog(null, "Error! Can't retrieve passwords!" );
        e.printStackTrace();
    }   finally {
        closeResources(null, prepareStatement, rs);
    }
	return null;

}

//Method #13 for updating user passwords
public void updatePassword(String newPassword, int passID) {
	try {
        String selectSql = "UPDATE passwords SET w_password = ? WHERE password_id = ?";
        prepareStatement = con.prepareStatement(selectSql);
        prepareStatement.setString(1, newPassword);
        prepareStatement.setInt(2, passID);
        prepareStatement.executeUpdate();
        
        
        
    } catch (SQLException e) {
 	   JOptionPane.showMessageDialog(null, "Error! Could not update password!" );
        e.printStackTrace();
    }  finally {
    closeResources(null, prepareStatement, null); // Call the method to close resources
}
	
}

// Method #14 fro closing the database resources to ensure that there are no conflicts.
public void closeResources(Connection con, PreparedStatement stmt, ResultSet rs) {
    try {
        if (rs != null) {
            rs.close();
        }
    } catch (SQLException e) {
      System.out.println(e); 
    }

    try {
        if (stmt != null) {
            stmt.close();
        }
    } catch (SQLException e) {
    	System.out.println(e); 
    }

    try {
        if (con != null) {
            con.close();
        }
    } catch (SQLException e) {
       System.out.println(e); 
    }
}
}






