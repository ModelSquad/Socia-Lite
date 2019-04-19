<%-- 
    Document   : friends
    Created on : 18-abr-2019, 20:01:29
    Author     : cherra
--%>

<%@page import="java.util.List"%>
<%@page import="socialite.entity.*"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
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
        List<User> friends =  (List<User>)request.getSession().getAttribute("friends");
        
        // Receive any request
        List<FriendshipRequest> requests = user.getFriendshipRequestList1();
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
                    <button type="button" class="btn btn-selection btn-outline-selected">My friends</button>
                    <button type="button" class="btn btn-selection btn-default">Find friends</button>
                    <button type="button" class="btn btn-selection btn-default">Friendship requests</button>
                </div>
            <div class="card gedf-card mt-2" style="width: 100%;">     
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="m-2">
                            <img class="rounded-circle" width="45" src="https://avatars0.githubusercontent.com/u/33965662?s=460&v=4" alt="">
                        </div>
                        <div class="ml-2">
                            <div class="h5 m-0"><a href="#">@xFJA</a></div>
                            <div class="h7 text-muted"> Francisco Jiménez Aguilera </div>
                        </div>
                    </div>
                    <div>
                        <div class="dropdown">
                            <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-ellipsis-h"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                <div class="h6 dropdown-header">Options</div>
                                <a class="dropdown-item" href="#">See profile</a>
                                <a class="dropdown-item" href="#">Delete from friends</a>
                                <a class="dropdown-item" href="#">Block</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
            <div class="card gedf-card mt-2" style="width: 100%;">     
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="m-2">
                            <img class="rounded-circle" width="45" src="<%= 
                                (user.getProfilePic() == null) 
                      ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg" 
                      : user.getProfilePic()%>" alt="">
                        </div>
                        <div class="ml-2">
                            <div class="h5 m-0"><a href="#"><%="@"+user.getNickname()%></a></div>
                            <div class="h7 text-muted"><%=user.getName() +" "+user.getSurname()%></div>
                        </div>
                    </div>
                    <div>
                        <div class="dropdown">
                            <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-ellipsis-h"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                <div class="h6 dropdown-header">Options</div>
                                <a class="dropdown-item" href="#">See profile</a>
                                <a class="dropdown-item" href="#">Delete from friends</a>
                                <a class="dropdown-item" href="#">Block</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% System.out.print(friends.size());
                if(friends != null){
                for(User friend: friends){
                %>
                <div class="card gedf-card mt-2" style="width: 100%;">     
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="m-2">
                            <img class="rounded-circle" width="45" src="<%= 
                                (friend.getProfilePic() == null) 
                      ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg" 
                      : friend.getProfilePic()%>" alt="">
                        </div>
                        <div class="ml-2">
                            <div class="h5 m-0"><a href="#"><%=friend.getNickname()%></a></div>
                            <div class="h7 text-muted"><%=friend.getName() +" "+friend.getSurname()%></div>
                        </div>
                    </div>
                    <div>
                        <div class="dropdown">
                            <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-ellipsis-h"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                <div class="h6 dropdown-header">Options</div>
                                <a class="dropdown-item" href="#">See profile</a>
                                <a class="dropdown-item" href="#">Delete from friends</a>
                                <a class="dropdown-item" href="#">Block</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                 <%     
                    };
                } else {
                    System.err.println("No hay peñita");
                }

                %>
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
