/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Garagetypes;
import Entities.Properties;
import Entities.Propertytypes;
import Entities.Styles;
import PersistenceManager.PropertiesUtility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 *
 * @author Shane
 */
@WebServlet(name = "PropertyServlet", urlPatterns = {"/Property"})
public class PropertyServlet extends HttpServlet {

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
        try{
            Properties requested_property = PropertiesUtility.getProperty(
                    Integer.parseInt(request.getParameter("id")));
            
            if(requested_property == null)
                throw new Exception("Invalid ID");
            
            request.setAttribute("property", requested_property);
            
            Session session = SecurityUtils.getSubject().getSession();

            request.setAttribute(SessionServlet.property_type, ((HashMap<Integer, Propertytypes>) 
                    session.getAttribute(SessionServlet.property_type))
                    .get(requested_property.getTypeId()));
            
            request.setAttribute(SessionServlet.style_type, ((HashMap<Integer, Styles>) 
                    session.getAttribute(SessionServlet.style_type))
                    .get(requested_property.getStyleId()));
            
            request.setAttribute(SessionServlet.garage_type, ((HashMap<Integer, Garagetypes>) 
                    session.getAttribute(SessionServlet.garage_type))
                    .get(requested_property.getGarageId()));
            
            ArrayList<String> images = new ArrayList<>();
            File folder = new File(request.getServletContext().getRealPath("/") 
                    + "/assets/img/properties/large/" + requested_property.getListingNum() + "/");
            for (File file : folder.listFiles()) {
                    if (file.isFile() && file.getName().toUpperCase().endsWith(".JPG"))
                        images.add(file.getName().toUpperCase());
            }
            request.setAttribute("images", images);
            
            request.getRequestDispatcher("/WEB-INF/jsp/property.jsp").forward(request, response);
        }catch(Exception ex){
            System.out.println(ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
