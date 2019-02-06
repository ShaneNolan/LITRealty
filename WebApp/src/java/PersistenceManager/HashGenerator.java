package PersistenceManager;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;
 
/**
 * The Class HashGenerator.
 * @author Shane Nolan
 */
public class HashGenerator{

    /**
     * Generate salt.
     *
     * @return the string
     */
    public String generateSalt() {
    	return new BigInteger(250, new SecureRandom()).toString(32);
    }    
    
    /**
     * Generate hash.
     *
     * @param password the password
     * @return the string
     */
    public String generateHash(String password){
    	Sha256Hash hash = new Sha256Hash(password);
    	return hash.toHex();
    }
    
    /**
     * Salt hash password.
     *
     * @param password the password
     * @param salt the salt
     * @return the string
     */
    public String saltHashPassword(String password, String salt) {        
        Sha256Hash hash = new Sha256Hash(password, (new SimpleByteSource(salt)).getBytes(), 10000);        
        return hash.toHex();
    }
    
}