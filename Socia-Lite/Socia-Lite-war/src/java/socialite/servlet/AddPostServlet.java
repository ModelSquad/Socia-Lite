/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import socialite.dao.PostFacade;
import socialite.dao.VisibilityFacade;
import socialite.entity.Post;
import socialite.entity.User;

/**
 *
 * @author xfja
 */
@WebServlet(name = "AddPostServlet", urlPatterns = {"/AddPostServlet"})
public class AddPostServlet extends HttpServlet {

    @EJB
    private PostFacade postFacade;
    
    @EJB
    private VisibilityFacade visibilityFacade;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddPostServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPostServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null && request.getParameter("post-text") != null) {
            User user = (User)session.getAttribute("user");
            Post post = new Post();
            post.setText(request.getParameter("post-text"));
            post.setTitle("");
            post.setLikes(0);
            post.setDate(new Date());
            post.setUser(user);
            post.setVisibility(visibilityFacade.find(1));
            postFacade.create(post);
            session.setAttribute("posts", getPosts(user));
            System.out.println(request.getParameter("post-text"));
        } 
        response.sendRedirect(request.getContextPath()+"/welcome.jsp");
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
