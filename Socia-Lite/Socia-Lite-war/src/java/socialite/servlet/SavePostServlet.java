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
import java.util.Objects;
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

@WebServlet(name = "SavePostServlet", urlPatterns = {"/SavePostServlet"})
public class SavePostServlet extends HttpServlet {

    @EJB
    private VisibilityFacade visibilityFacade;

    @EJB
    private PostFacade postFacade;
    
    
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
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && request.getParameter("id")!=null) {
            Integer idPost = Integer.parseInt(request.getParameter("id"));
            Post post = postFacade.find(idPost);
            String title = (String) request.getParameter("title");
            post.setTitle(title);
            String textBody = (String) request.getParameter("text-body");
            post.setText(textBody);
            post.setDate(new Date());
            String visibility = (String)request.getParameter("visibility");
            post.setVisibility((visibility.equalsIgnoreCase("PUBLIC")) ? visibilityFacade.find(1) : visibilityFacade.find(2));
            postFacade.edit(post);
            RequestDispatcher rd = request.getRequestDispatcher("/PostServlet");
            rd.forward(request, response);
        }else{
             response.sendRedirect(request.getContextPath() + "/welcome.jsp"); //should return error in future versions
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
