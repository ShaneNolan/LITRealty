/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import PersistenceManager.AgentsUtility;
import PersistenceManager.PropertiesUtility;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Shane
 */
public class SecurityFilter implements Filter{
    FilterConfig filterConfig = null;
    
    @Override
    public void init(FilterConfig args) throws ServletException {
        this.filterConfig = args;
    }  
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException { 
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            if(SecurityUtils.getSubject().isAuthenticated())
                chain.doFilter(request, response);
            else
                httpResponse.sendRedirect("login.jsp");
        }catch(Exception ex){
            System.out.println(ex);
            httpResponse.sendRedirect("../error.jsp");
        }
    }  
    
    @Override
    public void destroy() {
        //Cleanup.
    } 
}
