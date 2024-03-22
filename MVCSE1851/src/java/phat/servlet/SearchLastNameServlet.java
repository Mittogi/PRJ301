/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phat.registration.RegistrationDAO;
import phat.registration.RegistrationDTO;

/**
 *
 * @author vopha
 */
@WebServlet(name = "SearchLastNameServlet", urlPatterns = {"/SearchLastNameServlet"})
public class SearchLastNameServlet extends HttpServlet {

    private final String RESULT_PAGE = "search.jsp";
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
        String searchValue = request.getParameter("txtSearchValue");
        String url = RESULT_PAGE;
        
        try {
            if (!searchValue.trim().isEmpty()) {
                //2. Call model
                //2.1 New DAO object 
                    RegistrationDAO dao = new RegistrationDAO();
                    
                //2.2 Call method of DAO
                dao.searchLasName(searchValue);
                List<RegistrationDTO> result = dao.getListAccounts();
                
                //3. Process result
                request.setAttribute("SEARCH_RESULT", result);
            }//End search value is valid
        } catch (SQLException ex) {
            log("SearchServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("SearchServlet _ Class Not Found " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
