/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.TrackingCookieHelper;
import Entities.Properties;
import PersistenceManager.PropertiesUtility;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shane
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {

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
            TrackingCookieHelper tch = new TrackingCookieHelper(request.getCookies());
            HashMap<String, Integer> searchedURLS = tch.getUser_tracking().getSearchedURLs();
            if(searchedURLS.size() > 0){
                String mostSearchedURL = Collections.max(searchedURLS.entrySet(), 
                        Map.Entry.comparingByValue()).getKey();
                
                String[] prices = mostSearchedURL.split("price=")[1].split("to");
                int[] intprices = Arrays.asList(prices).stream()
                        .mapToInt(Integer::parseInt).toArray();
                int errorMargin = intprices[0] / 4;

                prices[0] = Integer.toString(intprices[0] - errorMargin);
                prices[1] = Integer.toString(intprices[1] + errorMargin);

                List<Properties> recommended_properties = PropertiesUtility
                        .searchProperties(mostSearchedURL.split("place=")[1].split("&")[0], 
                                prices);

                Collections.shuffle(recommended_properties);
                request.setAttribute("recommended_properties", 
                        recommended_properties.subList(0, 
                                recommended_properties.size() > 4 ? 4 : recommended_properties.size()));
            }
            
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
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
