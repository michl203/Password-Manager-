package PasswordManager.copy;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;


public class Security {

//Method to generate a secure random key for AES encryption/ decryption
public static byte[]  generateKey() {
	SecureRandom secureRandom = new SecureRandom();
	byte[] key = new byte[32]; // 32 bytes = 256 bits for AES-256
    secureRandom.nextBytes(key);
    return key;
}


//Method to encrypt a user's password (non login) using AES-256.
public static String encrypt(String strToEnc, Key secretKey) {
    try {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(strToEnc.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    } catch (Exception e) {
    e.printStackTrace();
    return null;
    }
}


//Method to decrypt a user's password (non login)
public static String decrypt(String strToDec, Key secretkey ) {
	try {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretkey);
		return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));
		
	}
	catch(Exception e) {
		
	}
	return null;
}
//Method to generate the salt for user's password
public static  byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16]; 
    random.nextBytes(salt);
    return salt;
}

/*Method to hash user's password (login) using SHA-256. 
*  This method adds a salt to the users unhashed password, and then hashes the password combined with the salt.
*/
public static byte[] hashPasswordWithSalt(String password, byte[] salt) {
    try {
        // Concatenate salt with the password
        byte[] passwordBytes = password.getBytes();
        byte[] saltedPassword = new byte[salt.length + passwordBytes.length];
        System.arraycopy(passwordBytes, 0, saltedPassword, 0, passwordBytes.length);
        System.arraycopy(salt, 0, saltedPassword, passwordBytes.length, salt.length);

        // Create a MessageDigest instance for hashing using SHA-256.
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Add salted password bytes to the digest
        md.update(saltedPassword);

        // Return the hashed password as a byte array
        return md.digest();
    } catch (NoSuchAlgorithmException e) {
        // Handle exceptions related to unavailable algorithms
        e.printStackTrace();
        return null; // Return null if an exception occurs
    }
}


    
}       
        





