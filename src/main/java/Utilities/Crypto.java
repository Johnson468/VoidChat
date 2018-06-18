package Utilities;

/*
 * Ideally only the decrypt function needs to be here.
 * The encryption should be done client side via Javascript with the rooms key
 * The room should have a unique key? (Research needs to be done on best practice)
 * 
 */


import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public interface Crypto {

	public static String hash(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException { 
		  MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
	      byte[] result = mDigest.digest(s.getBytes());
	      StringBuffer sb = new StringBuffer();
	      for (int i = 0; i < result.length; i++) {
	          sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	      } 
	      return sb.toString();
	  }
	
	public static byte[] encrypt(String message, String key) {
		byte[] encrypted = null;
		try {
            String text = message;
            
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(text.getBytes());
            System.out.println(new String(encrypted));
            // decrypt the text
        } catch(Exception e) 
        {
            e.printStackTrace();
        }
		return encrypted;
    }
	
	
	public static String decrypt(byte[] message, String key) {
		String decrypted = null;
		try {
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
        decrypted = new String(cipher.doFinal(message));
        System.err.println(decrypted);
        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypted;
	}
	 
}
