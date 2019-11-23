/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Book;
import business.BookDB;
import business.Inventory;
import business.InventoryDB;
import business.Store;
import business.StoreDB;
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
public class AddBookServlet extends HttpServlet {

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
        
        String URL = "/StoreSelection.jsp",msg="";
        
        Book bk = new Book();
        
        try{
            bk.setBookID(request.getParameter("bookid"));
            bk.setTitle(request.getParameter("title"));
            bk.setAuthor(request.getParameter("author"));
            bk.setPubcd(request.getParameter("pubcd"));
            
            bk.setBooktype(request.getParameter("booktype"));
            bk.setPrice(Double.parseDouble(request.getParameter("price")));
            //validity check
            msg += bk.isValid();
        }catch(NumberFormatException e){
            msg += "Book price is not numeric.<br>";
        }
        
        try{
            if(msg.isEmpty()){
                boolean bookadded = BookDB.addBook(bk);
                if(bookadded){
                    //add inventory lines for book for each store
                    msg += "Book " + bk.getBookID()+ "  added! <br>";
                    List<Store>stores=
                            (List<Store>) request.getSession().getAttribute("stores");
                    if(stores==null){
                        throw new IOException("stores list not on session<br>");
                    }
                    for(Store store : stores){
                        Inventory inv = new Inventory();
                        inv.setBookID(bk.getBookID());
                        inv.setStoreID(store.getStoreID());
                        inv.setOnHand(0);
                        if(!InventoryDB.addBookInv(inv)){
                            msg += "Book" + bk.getBookID()+
                                    " not added for store: "+
                                    store.getStoreID();
                        }
                    }//for
                    stores = StoreDB.getStores();
                    request.getSession().setAttribute("stores", stores);
                    
                }//bookadded if
            }else{
                msg += "Book " + bk.getBookID()+ " not added to system. <br>";
            }
        }catch(Exception e){
             msg += "Servlet error: " + e.getMessage() + "<br>";
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
