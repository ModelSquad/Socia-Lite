/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import socialite.services.Mail;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import socialite.dao.PasswordResetFacade;
import socialite.dao.UserFacade;
import socialite.entity.PasswordReset;
import socialite.entity.User;

/**
 *
 * @author jaysus
 */
@WebServlet(name = "ForgetPasswordServlet", urlPatterns = {"/ForgetPasswordServlet"})
public class ForgetPasswordServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    
    @EJB
    private PasswordResetFacade passwordResetFacade;
    
    private static final String RESET_BASE_URL = "http://localhost:8080/Socia-Lite-war/ResetPasswordServlet?resetId=";
    
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
        String email = request.getParameter("email");
        User user = userFacade.findByEmail(email);
        if(user == null) {
            request.setAttribute("error", true);
        } else {
            PasswordReset psswdReset = new PasswordReset();
            psswdReset.setUsuario(user);
            
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.HOUR, 1);
            psswdReset.setExpiritionDate(c.getTime());
            
            String resetUrlId = UUID.randomUUID().toString();           
            
            psswdReset.setUrl(resetUrlId);
            
            passwordResetFacade.create(psswdReset);
            
            Mail.sendMail(user.getEmail(), RESET_BASE_URL + resetUrlId, user.getNickname());
            request.setAttribute("error", false);
        }
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/forgetPassword.jsp");
        rd.forward(request, response);
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
