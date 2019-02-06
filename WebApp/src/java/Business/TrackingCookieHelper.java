/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import PersistenceManager.HashGenerator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Security;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.Cookie;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.OperationMode;
import org.apache.shiro.crypto.PaddingScheme;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author Shane
 */
public class TrackingCookieHelper {
    
    private final static String CookieEncryptionPassword = "3d<=L95$ss+nH.UR";
    public final static String tracking_cookie_name = "LIT_T";
    private SimpleCookie tracking_cookie;
    
    private AesCipherService cipher;
    private final int keysize = 256;
    
    private UserInformation user_tracking;
    
    private SecretKeyFactory keyFactory;
    private SecretKey keySpec;
    
    public TrackingCookieHelper(){
        Security.addProvider(new BouncyCastleProvider());
        cipher = new AesCipherService();
        cipher.setKeySize(keysize);
        cipher.setMode(OperationMode.GCM);
        cipher.setPaddingScheme(PaddingScheme.NONE);
    }
    
    public TrackingCookieHelper(Cookie[] cookies){
        this();
        findCookie(cookies);
        user_tracking = new UserInformation(tracking_cookie == null ? 
                new HashGenerator().generateSalt() : 
                tracking_cookie.getName().split(tracking_cookie_name)[1]);
        generate256Key();
        decryptCookie();
    }
    
    public void findCookie(Cookie[] cookies){
        if (cookies != null){
            for(Cookie c : cookies){
                if(c.getName().contains(tracking_cookie_name)){
                    //tracking_cookie = SimpleCookie(c); ?
                    tracking_cookie = new SimpleCookie();
                    tracking_cookie.setName(c.getName());
                    tracking_cookie.setValue(c.getValue());
                    tracking_cookie.setDomain(c.getDomain());
                    tracking_cookie.setPath(c.getPath());
                    tracking_cookie.setMaxAge(c.getMaxAge());
                    tracking_cookie.setVersion(c.getVersion());
                    tracking_cookie.setSecure(c.getSecure());
                    tracking_cookie.setHttpOnly(c.isHttpOnly());
                    break;
                }
            }
        }
    }
    
     public AesCipherService getCipher() {
        return cipher;
    }

    public void setCipher(AesCipherService cipher) {
        this.cipher = cipher;
    }

    public UserInformation getUser_tracking() {
        return user_tracking;
    }

    public void setUser_tracking(UserInformation user_tracking) {
        this.user_tracking = user_tracking;
    }
    
    public SimpleCookie getTracking_cookie() {
        return tracking_cookie;
    }

    public void setTracking_cookie(SimpleCookie tracking_cookie) {
        this.tracking_cookie = tracking_cookie;
    }
    
    public void generate256Key(){
        if(user_tracking != null){
            try{
                keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                keySpec = keyFactory.generateSecret(
                        new PBEKeySpec(CookieEncryptionPassword.toCharArray(), 
                                user_tracking.getUserID().getBytes(), 10000, keysize));
            }catch(Exception ex){}
        }
    }
    
    public void decryptCookie(){
        if(tracking_cookie != null){
            try{
                user_tracking = (UserInformation) new ObjectInputStream(
                        new ByteArrayInputStream(cipher.decrypt(
                                Base64.decode(tracking_cookie.getValue()), 
                                keySpec.getEncoded()).getBytes())
                    ).readObject();
            }catch(Exception ex){}
        }else
            tracking_cookie = new SimpleCookie(tracking_cookie_name + user_tracking.getUserID());
    }
    
    public void encryptCookie(){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(user_tracking);
            so.flush();
            tracking_cookie.setValue(cipher.encrypt(bo.toByteArray(), 
                    keySpec.getEncoded()).toBase64());
        }catch (Exception e){}
    }
    
    public void expireOneYear(){
        if(tracking_cookie != null)
            tracking_cookie.setMaxAge(SimpleCookie.ONE_YEAR);
    }
    
    public void addUserInformationURL(String url){
        user_tracking.addURL(url);
    }
}
