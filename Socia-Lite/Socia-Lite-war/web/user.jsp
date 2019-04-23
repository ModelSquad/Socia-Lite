<%-- 
    Document   : user.jsp
    Created on : Apr 19, 2019, 12:26:46 PM
    Author     : Sevi
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="socialite.entity.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SociaLite - User</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            <%@ include file="user.css"%>
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
            User user = (User) request.getSession().getAttribute("user");
        %>
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top">
            <a class="navbar-brand" href="#">Socia-Lite</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="/Socia-Lite-war/welcome.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Features</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pricing</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">My profile <span class="sr-only">(current)</span></a>
                    </li>
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
                    <center>
                    <% if(user.getProfilePic()!=null) {%>
                        <img src=<%=user.getProfilePic()%> name="aboutme" width="140" height="140" border="0" class="rounded-circle"></a>
                    <% } else{ %>
                        <img src="http://www.softball.org.au/wp-content/uploads/2017/05/no-profile-image.png" name="aboutme" width="140" height="140" border="0" class="rounded-circle"></a>
                    <% } %>
                    <h5 class="media-heading"><%=user.getName()%> <%=user.getSurname()%> <small><%=user.getNickname()%></small></h5>
       
                    <br>   
                    </center>
                    
                    <div class="card bg-light mb-3" style="margin: 1em;">
                        <div class="card-header">
                            <h5>Name</h5>
                            <button type="button" class="bename btn btn-outline-secondary btn-sm"><i class="fas fa-edit"></i> Edit</button>
                        </div>
                            <div class="card-body">
                                <p class="card-text"><%=user.getName()%></p>
                                <div class="card-body ename" style="display:none">
                                    <form action="UserServlet" method="post">
                                        <h4>Edit your name</h4>
                                        <input type="text" name="name">
                                        <input type="submit" name="submit" value="Edit" class="btn btn-outline-secondary btn-sm">  
                                    </form>
                                </div>
                            </div>
                    </div>
                    <script>
                        $(function(){
                            $(".bename").click(function(){
                                $(".ename").show();
                            });
                        });
                    </script>
                    
                    <div class="card bg-light mb-3" style="margin: 1em;">
                        <div class="card-header">
                            <h5>Surname</h5>
                            <button type="button" class="besurname btn btn-outline-secondary btn-sm"><i class="fas fa-edit"></i> Edit</button>
                        </div>
                            <div class="card-body">
                                <p class="card-text"><%=user.getSurname()%></p>
                                <div class="card-body esurname" style="display:none">
                                    <form action="UserServlet" method="post">
                                        <h4>Edit your surname</h4>
                                        <input type="text" name="surname">
                                        <input type="submit" name="submit" value="Edit" class="btn btn-outline-secondary btn-sm">  
                                    </form>
                                </div>
                            </div>
                    </div>
                    <script>
                        $(function(){
                            $(".besurname").click(function(){
                                $(".esurname").show();
                            });
                        });
                    </script>
                    
                    
                    
                    
                    
                    
                    <div class="card bg-light mb-3" style="margin: 1em;">
                        <div class="card-header">
                            <h5>Birthdate</h5>
                            <button type="button" class="bebirthdate btn btn-outline-secondary btn-sm"><i class="fas fa-edit"></i> Edit</button>
                        </div>
                            <div class="card-body">
                                <p class="card-text"><% 
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                String dateString = format.format(user.getBirthDate());                               
                                %><%=dateString%>
                                </p>
                                
                                <div class="card-body ebirthdate" style="display:none">
                                    <form action="UserServlet" method="post">
                                        <h4>Edit your birthdate</h4>
                                        <input type="date" name="birthdate">
                                        <input type="submit" name="submit" value="Edit" class="btn btn-outline-secondary btn-sm">  
                                    </form>
                                </div>
                            </div>
                    </div>
                    <script>
                        $(function(){
                            $(".bebirthdate").click(function(){
                                $(".ebirthdate").show();
                            });
                        });
                    </script>
                    
                    <div class="card bg-light mb-3" style="margin: 1em;">
                        <div class="card-header">
                            <h5>Birthplace</h5>
                            <% if(user.getBirthPlace()==null){ %>
                                <button type="button" class="babirthplace btn btn-outline-primary btn-sm"><i class="fas fa-plus"></i> Add</button>
                            <% } else {%>
                                <button type="button" class="bebirthplace btn btn-outline-secondary btn-sm"><i class="fas fa-edit"></i> Edit</button>
                                <form action="UserServlet" method="post" class="linea">
                                    <input hidden type="text" name="birthplaceDelete" value="yes">
                                    <button type="submit" class ="btn btn-outline-danger btn-sm"><i class="far fa-trash-alt"></i> Delete</button>     
                                </form>
                            <% } %>
                        </div>
                            <div class="card-body">
                                <% if(user.getBirthPlace()!=null){ %>
                                <p class="card-text"><%=user.getBirthPlace()%></p>
                                <% } %>
                            </div>
                            <div class="card-body abirthplace" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Add your birthplace</h4>
                                    <input type="text" name="birthplace">
                                    <input type="submit" name="submit" value="Add" class="btn btn-outline-primary btn-sm">    
                                </form>
                            </div>
                            <div class="card-body ebirthplace" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Edit your birthplace</h4>
                                    <input type="text" name="birthplace">
                                    <input type="submit" name="submit" value="Edit" class="btn btn-outline-secondary btn-sm">  
                                </form>
                            </div>

                    </div>
                    <script>
                        $(function(){
                            $(".babirthplace").click(function(){
                                $(".abirthplace").show();
                            });
                            $(".bebirthplace").click(function(){
                                $(".ebirthplace").show();
                            });
                        });
                    </script>
                    
                    
                    <div class="card bg-light mb-3" style="margin: 1em;">
                        <div class="card-header">
                            <h5>Job</h5>
                            <% if(user.getJob()==null){ %>
                            <button type="button" class="bajob btn btn-outline-primary btn-sm"><i class="fas fa-plus"></i> Add</button>
                            <% }else{ %>
                            <button type="button" class="bejob btn btn-outline-secondary btn-sm"><i class="fas fa-edit"></i> Edit</button>
                            <form action="UserServlet" method="post" class="linea">
                                <input hidden type="text" name="jobDelete" value="yes">
                                <button type="submit" class ="btn btn-outline-danger btn-sm"><i class="far fa-trash-alt"></i> Delete</button>     
                            </form>
                            <% } %>
                            
                        </div>
                            <div class="card-body">
                                <% if(user.getJob()!=null){ %>
                                <p class="card-text"><%=user.getJob()%></p>
                                <% } %>
                            </div>
                            <div class="card-body ajob" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Add your job</h4>
                                    <input type="text" name="job">
                                    <input type="submit" name="submit" value="Add" class="btn btn-outline-primary btn-sm">  
                                </form>
                            </div>
                            <div class="card-body ejob" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Edit your job</h4>
                                    <input type="text" name="job">
                                    <input type="submit" name="submit" value="Edit" class="btn btn-outline-secondary btn-sm">  
                                </form>
                            </div>
                    </div>
                    <script>
                        $(function(){
                            $(".bajob").click(function(){
                                $(".ajob").show();
                            });
                            $(".bejob").click(function(){
                                $(".ejob").show();
                            });
                        });
                    </script>
                    
                    <div class="card bg-light mb-3" style="margin: 1em;">
                        <div class="card-header">
                            <h5>Study Place</h5>
                            <% if(user.getStudyPlace()==null){ %>
                            <button type="button" class="bastudyPlace btn btn-outline-primary btn-sm"><i class="fas fa-plus"></i> Add</button>
                            <% } %>
                            <% if(user.getStudyPlace()!=null){ %>
                            <button type="button" class="bestudyPlace btn btn-outline-secondary btn-sm"><i class="fas fa-edit"></i> Edit</button>
                            <form action="UserServlet" method="post" class="linea">
                                <input hidden type="text" name="studyPlaceDelete" value="yes">
                                <button type="submit" class="btn btn-outline-danger btn-sm"><i class="far fa-trash-alt"></i> Delete</button>     
                            </form>
                            <% } %>
                            
                        </div>
                            <div class="card-body">
                                <% if(user.getStudyPlace()!=null){ %>
                                <p class="card-text"><%=user.getStudyPlace()%></p>
                                <% } %>
                            </div>
                            <div class="card-body astudyPlace" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Add your study place</h4>
                                    <input type="text" name="studyPlace">
                                    <input type="submit" name="submit" value="Add" class="btn btn-outline-primary btn-sm">  
                                </form>
                            </div>
                            <div class="card-body estudyPlace" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Edit your study place</h4>
                                    <input type="text" name="studyPlace">
                                    <input type="submit" name="submit" value="Edit" class="btn btn-outline-secondary btn-sm">  
                                </form>
                            </div>
                    </div>
                    <script>
                        $(function(){
                            $(".bastudyPlace").click(function(){
                                $(".astudyPlace").show();
                            });
                            $(".bestudyPlace").click(function(){
                                $(".estudyPlace").show();
                            });
                        });
                    </script>                    


                    <div class="card bg-light mb-3" style="margin: 1em">
                        <div class="card-header">
                            <h5>Website</h5>
                            <% if(user.getWebsite()==null){ %>
                            <button type="button" class="bawebsite btn btn-outline-primary btn-sm"><i class="fas fa-plus"></i> Add</button>
                            <% } %>
                            <% if(user.getWebsite()!=null){ %>
                            <button type="button" class="bewebsite btn btn-outline-secondary btn-sm"><i class="fas fa-edit"></i> Edit</button>
                            <form action="UserServlet" method="post" class="linea">
                                <input hidden type="text" name="websiteDelete" value="yes">
                                <button type="submit" class="btn btn-outline-danger btn-sm"><i class="far fa-trash-alt"></i> Delete</button>     
                            </form>
                            <% } %>
                            
                        </div>
                            <div class="card-body">
                                <% if(user.getWebsite()!=null){ %>
                                <p class="card-text"><%=user.getWebsite()%></p>
                                <% } %>
                            </div>
                            <div class="card-body awebsite" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Add your website</h4>
                                    <input type="text" name="website">
                                    <input type="submit" name="submit" value="Add" class="btn btn-outline-primary btn-sm">  
                                </form>
                            </div>
                            <div class="card-body ewebsite" style="display:none">
                                <form action="UserServlet" method="post">
                                    <h4>Edit your website</h4>
                                    <input type="text" name="website">
                                    <input type="submit" name="submit" value="Edit" class="btn btn-outline-secondary btn-sm">  
                                </form>
                            </div>
                    </div>
                    <script>
                        $(function(){
                            $(".bawebsite").click(function(){
                                $(".awebsite").show();
                            });
                            $(".bewebsite").click(function(){
                                $(".ewebsite").show();
                            });
                        });
                    </script>                    
                    
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
                            
                            
        <footer class="container-fluid text-center">
            <p>Footer Text</p>
        </footer>

    </body>
</html>
