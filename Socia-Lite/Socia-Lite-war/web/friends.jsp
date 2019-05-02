<%-- 
    Document   : friends
    Created on : 18-abr-2019, 20:01:29
    Author     : cherra
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="socialite.entity.*"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <a href="friends.jsp"></a>
    <title>SociaLite - Friends</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
         <%@ include file="friends.css"%>
    </style>
    <script>
        <%@ include file="welcome.js"%>
    </script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous">
        
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
        
        <!-- Fancybox -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.7/dist/jquery.fancybox.min.css" />

<script src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.7/dist/jquery.fancybox.min.js"></script>
<!-- Icons -->
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">    

  </head>
    <body>
         <% 
        User user = (User)request.getSession().getAttribute("user");
        List<FriendshipRequest> requests = new ArrayList<FriendshipRequest>();
        List<User> friends= new ArrayList<User>();
        if(user != null){
            friends= user.getUserList();
            requests = user.getFriendshipRequestList();
        }

    %>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="welcome.jsp"><i class="material-icons" style="font-size:20px;">home</i>Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
               
              <a class="nav-link active" href="friends.jsp"><i class="material-icons" style="font-size:20px;">people</i> Friends</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Features</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Pricing</a>
            </li>

          </ul>
        </div>
    </nav>
    
    
    <div class="container-fluid text-center">
        <div class="row content">
            <div class="col-sm-2 sidenav">
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
            </div>

            <div class="col-sm text-left feed">
                <div class="btn-group" role="group">
                    <a href="friends.jsp" class="btn btn-selection btn-selected">My friends</a>
                    <a href="FindFriendsServlet" class="btn btn-selection btn-default">Find friends</a>
                    <a href="friendshipRequest.jsp" class="btn btn-selection btn-default">Friendship Request <%
                        if(requests != null && requests.size() > 0){%>
                        <span class="badge badge-danger"><%=requests.size()%></span>
                        <%};
                        %></a>
                </div>
            <% 
                if(friends != null){
                for(User friend: friends){
                    
                %>
            <div class="card gedf-card mt-2" style="width: 100%;">     
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="m-2">
                            <img class="rounded-circle" width="45" src="<%=(friend.getProfilePic() == null) 
                      ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg" 
                      : friend.getProfilePic() %>" alt="">
                        </div>
                        <div class="ml-2">
                            <div class="h5 m-0"><a href="#"><%=friend.getNickname()%></a></div>
                            <div class="h7 text-muted"><%=friend.getName()%></div>
                        </div>
                    </div>
                    <div>
                        <div class="btn-group btn-group-justified m-2 mr-4" >
                            <a class="btn btn-xs btn-primary" data-href="#" data-target="#" ><i class="material-icons" style="font-size:20px;">remove_red_eye</i>See profile</a>
                            <a class="btn btn-xs btn-danger trigger-btn" data-href="DeleteFriendServlet?idFriend=<%=friend.getIdUser()%>" data-target="#confirm-delete" data-toggle="modal"><i class="material-icons" style="font-size:20px;">clear</i>Delete</a>
                        </div>
                    </div>
                </div> 
            </div>
                 <%     
                    };
                } else {
                   %>
                   <p>No tienes amigos, lo siento.</p>
                   <p>Puedes conocer gente nueva en la pestaña find Friends</p>
                   <%
                }
                %>
              <div id="confirm-delete" class="modal fade">
                        <div class="modal-dialog modal-confirm">
                            <div class="modal-content">
                                <div class="modal-header">				
                                    <h4 class="modal-title">Are you sure?</h4>	
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <p>Do you really want to delete this friend from your list? This process cannot be undone.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                                    <a id="delete-btn" class="btn btn-danger btn-ok">Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>   
                
            
            </div>
                <div class="col-sm-2 sidenav">
                    <div class="well">
                    <p>ADS</p>
                    </div>
                    <div class="well">
                    <p>ADS</p>
                    </div>
                </div>
        </div> 
    </div>
    </body>
</html>

<!-- SCRIPT DELETE FRIEND WARNING -->
<script>
    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
    });
</script>