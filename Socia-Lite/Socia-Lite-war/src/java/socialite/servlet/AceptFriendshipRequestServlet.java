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
import socialite.entity.*;

/**
 *
 * @author cherra
 */
@WebServlet(name = "AceptFriendshipRequestServlet", urlPatterns = {"/AceptFriendshipRequestServlet"})
public class AceptFriendshipRequestServlet extends HttpServlet {

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
            Integer friendshipRequestId = Integer.valueOf(request.getParameter("friendshipRequest"));
            FriendshipRequest friendshipRequest = friendshipRequestFacade.findByFriendshipRequestId(friendshipRequestId);
            List<User> amigos = user.getUserList();
            List<User> amigos1 = user.getUserList1();
            User newFriend = friendshipRequest.getUserSender();
            List<User> amigos2 = newFriend.getUserList();
            List<User> amigos3 = newFriend.getUserList1();
            amigos.add(newFriend);
            amigos1.add(newFriend);
            user.setUserList(amigos);
            user.setUserList1(amigos1);
            amigos2.add(user);
            amigos3.add(user);
            newFriend.setUserList(amigos2);
            newFriend.setUserList1(amigos3);
            List<FriendshipRequest> fr = user.getFriendshipRequestList();
            List<FriendshipRequest> frSender = newFriend.getFriendshipRequestList1();
            fr.remove(friendshipRequest);
            user.setFriendshipRequestList(fr);
            frSender.remove(friendshipRequest);
            newFriend.setFriendshipRequestList1(frSender);
            friendshipRequestFacade.remove(friendshipRequest);
            userFacade.edit(user);
            userFacade.edit(newFriend);
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
