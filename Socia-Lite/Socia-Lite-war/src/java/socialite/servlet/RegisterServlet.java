/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import socialite.entity.User;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import socialite.dao.UserFacade;

/**
 *
 * @author Sevi
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

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

        String strErrors = "";
        User user = new User();

        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            strErrors += "emailNull";
        } else if (!emailIsAvailable(email)) {
            strErrors += "emailExists";
        } else {
            user.setEmail(email);
        }

        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        if (password == null || password.isEmpty()) {
            strErrors += "password";
        } else if (repeatPassword == null || repeatPassword.isEmpty() || !password.equals(repeatPassword)) {
            strErrors += "repeatPassword";
        } else {
            user.setPassword(this.hashPassword(password));
        }

        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            strErrors += "justname";
        } else {
            user.setName(name);
        }

        String surname = request.getParameter("surname");
        if (surname == null || surname.isEmpty()) {
            strErrors += "surname";
        } else {
            user.setSurname(surname);
        }

        String nickname = request.getParameter("nickname");
        if (nickname == null || nickname.isEmpty()) {
            strErrors += "nickname";
        } else {
            user.setNickname(nickname);
        }

        String birthdate = request.getParameter("birthdate");
        if (birthdate == null || birthdate.isEmpty()) {
            strErrors += "birthdate";
        } else {
            try {
                user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
            } catch (ParseException ex) {
                strErrors += "birthdate";
            }
        }
        if (strErrors.equals("")) {
            userFacade.create(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/PostServlet");
        } else {
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("repeatPassword", repeatPassword);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("nickname", nickname);
            request.setAttribute("birthdate", birthdate);
            request.setAttribute("strErrors", strErrors);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
        }

    }

    private boolean emailIsAvailable(String email) {
        return userFacade.findByEmail(email) == null;
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

    public String hashPassword(String password_plaintext) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return (hashed_password);
    }
}
