/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import socialite.dao.UserFacade;
import socialite.entity.Media;
import socialite.entity.Post;
import socialite.entity.User;

/**
 *
 * @author Sevi
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    
    private static final String ACCESS_TOKEN = "3wQ3NmRIRPAAAAAAAAAADR3SEijLf_rodEXbuypIw0ubDuUyjZ-bDPvuA9-qdgEv";
    private boolean changepic=false;
    
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
        try {
            HashMap<String, Object> requestData = this.getMedia(request);
            if(!changepic){
                String name = (String) requestData.get("name");
                String surname = (String) requestData.get("surname");
                String birthdate = (String) requestData.get("birthdate");
                String birthplace = (String) requestData.get("birthplace");
                String birthplaceDelete = (String) requestData.get("birthplaceDelete");
                String job = (String) requestData.get("job");
                String jobdelete = (String) requestData.get("jobDelete");
                String studyPlace = (String) requestData.get("studyPlace");
                String studyPlaceDelete = (String) requestData.get("studyPlaceDelete");
                String website = (String) requestData.get("website");
                String websiteDelete = (String) requestData.get("websiteDelete");
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
            }
            response.sendRedirect(request.getContextPath()+"/user.jsp");
            
        } catch (Exception ex) {
                Logger.getLogger(AddPostServlet.class.getName()).log(Level.SEVERE, null, ex);
        }           
    } 
    
    
    private HashMap<String, Object> getMedia(HttpServletRequest request) throws Exception {
        
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> items = fileUpload.parseRequest(request);
        HashMap<String, Object> result = new HashMap<>();   

        User user = (User)request.getSession().getAttribute("user");
        String filename = user.getNickname() + new Date().toString().replaceAll("[ :]", "_");
        
        for (FileItem item : items) {
            
            if(item.isFormField()) {
                result.put(item.getFieldName(), item.getString());
            } else if(item.getContentType().startsWith("image")){
                DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
                DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);                
                InputStream inputStream = item.getInputStream();  
                String extension = item.getContentType().substring(item.getContentType().lastIndexOf("/") + 1);             
                FileMetadata metadata = client.files().uploadBuilder("/" + filename + "." + extension).uploadAndFinish(inputStream);                              
                
                user.setProfilePic(client.sharing().createSharedLinkWithSettings(metadata.getPathLower()).getUrl().replace("?dl=0", "?raw=1"));
                userFacade.edit(user);
                changepic=true;
            }
        }            
        return result;       
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
