/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import PersistenceManager.AgentsUtility;
import PersistenceManager.PropertiesUtility;
import PersistenceManager.VendorsUtility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author Shane
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/Session"})
public class SessionServlet extends HttpServlet {
    
    static public final String agent_key = "agent", 
            vendors_key = "vendors",
            property_type = "property_type", 
            style_type = "style_types",
            garage_type = "garage_types"; 
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Subject currentUser = SecurityUtils.getSubject();

            org.apache.shiro.session.Session session = currentUser.getSession();
            if(currentUser.isAuthenticated()){
                if(session.getAttribute(agent_key) == null)
                    session.setAttribute(agent_key, AgentsUtility.getAgentDetails(currentUser.getPrincipal().toString()));
            }

            if(session.getAttribute(property_type) == null)
                session.setAttribute(property_type, PropertiesUtility.getPropertyTypes());

            if(session.getAttribute(style_type) == null)
                session.setAttribute(style_type, PropertiesUtility.getStyleTypes());

            if(session.getAttribute(garage_type) == null)
                session.setAttribute(garage_type, PropertiesUtility.getGarageTypes());
            
            if(session.getAttribute(vendors_key) == null)
                session.setAttribute(vendors_key, VendorsUtility.getAllVendors());

        }catch(Exception ex){}
        
        response.sendRedirect("Dashboard");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
