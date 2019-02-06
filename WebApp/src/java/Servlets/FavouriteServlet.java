/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Business.TrackingCookieHelper;
import Entities.Properties;
import PersistenceManager.PropertiesUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shane
 */
@WebServlet(name = "FavouriteServlet", urlPatterns = {"/Favourite"})
public class FavouriteServlet extends HttpServlet {

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
        
        final String add = "add", remove = "remove";
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        try {
            TrackingCookieHelper tch = new TrackingCookieHelper(req.getCookies());
            
            String action = request.getParameter("action");
            
            if(action != null){
                Integer propertyID = Integer.parseInt(request.getParameter("id"));
                if(action.equals(add))
                    tch.getUser_tracking().addFavourite(propertyID);
                else if(action.equals(remove))
                    tch.getUser_tracking().removeFavourite(propertyID);
            }
            
            ArrayList<Properties> user_favourites = new ArrayList<>();
            tch.getUser_tracking().getFavourites().forEach((i) -> {
                user_favourites.add(PropertiesUtility.getProperty(i));
            });
            tch.encryptCookie();
            tch.getTracking_cookie().saveTo(req, res);
            request.setAttribute("favourites", user_favourites);
            
            request.getRequestDispatcher("/WEB-INF/jsp/favourites.jsp").forward(request, response);
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
