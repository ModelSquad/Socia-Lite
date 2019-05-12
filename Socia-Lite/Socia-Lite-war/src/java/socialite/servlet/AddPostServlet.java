/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import socialite.dao.PostFacade;
import socialite.dao.VisibilityFacade;
import socialite.entity.Post;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import socialite.dao.AssociationFacade;
import socialite.dao.MediaFacade;
import socialite.entity.Media;

/**
 *
 * @author xfja
 */
@WebServlet(name = "AddPostServlet", urlPatterns = {"/AddPostServlet"})
@MultipartConfig
public class AddPostServlet extends HttpServlet {

    @EJB
    private PostFacade postFacade;
    
    @EJB
    private VisibilityFacade visibilityFacade;

    @EJB
    private MediaFacade mediaFacade;
    
    @EJB
    private AssociationFacade associationFacade;
    
        private static final String ACCESS_TOKEN = "3wQ3NmRIRPAAAAAAAAAADR3SEijLf_rodEXbuypIw0ubDuUyjZ-bDPvuA9-qdgEv";
    
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
           
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            try {
                HashMap<String, Object> requestData = this.getMedia(request);
                
                if(requestData.get("post-text") != null) {
                    socialite.entity.User user = (socialite.entity.User)session.getAttribute("user");
                    Post post = (Post)requestData.get("post");
                    String visibility = (String)requestData.get("visibility");
                    post.setText((String)requestData.get("post-text"));
                    post.setVisibility((visibility.equalsIgnoreCase("PUBLIC")) ? visibilityFacade.find(1) : visibilityFacade.find(2));
                    post.setMediaList((List<Media>)requestData.get("media"));
                    post.setTitle((String)requestData.get("title"));
                    
                    if(requestData.containsKey("idGroup")) {
                        Integer groupId = Integer.parseInt((String)requestData.get("idGroup"));
                        post.setAssociation(associationFacade.find(groupId));
                        postFacade.edit(post);
                        
                        response.sendRedirect(request.getContextPath() + "/PostServlet?idGroup=" + groupId);
                        
                    } else {
                        postFacade.edit(post);
                        response.sendRedirect(request.getContextPath() + "/PostServlet");
                    }
                }
                
            } catch (Exception ex) {
                Logger.getLogger(AddPostServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
          response.sendRedirect(request.getContextPath() + "/LoginServlet");
        } 
    }
    
    private HashMap<String, Object> getMedia(HttpServletRequest request) throws Exception {       
        
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> items = fileUpload.parseRequest(request);
        HashMap<String, Object> result = new HashMap<>();     
        
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        
        socialite.entity.User user = (socialite.entity.User)request.getSession().getAttribute("user");
        
        Post post = new Post();
        post.setText("");
        post.setTitle("");
        post.setLikes(0);
        post.setDate(new Date());
        post.setUser(user);
        post.setVisibility(visibilityFacade.find(1));
        postFacade.create(post);
        
        String filename = user.getNickname() + new Date().toString().replaceAll("[ :]", "_");
        
        int counter = 1;
        List<Media> media = new ArrayList<>();
        
        for (FileItem item : items) {
            
            if(item.isFormField()) {
                result.put(item.getFieldName(), item.getString());
            } else if(item.getContentType().startsWith("image")){
                
                InputStream inputStream = item.getInputStream();
                String extension = item.getContentType().substring(item.getContentType().lastIndexOf("/") + 1);
                
                FileMetadata metadata = client.files().uploadBuilder("/" + filename + counter + "." + extension)
                .uploadAndFinish(inputStream);
                
                Media currentMedia = new Media();
                currentMedia.setMediaUrl(
                client.sharing().createSharedLinkWithSettings(metadata.getPathLower()).getUrl().replace("?dl=0", "?raw=1")
                );
                currentMedia.setPost(post);
                mediaFacade.create(currentMedia);
                media.add(currentMedia);
                counter++;
            }
        }
       
	result.put("media", media);
        result.put("post", post);
        
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
