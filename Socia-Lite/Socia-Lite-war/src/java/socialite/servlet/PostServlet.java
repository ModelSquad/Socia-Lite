package socialite.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import socialite.dao.PostFacade;
import socialite.dao.UserFacade;
import socialite.entity.Post;
import socialite.entity.User;

@WebServlet(name = "PostServlet", urlPatterns = {"/PostServlet"})
public class PostServlet extends HttpServlet {

    @EJB
    private AssociationFacade associationFacade;

    @EJB
    private PostFacade postFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String groupId = request.getParameter("idGroup");
            if (groupId == null) {
                List<User> friends = user.getUserList();
                List<Integer> ids = new ArrayList<>();
                ids.add(user.getIdUser());
                friends.forEach((u) -> {
                    ids.add(u.getIdUser());
                });
                request.setAttribute("posts", postFacade.findPostsByMultipleIds(ids, user.getIdUser()));
            } else {
                
                request.setAttribute("association", associationFacade.find(new Integer(groupId)));
                request.setAttribute("posts", postFacade.findPostsByGroup(groupId));
            }

            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/welcome.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
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
