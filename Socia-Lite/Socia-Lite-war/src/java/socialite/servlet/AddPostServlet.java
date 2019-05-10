/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialite.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import socialite.entity.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
                /**
                FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("socialite.json"))
                .setDatabaseUrl("https://socia-lite.firebaseio.com/")
                .build();
                FirebaseApp.initializeApp(options);
                **/
                HashMap<String, Object> requestData = this.getMedia(request);
                
                if(requestData.get("post-text") != null) {
                    User user = (User)session.getAttribute("user");
                    Post post = (Post)requestData.get("post");
                    String visibility = (String)requestData.get("visibility");
                    post.setText((String)requestData.get("post-text"));
                    post.setVisibility((visibility.equalsIgnoreCase("PUBLIC")) ? visibilityFacade.find(1) : visibilityFacade.find(2));
                    post.setMediaList((List<Media>)requestData.get("media"));
                    
                    postFacade.edit(post);

                    RequestDispatcher rd = request.getRequestDispatcher("/PostServlet");
                    rd.forward(request, response);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(AddPostServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
          response.sendRedirect(request.getContextPath()+"/index.jsp");  
        } 
    }
    
    private HashMap<String, Object> getMedia(HttpServletRequest request) throws Exception {       
        
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> items = fileUpload.parseRequest(request);
        HashMap<String, Object> result = new HashMap<>();     
        
        
        User user = (User)request.getSession().getAttribute("user");
        
        Post post = new Post();
        post.setText("");
        post.setTitle("");
        post.setLikes(0);
        post.setDate(new Date());
        post.setUser(user);
        post.setVisibility(visibilityFacade.find(1));
        postFacade.create(post);
        
        String filename = System.getProperty("user.dir") + user.getNickname() + new Date().toString().replaceAll("[ :]", "_");
        
        int counter = 1;
        List<Media> media = new ArrayList<>();
        
        for (FileItem item : items) {
            
            if(item.isFormField()) {
                result.put(item.getFieldName(), item.getString());
            } else if(item.getContentType().startsWith("image")){
                
                InputStream inputStream = item.getInputStream();

                Logger.getLogger(AddPostServlet.class.getName()).log(Level.SEVERE, item.getContentType());
                
                String extension = item.getContentType().substring(item.getContentType().lastIndexOf("/") + 1);
                
                int data = inputStream.read();
                FileOutputStream file = new FileOutputStream(filename + counter + "." + extension);
                while(data != -1) {
                    file.write(data);
                    data = inputStream.read();
                }
                Media currentMedia = new Media();
                currentMedia.setMediaUrl(filename + counter + "." + extension);
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
