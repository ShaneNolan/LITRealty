/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entities.Vendors;
import PersistenceManager.VendorsUtility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shane
 */
@WebServlet(name = "EditVendor", urlPatterns = {"/EditVendor"})
public class EditVendor extends HttpServlet {

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
            String action = request.getParameter("action");
            Vendors vendor = VendorsUtility.getVendorById(
                        Integer.parseInt(request.getParameter("id")));
            
            if(action != null && action.equals("edit")){
                vendor.setAddress(request.getParameter("vendoraddress"));
                vendor.setEmail(request.getParameter("vendoremail"));
                vendor.setPhone(request.getParameter("vendorphone"));
                vendor.setName(request.getParameter("vendorname"));
                
                VendorsUtility.updateVendor(vendor);
                request.setAttribute("message", "Vendor " + vendor.getName() + " was updated successfully.");
                request.getRequestDispatcher("Dashboard")
                        .forward(request, response);
            }else{
                request.setAttribute("vendor", vendor);
                request.getRequestDispatcher("/WEB-INF/jsp/dashboard/editvendor.jsp")
                        .forward(request, response);
            }
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
