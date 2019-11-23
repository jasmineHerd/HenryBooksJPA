/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Publisher;
import business.PublisherDB;
import business.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jasmine
 */
public class ViewInventoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/ViewInventory.jsp", msg= "";
        int storeid;
        Store st = null;
        List<Store> stores;
        
        
        List<Publisher> publishers;
        
        try{
            publishers = PublisherDB.getPublishers();
            if(publishers == null){
                msg = "No publishers returned from database";
            }else{
                 URL = "/ViewInventory.jsp";
                 request.getSession().setAttribute("publishers", publishers);
            }
        }catch(Exception e){
            msg = "Servlet error: " + e.getMessage() + "<br>";
        }
        
        
        
        
        
        
        
        
        
        try{
            storeid = Integer.parseInt(request.getParameter("storeid"));
            
            stores = (List<Store>) request.getSession().getAttribute("stores");
            for(Store store : stores){
                if(store.getStoreID() == storeid){
                    st = store;
                    request.getSession().setAttribute("store",store);
                }
            }
            if(st == null){
                msg = "Store id not found.<br>";
            }
        }catch(Exception e){
            msg = "Servlet error: "+ e.getMessage() + "<br>";
        }
        request.setAttribute("msg", msg);
        RequestDispatcher disp =
                getServletContext().getRequestDispatcher(URL);
        disp.forward(request,response);
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
