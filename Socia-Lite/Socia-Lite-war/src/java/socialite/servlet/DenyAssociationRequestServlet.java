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
import socialite.dao.AssociationFacade;
import socialite.dao.AssociationRequestFacade;
import socialite.dao.UserFacade;
import socialite.entity.Association;
import socialite.entity.AssociationRequest;
import socialite.entity.User;

/**
 *
 * @author cherra
 */
@WebServlet(name = "DenyAssociationRequestServlet", urlPatterns = {"/DenyAssociationRequestServlet"})
public class DenyAssociationRequestServlet extends HttpServlet {

    @EJB
    private AssociationFacade associationFacade;

    @EJB
    private AssociationRequestFacade associationRequestFacade;

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
        User user = (User) session.getAttribute("user");
        if(user != null){
            Integer associationRequestId = Integer.valueOf(request.getParameter("associationRequestId"));
            AssociationRequest associationRequest = associationRequestFacade.findByAssociationRequestId(associationRequestId);
            User sender = associationRequest.getUserSender();
            Association association = associationRequest.getAssociationReceiver();
            List<AssociationRequest> usersRequesting = association.getAssociationRequestList();
            usersRequesting.remove(associationRequest);
            association.setAssociationRequestList(usersRequesting);
            List<AssociationRequest> requestSend = sender.getAssociationRequestList();
            requestSend.remove(associationRequest);
            sender.setAssociationRequestList(requestSend);
            associationRequestFacade.remove(associationRequest);
            userFacade.edit(sender);
            associationFacade.edit(association);
            // Update user from bd
            user = userFacade.find(user.getIdUser());
            session.setAttribute("user", user);
            response.sendRedirect("groupRequest.jsp");
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
