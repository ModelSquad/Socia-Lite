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
import socialite.dao.UserFacade;
import socialite.entity.Association;
import socialite.entity.User;

/**
 *
 * @author cherra
 */
@WebServlet(name = "GoOutFromGroupServlet", urlPatterns = {"/GoOutFromGroupServlet"})
public class GoOutFromGroupServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

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
        User user = (User)session.getAttribute("user");
        if(user != null){
            Integer idAssociation = Integer.valueOf(request.getParameter("idAssociation"));
            Association association = associationFacade.find(idAssociation);
            if(!user.getIdUser().equals(association.getAdmin().getIdUser())){
                List<Association> associations = user.getAssociationList();
                associations.remove(association);
                user.setAssociationList(associations);
                List<User> members = association.getUserList();
                members.remove(user);
                association.setUserList(members);
                userFacade.edit(user);
                associationFacade.edit(association);
            } else if(user.getIdUser().equals(association.getAdmin().getIdUser()) && association.getUserList().size() == 1){
                List<User> members = association.getUserList();
                members.remove(user);
                association.setUserList(members);
                List<Association> member = user.getAssociationList();
                member.remove(association);
                user.setAssociationList(member);
                List<Association> admin = user.getAssociationList1();
                member.remove(association);
                user.setAssociationList1(admin);
                userFacade.edit(user);
                associationFacade.remove(association);
            }
            response.sendRedirect("groups.jsp");
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
