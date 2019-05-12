/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import socialite.dao.PasswordResetFacade;
import socialite.dao.UserFacade;
import socialite.entity.PasswordReset;
import socialite.entity.User;

/**
 *
 * @author jaysus
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/ResetPasswordServlet"})
public class ResetPasswordServlet extends HttpServlet {

    @EJB
    private PasswordResetFacade passwordResetFacade;
    
    @EJB
    private UserFacade userFacade;

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
        String resetId = request.getParameter("resetId");
        
        PasswordReset passwordReset = passwordResetFacade.getByUrlId(resetId);
        
        if(passwordReset == null) {
            request.setAttribute("expired", true);
        } else if(passwordReset.getExpiritionDate().before(new Date())) {
            passwordResetFacade.remove(passwordReset);
            request.setAttribute("expired", true);
        } else {
            User user = passwordReset.getUsuario();
            request.setAttribute("user", user);
        }
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/resetPassword.jsp");
        rd.forward(request, response);
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
        String userId = request.getParameter("userId");
        User user = userFacade.findByIdUser(new Integer(userId));
        
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("checkPassword");
        
        if(password.isEmpty() || !password.equals(checkPassword)) {
            request.setAttribute("error", true);
            request.setAttribute("user", user);
        } else {
            user.setPassword(this.hashPassword(password));
            
            Collection<PasswordReset> passwordResetCollecion = user.getPasswordResetCollection();
            for(PasswordReset pw : passwordResetCollecion) {
                passwordResetFacade.remove(pw);
            }
            passwordResetCollecion.clear();
            user.setPasswordResetCollection(passwordResetCollecion);
            
            userFacade.edit(user);
            
            request.setAttribute("success", true);
        }
        
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/resetPassword.jsp");
        rd.forward(request, response);
        
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
    
        public String hashPassword(String password_plaintext) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return (hashed_password);
    }
}
