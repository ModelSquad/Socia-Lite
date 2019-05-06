/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import socialite.dao.PostFacade;
import socialite.dao.UserFacade;
import socialite.entity.Post;
import socialite.entity.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/"})
public class LoginServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private PostFacade postFacade;

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
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String email = request.getParameter("email");

        if (email == null) {
            throw new RuntimeException("ERROR. Username is null");
        }
        String password = request.getParameter("password");
        if (password == null) {
            throw new RuntimeException("ERROR. Password is null");
        }

        User user = userFacade.findByEmail(email);
        RequestDispatcher rd;

        if (user == null || !user.getPassword().equals(password)) {
            request.setAttribute("errorLogin", true);
            rd = request.getRequestDispatcher("/index.jsp");
        } else {
            session.setAttribute("user", user);
            rd = request.getRequestDispatcher("/PostServlet");
        }
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

    private List<Post> getPosts(User user) {
        List<Post> posts = postFacade.findByUser(user);
        List<User> friends = user.getUserList();
        friends.forEach((friend) -> {
            posts.addAll(postFacade.findByUser(friend));
        });
        Collections.sort(posts, (Post p1, Post p2) -> {
            if (p1.getDate() == null || p2.getDate() == null) {
                return 0;
            }
            return p2.getDate().compareTo(p1.getDate());
        });

        return posts;
    }

}
