<%-- 
    Document   : findGroups
    Created on : 09-may-2019, 17:48:16
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
    <title>SociaLite - Friends</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
         <%@ include file="stylesheets/friendshipRequest.css"%>
    </style>
    <script>
        <%@ include file="js/welcome.js"%>
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
        List<AssociationRequest> requests = new ArrayList<AssociationRequest>();
        List<Association> admin = new ArrayList<Association>();
        List<Association> notAssociations = new ArrayList<Association>();
        String contextPath = request.getContextPath();
        session.getAttribute("notGroups");
        if(user != null){
            admin = user.getAssociationList1();
            for(Association association : admin){
                for(AssociationRequest userRequest :association.getAssociationRequestList()){
                     requests.add(userRequest);
                }
            }
            notAssociations  = (List<Association>)request.getSession().getAttribute("notGroups");
        } else{
            
        }
         
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
                    <li class="nav-item active">
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
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
            </div>

            <div class="col-sm text-left feed">
                <div class="btn-group" role="group">
                    <a href="groups.jsp" class="btn btn-selection btn-default">My groups</a>
                    <a href="FindGroupsServlet" class="btn btn-selection btn-selected">Find groups</a>
                    <a href="groupRequest.jsp" class="btn btn-selection btn-default">Group Request <%
                        if(requests != null && requests.size() > 0){%>
                        <span class="badge badge-danger"><%=requests.size()%></span>
                        <%};
                        %></a>
                </div> 
            <% 
                if(notAssociations != null){
                for(Association notAssociation : notAssociations){
                    
                %>
                <div class="card gedf-card mt-0 mb-3" style="width: 100%;">     
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="m-2">
                            <img class="rounded-circle" width="45" src="<%= 
                                (notAssociation.getProfilePic() == null) 
                      ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg" 
                      : notAssociation.getProfilePic()%>" alt="">
                        </div>
                        <div class="ml-2">
                            <div class="h5 m-0"><a href="#"><%=notAssociation.getName()%></a></div>
                            <div class="h7 text-muted"><%=notAssociation.getDescription()== null? "" : notAssociation.getDescription() %></div>
                        </div>
                    </div>
                    <div>
                        <div class="btn-group btn-group-justified m-2 mr-4" >
                            <a href="RequestEntryServlet?idAssociation=<%=notAssociation.getIdAssociation()%>" class="btn btn-xs btn-primary"><i class="material-icons" style="font-size:20px;">done</i></span> Request entry</a>
                        </div>
                    </div>
                </div>
            </div>
                 <%
                   };
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
