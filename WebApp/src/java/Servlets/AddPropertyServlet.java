/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.HelperFunctions;
import Entities.Agents;
import Entities.Garagetypes;
import Entities.Properties;
import Entities.Propertytypes;
import Entities.Styles;
import PersistenceManager.PropertiesUtility;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 *
 * @author Shane
 */
@WebServlet(name = "AddPropertyServlet", urlPatterns = {"/AddProperty"})
@MultipartConfig
public class AddPropertyServlet extends HttpServlet {

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
        final String IMAGE_EXTENSION = ".JPG";
        
        try{
            String action = request.getParameter("action");
            if(action != null && action.equals("add")){
                if(!HelperFunctions.checkNameRequests(request))
                    throw new Exception("Invalid Input");
                
                List<Part> images = request.getParts().stream()
                        .filter(part -> "file".equals(part.getName()))
                        .collect(Collectors.toList());
                
                if(images.isEmpty())
                    throw new Exception("Invalid Input");
                
                String listingNumber = request.getParameter("listing");
                HelperFunctions.saveImages(request.getServletContext()
                        .getRealPath("/assets/img/properties/large/"), 
                        IMAGE_EXTENSION, listingNumber, images);
                
                Properties property = new Properties(request.getParameter("street"),
                        request.getParameter("city"), Integer.parseInt(listingNumber),
                        Integer.parseInt(request.getParameter("style")),
                        Integer.parseInt(request.getParameter("type")),
                        Integer.parseInt(request.getParameter("bedrooms")), 
                        Float.parseFloat(request.getParameter("bathrooms")), 
                        Integer.parseInt(request.getParameter("squarefeet")),
                        request.getParameter("ber"), 
                        request.getParameter("description"), 
                        Short.parseShort(request.getParameter("garagesize")), 
                        Integer.parseInt(request.getParameter("gtype")), 
                        Double.parseDouble(request.getParameter("price")),
                        ((Agents)SecurityUtils.getSubject().getSession()
                                .getAttribute(SessionServlet.agent_key))
                                .getAgentId(),
                        Integer.parseInt(request.getParameter("vendor")),
                        HelperFunctions.convertLocalDateToDate(LocalDate.now()),
                        request.getParameter("lotsize"), listingNumber + IMAGE_EXTENSION);
                
                PropertiesUtility.insertProperty(property);
                request.setAttribute("message", "Property " + listingNumber + " was created successfully.");
                address = "Dashboard";
           }else{
                Session session = SecurityUtils.getSubject().getSession();
                
                request.setAttribute("garagetypes", new ArrayList<>(((HashMap<Integer, Garagetypes>) 
                    session.getAttribute(SessionServlet.garage_type)).values()));
                request.setAttribute("styletypes", new ArrayList<>(((HashMap<Integer, Styles>) 
                    session.getAttribute(SessionServlet.style_type)).values()));
                request.setAttribute("propertytypes", new ArrayList<>(((HashMap<Integer, Propertytypes>) 
                    session.getAttribute(SessionServlet.property_type)).values()));
                address = "/WEB-INF/jsp/dashboard/addproperty.jsp";
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
