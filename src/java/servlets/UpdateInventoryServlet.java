/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Inventory;
import business.Store;
import business.StoreDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasmine
 */
public class UpdateInventoryServlet extends HttpServlet {

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
        String URL = "/ViewInventory", msg = "";
        int onhand = 0;

        Store s;
        Inventory invitem;

        try {
            onhand = Integer.parseInt(request.getParameter("onhand"));
            if (onhand < 0) {
                msg += "On hand Entry must be >= zero";
            }
        } catch (Exception e) {
            msg += "On Hand Entry Was Illegal Or Missing<br>";
        }

        if (msg.isEmpty()) {
            try {
                s = (Store) request.getSession().getAttribute("store");
                invitem = (Inventory) request.getSession().getAttribute("inv");
                invitem.setOnHand(onhand);
                msg = StoreDB.persistStore(s) + "<br>";

                request.getSession().removeAttribute("inv");

            } catch (Exception e) {
                msg += "Servlet error: " + e.getMessage() + "<br>";
            }
        }
        request.setAttribute("msg", msg);
        RequestDispatcher disp
                = getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);

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
