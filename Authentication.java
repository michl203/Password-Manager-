package PasswordManager.copy;
import java.security.*;
import com.amdelamar.jotp.OTP;
import com.amdelamar.jotp.type.Type;
import java.security.SecureRandom;


public class Authentication {
private PassManagerDAO pdao;
//Basic constructor
public Authentication() {
	pdao = new PassManagerDAO();
}
//Method to valid user credentials during login on LoginWindow
public boolean validateUserCredentials(String username, String password) {
    int userID = pdao.getUserIDfromUsername(username);
      
    byte[] salt = pdao.getUserSalt(username);
    if (userID != -1) {
    byte[] storedPasswordHash = pdao.getUserPasswordHashFromDB(userID);
    // Hash the entered password (with the associated salt) since the stores password is hashed with the salt!
    byte[] enteredPassWordHash = Security.hashPasswordWithSalt(password, salt);
    if (MessageDigest.isEqual(storedPasswordHash, enteredPassWordHash)) {
      return true;
       }
    }

    return false; // User not found or password retrieval error
}


/*
 * NOTE THE BELOW METHODS IMPLEMENT FUNCTIONALITY FROM AUSTIN DELMAR (GitHub Link: https://github.com/amdelamar/jotp)
 * I am implementing his JOTP utilty which allows you to generate time based one time passwords
 * The ultimate purpose is to have users use 2FA when logging into this application. 
 */

//Method to generate one time password
public String generateOTP(String secret){
	// Generate a 6-digit TOTP that changes every 30 seconds
	try {
    	String hexTime = OTP.timeInHex(System.currentTimeMillis(), 30);
    	return OTP.create(secret, hexTime, 6, Type.TOTP); 
    }
    catch(Exception e) {
    e.printStackTrace();	
    }
    return null;
}
//Method to verify otp.
public boolean verifyOTP(String userEnteredCode, String secret) {
    //Implemented the OTP.verify method that comes with Austin Delmar's TOTP utility.
	try {
        String hexTime = OTP.timeInHex(System.currentTimeMillis(), 30);
        return OTP.verify(secret, hexTime, userEnteredCode, 6, Type.TOTP);
    } catch (Exception e) {
        e.printStackTrace();
        return false; 
    }
}


//Method to generate a secure string secret key using base 32 to align with google authenticator application requirements
//Google authenticator requires a 16 byte (16 character) base-32 character secret key to work!
public static String generateSecretKey() {
    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder();
    String BASE32_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    for (int i = 0; i < 16; i++) { 
        int randomIndex = random.nextInt(BASE32_CHARACTERS.length());
        sb.append(BASE32_CHARACTERS.charAt(randomIndex));
    }

    return sb.toString();
}



	
	


}
