/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import socialite.dao.FriendshipRequestFacade;
import socialite.dao.UserFacade;
import socialite.entity.FriendshipRequest;
import socialite.entity.User;

/**
 *
 * @author cherra
 */
@WebServlet(name = "DenyFriendServlet", urlPatterns = {"/DenyFriendshipRequestServlet"})
public class DenyFriendshipRequestServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @EJB
    private FriendshipRequestFacade friendshipRequestFacade;
    
    

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
        User user = (User) session.getAttribute("user");
        if(user != null){
            Integer friendshipRequestId = new Integer(request.getParameter("friendshipRequest"));
            FriendshipRequest friendshipRequest = friendshipRequestFacade.findByFriendshipRequestId(friendshipRequestId);
            User userSender = friendshipRequest.getUserSender();
            List<FriendshipRequest> fr = user.getFriendshipRequestList();
            List<FriendshipRequest> frSender = userSender.getFriendshipRequestList1();
            fr.remove(friendshipRequest);
            frSender.remove(friendshipRequest);
            user.setFriendshipRequestList(fr);
            userSender.setFriendshipRequestList1(frSender);
            friendshipRequestFacade.remove(friendshipRequest);
            userFacade.edit(user);
            userFacade.edit(userSender);
            
            response.sendRedirect("friendshipRequest.jsp");
        } else {
            response.sendRedirect("login.jsp");
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
