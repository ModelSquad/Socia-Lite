<%@page import="java.util.List"%>
<%@page import="socialite.entity.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="socialite.entity.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%
            User user = (User) request.getSession().getAttribute("user");
            Vector<Post> posts = (Vector<Post>) request.getSession().getAttribute("posts");
            List<User> friends = user.getUserList();
            List<Association> associations = user.getAssociationList();
            Association association = (Association) request.getAttribute("association");
            String currentPath = request.getContextPath();
            if (association != null && !user.getAssociationList().contains(association)) {
                response.sendRedirect(currentPath + "/PostServlet");
            }
            String contextPath = request.getContextPath();
        %>
        <title>SociaLite <%
            if (association == null) { %>
            - Homepage
            <% } else {%> 
            - <%= association.getName()%> 
            <% } %>
        </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            <%@ include file="stylesheets/welcome.css"%>
        </style>
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.7/dist/jquery.fancybox.min.css" />
        <script src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.7/dist/jquery.fancybox.min.js"></script>
        <!-- Icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script>
            <%@ include file="js/welcome.js"%>
        </script>

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark">
            <a class="navbar-brand" href="<%=contextPath%>/PostServlet">SociaLite</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
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
                    <div class="row content">
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
                </div>      


                <div class="col-sm text-left feed">

                    <%
                        if (user == null) {
                            response.sendRedirect(currentPath);
                        } else {
                            posts = (Vector<Post>) request.getAttribute("posts");
                        }
                        if (posts == null) {
                            response.sendRedirect(currentPath + "/PostServlet");
                        }
                    %>


                    <div class="container-fluid text-center">
                        <div class="col-sm text-left feed">
                            <% if(association != null) { %> <center><h1><%= association.getName() %> </h1></center> <% } %>
                            <!-- ADD POST -->
                            <form method="POST" action="<%=request.getContextPath()%>/AddPostServlet" enctype='multipart/form-data'>
                                <% if (association != null) {
                                %>
                                <input type="hidden" value="<%= association.getIdAssociation()%>" name="idGroup">
                                <%
                                    }
                                %>
                                <div class="jumbotron jumbotron-post">
                                    <h4>Share your experiences</h4>
                                    <textarea name="post-text" class="form-control text-area-post" placeholder="Write something here..."></textarea>
                                    <input type="text" name="title" class="form-control post-actions" placeholder="Title"/>
                                    <div class="btn-group post-actions" role="group">
                                        <div class="input-group">
                                            <div id="radioBtn" class="btn-group">
                                                <a class="btn btn-primary btn-sm active" data-toggle="visibility" data-title="public">Friends</a>
                                                <a class="btn btn-primary btn-sm notActive" data-toggle="visibility" data-title="private">Private</a>
                                            </div>
                                            <input type="hidden" name="visibility" id="visibility">
                                        </div>
                                    </div>
                                    <div class="btn-group post-button post-actions" role="group">
                                        <label class="btn btn-post">
                                            Upload picture <input type="file" name="img-upload" id="img-upload" hidden multiple>
                                        </label>
                                        <input type="submit" class="btn btn-post btn-send" value="Send">
                                    </div>
                                    <div class="row post-row">
                                        <img id="img-0" class="img-preview" src="https://zenit.org/wp-content/uploads/2018/05/no-image-icon.png">
                                        <img id="img-1" class="img-preview" src="https://zenit.org/wp-content/uploads/2018/05/no-image-icon.png">
                                        <img id="img-2" class="img-preview" src="https://zenit.org/wp-content/uploads/2018/05/no-image-icon.png">
                                        <img id="img-3" class="img-preview" src="https://zenit.org/wp-content/uploads/2018/05/no-image-icon.png">
                                        <img id="img-4" class="img-preview" src="https://zenit.org/wp-content/uploads/2018/05/no-image-icon.png">
                                    </div>
                                </div>
                            </form>

                            <%
                                if (posts != null) {
                                    for (Post post : posts) {
                            %>

                            <div class="card gedf-card " style="width: 100%;">
                                <div class="card-header">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="mr-2">
                                                <img class="rounded-circle" width="45" src="<%=(post.getUser().getProfilePic() == null)
                                                        ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg"
                                                        : post.getUser().getProfilePic()%>" alt="">
                                            </div>
                                            <div class="ml-2">
                                                <div class="h5 m-0">@<%= post.getUser().getNickname()%></div>
                                                <div class="h7 text-muted"><%=post.getUser().getName()%> <%=post.getUser().getSurname()%></div>
                                            </div>
                                        </div>
                                        <% if (post.getUser().getIdUser().equals(user.getIdUser())) {%>
                                        <div>
                                            <div class="dropdown">
                                                <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fa fa-ellipsis-h"></i>
                                                </button>
                                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                                    <div class="h6 dropdown-header">Options</div>
                                                    <a class="dropdown-item" data-href="<%=request.getContextPath()%>/DeletePostServlet?id=<%=post.getIdPost()%>" data-target="#confirm-delete" class="trigger-btn" data-toggle="modal">Delete</a>
                                                    <a class="dropdown-item" href="<%=request.getContextPath()%>/EditPostServlet?id=<%=post.getIdPost()%>">Edit</a>
                                                </div>
                                            </div>
                                        </div>
                                        <%}%>
                                    </div>

                                </div>
                                <div class="card-body">
                                    <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o" aria-hidden="true"></i> <%=post.getDate()%> </div>
                                    <%List<Media> photos = post.getMediaList();
                                        if (photos != null) {%>
                                    <div id="carouselIndicator" class="carousel slide" data-ride="carousel">
                                        <ol class="carousel-indicators">
                                            <%for (Integer i = 0; i < photos.size(); i++) {
                                                    String str = "";
                                                    str = (i == 0) ? "active" : "";
                                            %>               
                                            <li data-target="#carouselIndicator" data-slide-to="<%=i%>" class="<%=str%>"></li>
                                                <%}%>
                                        </ol>
                                        <div class="carousel-inner">

                                            <%boolean first = true;
                                                for (Media ph : photos) {
                                                    String str = "";
                                                    if (first) {
                                                        str = "active";
                                                        first = false;
                                                    }%>

                                            <div class="carousel-item <%=str%> post-image">
                                                <a data-fancybox="gallery" href="<%=ph.getMediaUrl()%>">
                                                    <img class="card-img-top post-image" src="<%=ph.getMediaUrl()%>" alt="Card image">
                                                </a>
                                            </div>
                                            <%}%>
                                        </div>
                                        <a class="carousel-control-prev" href="#carouselIndicator" role="button" data-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                        <a class="carousel-control-next" href="#carouselIndicator" role="button" data-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Previus</span>
                                        </a>
                                    </div>
                                    <%}%>
                                    <a class="card-link" href="#">
                                        <h5 class="card-title"><%=post.getTitle()%></h5>
                                    </a>
                                    <p class="card-text">
                                        <%= post.getText()%>
                                    </p>
                                </div>
                            </div>

                            <%
                                    }
                                }
                            %>
                            <div id="confirm-delete" class="modal fade">
                                <div class="modal-dialog modal-confirm">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <div class="icon-box">
                                                <i class="material-icons">&#xE5CD;</i>
                                            </div>				
                                            <h4 class="modal-title">Are you sure?</h4>	
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Do you really want to delete this post? This process cannot be undone.</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                                            <a id="delete-btn" class="btn btn-danger btn-ok">Delete</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- Right colum -->
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

                </body>
            </div>   
        </div>
    </div>

    <script>
        window.onscroll = function () {
            myFunction()
        };

        var header = document.getElementById("myHeader");
        var sticky = header.offsetTop;

        function myFunction() {
            if (window.pageYOffset > sticky) {
                header.classList.add("sticky");
            } else {
                header.classList.remove("sticky");
            }
        }
    </script>
</body>
</html>

<!-- SCRIPT DELETE POST WARNING -->
<script>
    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));

        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
    });
</script>
