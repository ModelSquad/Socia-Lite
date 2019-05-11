/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "RequestEntryServlet", urlPatterns = {"/RequestEntryServlet"})
public class RequestEntryServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @EJB
    private AssociationRequestFacade associationRequestFacade;

    @EJB
    private AssociationFacade associationFacade;
    
    

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
            Integer associationId = Integer.valueOf(request.getParameter("idAssociation"));
            Association association = associationFacade.find(associationId);
            AssociationRequest associationRequest = new AssociationRequest();
            associationRequest.setAssociationReceiver(association);
            associationRequest.setDateTime(new Date(System.currentTimeMillis()));
            associationRequest.setUserSender(user);
            List<AssociationRequest> requests = association.getAssociationRequestList();
            requests.add(associationRequest);
            association.setAssociationRequestList(requests);
            List<AssociationRequest> requests2 = user.getAssociationRequestList();
            requests2.add(associationRequest);
            user.setAssociationRequestList(requests2);
            associationRequestFacade.create(associationRequest);
            userFacade.edit(user);
            associationFacade.edit(association);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/FindGroupsServlet");
            rd.forward(request, response);
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
