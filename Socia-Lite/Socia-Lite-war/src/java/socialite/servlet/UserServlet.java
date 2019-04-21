/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import socialite.dao.UserFacade;
import socialite.entity.User;

/**
 *
 * @author Sevi
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
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
        
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birthdate = request.getParameter("birthdate");
        String birthplace = request.getParameter("birthplace");
        String birthplaceDelete = request.getParameter("birthplaceDelete");
        String job = request.getParameter("job");
        String jobdelete = request.getParameter("jobDelete");
        String studyPlace = request.getParameter("studyPlace");
        String studyPlaceDelete = request.getParameter("studyPlaceDelete");
        String website = request.getParameter("website");
        String websiteDelete = request.getParameter("websiteDelete");
        User user = (User) request.getSession().getAttribute("user");
        
        if(name!=null){
            user.setName(name);
        }else if(surname!=null){
            user.setSurname(surname);
        }else if(birthdate!=null){
            try {  
                user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
            } catch (ParseException ex) {
                throw new RuntimeException("Error: editing birthdate");
            }
        }else if(birthplace!=null){ 
            user.setBirthPlace(birthplace);
        }else if(birthplaceDelete!=null){
            user.setBirthPlace(null);
        }else if(job!=null){
            user.setJob(job);
        }else if(jobdelete!=null){
            user.setJob(null);
        }else if(studyPlace!=null){
            user.setStudyPlace(studyPlace);
        }else if(studyPlaceDelete!=null){
            user.setStudyPlace(null);
        }else if(website!=null){
            user.setWebsite(website);
        }else if(websiteDelete!=null){
            user.setWebsite(null);
        }
        userFacade.edit(user);
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user.jsp");
        rd.forward(request, response);
        
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
