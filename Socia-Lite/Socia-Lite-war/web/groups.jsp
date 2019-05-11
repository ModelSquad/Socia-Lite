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
    <title>SociaLite - Groups</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
         <%@ include file="stylesheets/groups.css"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
        List<Association> member = new ArrayList<Association>();
        if(user != null){
            member= user.getAssociationList();
            admin = user.getAssociationList1();
            for(Association association : admin){
                for(AssociationRequest userRequest :association.getAssociationRequestList()){
                     requests.add(userRequest);
                }
            }
        }

    %>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">SociaLite</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="/Socia-Lite-war/welcome.jsp"><i class="fa fa-home" aria-hidden="true" style="font-size:20px;"></i> Home</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link " href="/Socia-Lite-war/friends.jsp"><i class="material-icons" style="font-size:22px;">people</i> Friends<span class="sr-only">(current)</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="/Socia-Lite-war/groups.jsp"><i class="fa fa-users" aria-hidden="true"></i> Groups</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/Socia-Lite-war/user.jsp"><i class="fa fa-user" aria-hidden="true"></i> Profile</a>
            </li>
            <form class="form-inline md-form form-sm mt-0" action="SearchServlet">
                <i class="fa fa-search" aria-hidden="true" style="color:lightsteelblue"></i>
                <input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search users" aria-label="Search" name="search">
            </form>
          </ul>

          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
               <a class="nav-link" href="/Socia-Lite-war/SignoutServlet"><i class="fa fa-sign-out"></i> Sign out</a>
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
                    <a href="groups.jsp" class="btn btn-selection btn-selected">My groups</a>
                    <a href="FindGroupsServlet" class="btn btn-selection btn-default">Find groups</a>
                    <a href="groupRequest.jsp" class="btn btn-selection btn-default">Group Request <%
                        if(requests != null && requests.size() > 0){%>
                        <span class="badge badge-danger"><%=requests.size()%></span>
                        <%};
                        %></a>
                </div>
            <% 
                if(member != null){
                for(Association association: member){   
                %>
            <div class="card gedf-card expand-card mt-2" style="width: 100%;">     
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="m-2">
                            <img class="rounded-circle" width="45" src="<%=(association.getProfilePic() == null) 
                      ? "https://cdn.clipart.email/0ad2ce5b5370f2d91ef8b465f6770e77_people-icons-3800-free-files-in-png-eps-svg-format_338-338.jpeg" 
                      : association.getProfilePic() %>" alt="">
                        </div>
                        <div class="ml-2">
                            <div class="h5 m-0"><a href="PostServlet?idGroup=<%=association.getIdAssociation()%>"><%=association.getName()%><%if(association.getAdmin().getIdUser()==user.getIdUser()){%>  [Admin]<%}%></a></div>
                            <div class="h7 text-muted"><%=association.getDescription()== null? "" :association.getDescription() %></div>
                        </div>
                        
                    </div>
                    <div>
                        <div class="btn-group btn-group-justified m-2 mr-4" >
                            <a class="btn btn-xs btn-primary" href="#" ><i class="material-icons" style="font-size:20px;">remove_red_eye</i>See members</a>
                            <%if(association.getAdmin().getIdUser() != user.getIdUser() || association.getUserList().size() == 1){%>
                            <a class="btn btn-xs btn-danger trigger-btn" data-href="GoOutFromGroupServlet?idAssociation=<%=association.getIdAssociation()%>" data-target="#confirm-delete" data-toggle="modal"><i class="material-icons" style="font-size:20px;">clear</i>Go out</a>
                            <%}%>
                        </div>
                    </div>

                </div>

            </div>
            <div class="card gedf-card list-card plegable" style="width: 100%;">
                <div class="mt-2">
                    <ul>
                        <%for(User associationMember : association.getUserList()){%>
                        <li>
                        <%=associationMember.getName()+" "+associationMember.getSurname()+" ("+associationMember.getNickname() +")"%>
                        </li>
                        <%}%>
                    </ul>
                </div>
            </div>
                 <%     
                    };
                }
                %>
            
            <div class="card gedf-card align-items-center" style="width: 100%;">
                
               <a href=""  data-toggle="modal" data-target="#modalCreateGroup"><i class="material-icons mt-4" style="font-size:40px;">add_circle</i>Create Group</a>
            </div>

            
                <div class="modal fade" id="modalCreateGroup" >
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Create group</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body mx-3">
                          <form action="CreateGroupServlet" method="post" id="associationForm">
                            <div class="md-form mb-5">
                          <p>Name</p>
                          <input type="text" id="nameAssociation"name="nameAssociation" class="form-control validate">
                         </div>

                        <div class="md-form mb-4">
                          <p>Description</p>
                          <input type="text" id="descriptionAssociation" name="descriptionAssociation" class="form-control validate">
                        </div>
                        <button class="btn btn-indigo" type="submit" form="associationForm">Send</button>
                          </form>
                      </div>

                    </div>
                  </div>
                </div>

              <div id="confirm-delete" class="modal fade">
                        <div class="modal-dialog modal-confirm">
                            <div class="modal-content">
                                <div class="modal-header">				
                                    <h4 class="modal-title">Are you sure?</h4>	
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <p>Do you really want to go out of this group? This process cannot be undone.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                                    <a id="delete-btn" class="btn btn-danger btn-ok">Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
            <form action="actionName">
            <div id="divId" style="display:none;">
            UsreName:<input type="text" name="userName"/>
            </div>
            </form>
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
<script>
	jQuery.fn.animateAuto = function(prop, speed, callback){
    var elem, height, width;
    return this.each(function(i, el){
        el = jQuery(el), elem = el.clone().css({"height":"auto","width":"auto"}).appendTo("body");
        height = elem.css("height"),
        width = elem.css("width"),
        elem.remove();
        
        if(prop === "height")
            el.animate({"height":height}, speed, callback);
        else if(prop === "width")
            el.animate({"width":width}, speed, callback);  
        else if(prop === "both")
            el.animate({"width":width,"height":height}, speed, callback);
    });  
	}
	$(window).ready(function(){
		$('.expand-card').click(function(){
			if($(this).next().hasClass('desplegado')){
				$(this).next().removeClass('desplegado').animate({height:0},500);
			}else{
				$(this).next().addClass('desplegado').animateAuto("height",500);
			}
		})
	})
</script>