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
import socialite.dao.UserFacade;
import socialite.entity.User;

/**
 *
 * @author cherra
 */
@WebServlet(name = "DeleteFriendServlet", urlPatterns = {"/DeleteFriendServlet"})
public class DeleteFriendServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");
        
        if(currentUser != null){
            String deleteFriend = (String)request.getParameter("idFriend");
            Integer idFriend = Integer.valueOf(deleteFriend);
            User friend = userFacade.findByIdUser(idFriend);
            if(friend != null){
                List<User> friends1 = currentUser.getUserList();
                friends1.remove(friend);
                currentUser.setUserList(friends1);
                List<User> friends2 = currentUser.getUserList1();
                friends2.remove(friend); 
                currentUser.setUserList1(friends2);
                List<User> friends3 = friend.getUserList();
                friends3.remove(currentUser);
                friend.setUserList(friends3);
                List<User> friends4 = friend.getUserList1();
                friends4.remove(currentUser);
                friend.setUserList1(friends4);
                userFacade.edit(currentUser);
                userFacade.edit(friend);
            }
            response.sendRedirect(request.getContextPath()+"/friends.jsp");
        } else {
            response.sendRedirect("index.jsp");
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
