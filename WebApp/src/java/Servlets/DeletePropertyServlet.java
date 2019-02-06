/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Agents;
import Entities.Properties;
import PersistenceManager.PropertiesUtility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author Shane
 */
@WebServlet(name = "DeletePropertySerlvet", urlPatterns = {"/DeleteProperty"})
public class DeletePropertyServlet extends HttpServlet {

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
        String address = "";
        try{
            String action = request.getParameter("action");
            Properties property = PropertiesUtility.getProperty(
                        Integer.parseInt(request.getParameter("id")));
            
            if(action != null && action.equals("delete")){
                if(!property.getAgentId().equals(
                        ((Agents)SecurityUtils.getSubject().getSession()
                                .getAttribute(SessionServlet.agent_key))
                                .getAgentId()))
                    throw new Exception("Unauthorised Request");
                
                PropertiesUtility.deleteProperty(property);
                request.setAttribute("message", "Property " + property.getListingNum() + " was deleted successfully.");
                address = "Dashboard";
            }else{
                request.setAttribute("property", property);
                address = "/WEB-INF/jsp/dashboard/deleteproperty.jsp";
            }
        }catch(Exception ex){
            System.out.println(ex.toString());
            address = "error.jsp";
        }
        request.getRequestDispatcher(address).forward(request, response);
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
