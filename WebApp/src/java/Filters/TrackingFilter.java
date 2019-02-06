/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import Business.TrackingCookieHelper;
import java.io.IOException;
import javax.servlet.*;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TrackingFilter implements Filter {  
    
    FilterConfig filterConfig = null;
    
    @Override
    public void init(FilterConfig args) throws ServletException {
        this.filterConfig = args;
    }  
    
    /* Secure Tracking Cookie - Additional Feature
        A encrypted tracking cookie, which keeps track of the users searches 
        and favourites.
    
        Encryptions used:
            AES 256 WITH GCM, this provides encryption along with anti tampering
            with the cookie data.
            
            PBKDF2WithHmacSHA256, to generate a secure key for encryption
            using a password and salt. 
    
            SHA256, Base64.
    
        If I had more time I could have created a more advanced recommendation
        system as well as a detection system if a cookie was deleted to restore
        it via finger printing.
    */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException { 

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        TrackingCookieHelper tch = new TrackingCookieHelper(req.getCookies());
        tch.addUserInformationURL(req.getQueryString());
        tch.encryptCookie();
        
        tch.getTracking_cookie().saveTo(req, res);
        
        chain.doFilter(request, response);
    }  
    
    @Override
    public void destroy() {
        //Cleanup.
    }  
}  