<%-- 
    Document   : user.jsp
    Created on : Apr 19, 2019, 12:26:46 PM
    Author     : Sevi
--%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="socialite.entity.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SociaLite - Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            <%@ include file="stylesheets/user.css"%>
        </style>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    </head>
    <body>
        <%
            User u = (User) request.getAttribute("user");
            User user = (User) session.getAttribute("user");
            String contextPath = request.getContextPath();
            List<User> friends = user.getUserList();
            List<Association> associations = user.getAssociationList();
        %>
        <nav class="navbar navbar-expand-lg navbar-dark">
            <a class="navbar-brand" href="<%=contextPath%>/PostServlet">SociaLite</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=contextPath%>/welcome.jsp"><i class="fa fa-home" aria-hidden="true" style="font-size:20px;"></i> Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=contextPath%>/friends.jsp"><i class="material-icons" style="font-size:22px;">people</i> Friends</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=contextPath%>/groups.jsp"><i class="fa fa-users" aria-hidden="true"></i> Groups</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=contextPath%>/user.jsp"><i class="fa fa-user" aria-hidden="true"></i> Profile</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <div id="searcher">
                            <form class="form-inline md-form form-sm mt-0" action="SearchServlet">
                                <label>
                                    <i class="fa fa-search" aria-hidden="true" style="color:lightsteelblue"></i>
                                    <button input="submit" hidden></button>
                                </label>                                               
                            <input class="form-control form-control-sm ml-3 w-200 searcher-box input-lg" type="text" placeholder="Search users" aria-label="Search" name="search">                       
                            </form>
                         </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=contextPath%>/SignoutServlet"><i class="fa fa-sign-out"></i> Sign out</a>
                    </li>
                </ul>
            </div>
        </nav>
        
        <div class="container-fluid text-center">
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <div class="panel panel-primary friend-panel m-2">
                            <div class="panel-heading">Friends</div>
                            <div class="panel-body friend-content" style="overflow-y: scroll; ">
                                <%if (friends != null && friends.size() > 0) {
                                        for (User friend : friends) {
                                %>
                                <div class="card m-2">
                                    <div class="d-flex align-items-center">
                                        <div class="mr-0">
                                            <img class="rounded-circle" width="45" src="<%=(friend.getProfilePic() == null)
                                                        ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg"
                                                        : friend.getProfilePic()%>" alt="">
                                        </div>
                                        <div class="ml-2">
                                            <div class="h5 m-0"><a href="ProfileServlet?user=<%=friend.getIdUser()%>">@<%=friend.getNickname()%></a></div>
                                            <div class="h7 text-muted"><%=friend.getName()%> <%=friend.getSurname()%></div>
                                        </div>
                                    </div>
                                </div>
                                <%  }
                                }%>
                            </div>
                        </div>
                </div>
                
                <div class="col-sm text-left feed">
                    <center>
                    <%if(u==null){ %>
                        <p>No user found with that nickname.</p>
                    <%} else{ %>
                        <% if(u.getProfilePic()!=null) {%>
                            <img src=<%=u.getProfilePic()%> name="aboutme" width="140" height="140" border="0" class="rounded-circle"></a>
                        <% } else{ %>
                            <img src="http://www.softball.org.au/wp-content/uploads/2017/05/no-profile-image.png" name="aboutme" width="140" height="140" border="0" class="rounded-circle"></a>
                        <% } %>
                        <div>
                            <% if(!user.getUserList().contains(u)){ %>
                        <div class="btn-group btn-group-justified m-2 mr-4" >
                            <a href="SendFriendshipRequestServlet?idFriendRequest=<%=u.getIdUser()%>" class="btn btn-xs btn-primary"><i class="material-icons" style="font-size:20px;">done</i></span> Request friends</a>
                            </div>
                            <% } %>
                        </div>
                        <h5 class="media-heading"><%=u.getName()%> <%=u.getSurname()%> <small><%=u.getNickname()%></small></h5>

                        <br>   
                        </center>

                        <div class="card bg-light mb-3" style="margin: 1em;">
                            <div class="card-header">
                                <h5>Name</h5>
                            </div>
                                <div class="card-body">
                                    <p class="card-text"><%=u.getName()%></p>                                
                                </div>
                        </div>

                        <div class="card bg-light mb-3" style="margin: 1em;">
                            <div class="card-header">
                                <h5>Surname</h5>                            
                            </div>
                                <div class="card-body">
                                    <p class="card-text"><%=u.getSurname()%></p>                                
                                </div>
                        </div>                                                                                            

                        <div class="card bg-light mb-3" style="margin: 1em;">
                            <div class="card-header">
                                <h5>Birthdate</h5>                            
                            </div>
                                <div class="card-body">
                                    <p class="card-text"><% 
                                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                    String dateString = format.format(u.getBirthDate());                               
                                    %><%=dateString%>
                                    </p>                                                                
                                </div>
                        </div>

                        <div class="card bg-light mb-3" style="margin: 1em;">
                            <div class="card-header">
                                <h5>Birthplace</h5>
                            </div>
                                <div class="card-body">
                                    <% if(u.getBirthPlace()!=null){ %>
                                    <p class="card-text"><%=u.getBirthPlace()%></p>
                                    <% } %>
                                </div>                        
                        </div>                    

                        <div class="card bg-light mb-3" style="margin: 1em;">
                            <div class="card-header">
                                <h5>Job</h5>                                                       
                            </div>
                                <div class="card-body">
                                    <% if(u.getJob()!=null){ %>
                                    <p class="card-text"><%=u.getJob()%></p>
                                    <% } %>
                                </div>                            
                        </div>

                        <div class="card bg-light mb-3" style="margin: 1em;">
                            <div class="card-header">
                                <h5>Study Place</h5>                            
                            </div>
                                <div class="card-body">
                                    <% if(u.getStudyPlace()!=null){ %>
                                    <p class="card-text"><%=u.getStudyPlace()%></p>
                                    <% } %>
                                </div>                            
                        </div>                

                        <div class="card bg-light mb-3" style="margin: 1em">
                            <div class="card-header">
                                <h5>Website</h5>                            
                            </div>
                                <div class="card-body">
                                    <% if(u.getWebsite()!=null){ %>
                                    <p class="card-text"><%=u.getWebsite()%></p>
                                    <% } %>
                                </div>
                        </div>
                    <% } %>
                </div>    
                
                
                
                
                
                <div class="col-sm-2 sidenav">
                    <div class="panel panel-primary friend-panel m-2">
                            <div class="panel-heading">Groups</div>
                            <div class="panel-body friend-content" style="overflow-y: scroll; ">

                                <%if (associations != null && associations.size() > 0) {
                                        for (Association associationMember : associations) {
                                %>
                                <div class="card m-2">
                                    <div class="d-flex align-items-center">
                                        <div class="mr-0">
                                            <img class="rounded-circle" width="45" src="<%=(associationMember.getProfilePic() == null)
                                                        ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg"
                                                        : associationMember.getProfilePic()%>" alt="">
                                        </div>
                                        <div class="ml-2">
                                            <div class="h5 m-0"><a href="PostServlet?idGroup=<%=associationMember.getIdAssociation()%>"><%=associationMember.getName()%></a></div>
                                        </div>
                                    </div>
                                </div>
                                <%  }
                                    }%>
                            </div>
                    </div>
                </div>
            </div>
        </div>
                            
                            
        <footer class="container-fluid text-center">
            <p>Footer Text</p>
        </footer>

    </body>
</html>

