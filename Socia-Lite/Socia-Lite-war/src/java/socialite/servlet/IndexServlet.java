/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import socialite.dao.PostFacade;
import socialite.entity.User;
import socialite.entity.Post;
import java.util.Date;
import socialite.dao.VisibilityFacade;

/**
 *
 * @author jaysus
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {

    @EJB
    private PostFacade postFacade;    
    
    @EJB
    private VisibilityFacade visibilityFacade;    

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
        RequestDispatcher rd;
        User user = (User)session.getAttribute("user");
        if(user != null) {
            session.setAttribute("posts", postFacade.findByUser(user));
            rd = request.getRequestDispatcher("/welcome.jsp");
        } else {
            rd = request.getRequestDispatcher("/index.jsp");
        }
        
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
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Post post = new Post();
        post.setText(request.getParameter("post-text"));
        post.setTitle("Titulo");
        post.setLikes(0);
        post.setDate(new Date());
        post.setUser(user);
        post.setVisibility(visibilityFacade.find(1));
        postFacade.create(post);
        session.setAttribute("posts", postFacade.findByUser(user));
        request.getRequestDispatcher("welcome.jsp").forward(request, response);
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
