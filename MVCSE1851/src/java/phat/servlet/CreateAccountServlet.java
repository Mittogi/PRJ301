/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phat.registration.RegistrationCreateErrors;
import phat.registration.RegistrationDAO;
import phat.registration.RegistrationDTO;

/**
 *
 * @author vopha
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
    
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
        //1. Get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        boolean foundErrors = false;
        RegistrationCreateErrors errors = new RegistrationCreateErrors();
        String url = ERROR_PAGE;
        
        try {
            //2. Handle all user's errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundErrors = true;
                errors.setUsernameLengthError("User name is required typing from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErrors = true;
                errors.setPasswordLengthError("Password is required typing from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErrors = true;
                errors.setConfirmNotMatch("Confirm must match password");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErrors = true;
                errors.setFullNameLengthError("Full name is required typing from 2 to 50 characters");
            }
            
            if (foundErrors) {
                //caching a specific attribute then go to error page to show
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//no error
                //3. Insert to DB --> call model
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                
                //4.Process result
                if (result){
                    url = LOGIN_PAGE;
                }
            }//end no errors
            
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + msg);
            
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (ClassNotFoundException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ Class Not Found: " + msg);
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
