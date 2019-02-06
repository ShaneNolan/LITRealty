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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
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
@WebServlet(name = "EditPropertyServlet", urlPatterns = {"/EditProperty"})
@MultipartConfig
public class EditPropertyServlet extends HttpServlet {

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
            Properties property = PropertiesUtility.getProperty(
                        Integer.parseInt(request.getParameter("id")));
            
            Session session = SecurityUtils.getSubject().getSession();
            if(action != null && action.equals("edit")){
                if(!property.getAgentId().equals(
                        ((Agents)session.getAttribute(SessionServlet.agent_key))
                                .getAgentId()))
                    throw new Exception("Unauthorised Request");
                
                if(!HelperFunctions.checkNameRequests(request))
                    throw new Exception("Invalid Input");
                
                List<Part> images = request.getParts().stream()
                        .filter(part -> "file".equals(part.getName()))
                        .collect(Collectors.toList());
                
                String listingNumber = request.getParameter("listing");
                if(!images.isEmpty()){
                    String baseDir = request.getServletContext()
                            .getRealPath("/assets/img/properties/large/");
                    
                    HelperFunctions.deleteFolder(new File(baseDir 
                            + File.separator + property.getListingNum()));
                    HelperFunctions.saveImages(baseDir, IMAGE_EXTENSION, 
                            listingNumber, images);
                }
                
                //this part was so tedious to code :))
                property.setBathrooms(Float.parseFloat(request.getParameter("bathrooms")));
                property.setBedrooms(Integer.parseInt(request.getParameter("bedrooms")));
                property.setBerRating(request.getParameter("ber"));
                property.setCity(request.getParameter("city"));
                property.setStreet(request.getParameter("street"));
                property.setDescription(request.getParameter("description"));
                property.setGarageId(Integer.parseInt(request.getParameter("gtype")));
                property.setGaragesize(Short.parseShort(request.getParameter("garagesize")));
                property.setVendorId(Integer.parseInt(request.getParameter("vendor")));
                property.setPhoto(listingNumber + IMAGE_EXTENSION);
                //property.setListingNum(Integer.parseInt(request.getParameter("vendor")));
                property.setStyleId(Integer.parseInt(request.getParameter("style")));
                property.setTypeId(Integer.parseInt(request.getParameter("type")));
                property.setSquarefeet(Integer.parseInt(request.getParameter("squarefeet")));
                property.setDescription(request.getParameter("description"));
                property.setLotsize(request.getParameter("lotsize"));
                property.setPrice(Double.parseDouble(request.getParameter("price")));
                
                PropertiesUtility.updateProperty(property);
                request.setAttribute("message", "Property " + listingNumber + " has been updated successfully.");
                address = "Dashboard";
            }else{
                request.setAttribute("garagetypes", new ArrayList<>(((HashMap<Integer, Garagetypes>) 
                    session.getAttribute(SessionServlet.garage_type)).values()));
                request.setAttribute("styletypes", new ArrayList<>(((HashMap<Integer, Styles>) 
                    session.getAttribute(SessionServlet.style_type)).values()));
                request.setAttribute("propertytypes", new ArrayList<>(((HashMap<Integer, Propertytypes>) 
                    session.getAttribute(SessionServlet.property_type)).values()));
                
                request.setAttribute("property", property);
                address = "/WEB-INF/jsp/dashboard/editproperty.jsp";
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
