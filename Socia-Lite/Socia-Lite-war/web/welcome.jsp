<%@page import="socialite.entity.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="socialite.entity.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SociaLite - Homepage</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            <%@ include file="welcome.css"%>
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

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <script>
            <%@ include file="welcome.js"%>
        </script>
    </head>
    <body>
        <%
            String currentPath = request.getContextPath();
            Vector< Post> posts = null;
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(currentPath);
            } else {
                posts = (Vector<Post>) request.getSession().getAttribute("posts");
            }
        %>
        <nav class="navbar navbar-expand-lg navbar-dark">
            <a class="navbar-brand" href="#">Socia-Lite</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
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

                    <!-- ADD POST -->
                    <form method="POST" action="AddPostServlet">
                        <div class="jumbotron jumbotron-post">
                            <h4>Share your experiences</h4>
                            <textarea name="post-text" class="form-control text-area-post" placeholder="Write something here..."></textarea>
                            <div class="btn-group post-emoji post-actions" role="group">
                                <button type="button" class="btn btn-post btn-emoji">❤️</button>
                                <button type="button" class="btn btn-post btn-emoji">😉</button>
                                <button type="button" class="btn btn-post btn-emoji">😘</button>
                                <button type="button" class="btn btn-post btn-emoji">😂</button>
                            </div>

                            <div class="btn-group post-button post-actions" role="group">
                                <label class="btn btn-post">
                                    Upload picture <input type="file" id="img-upload" hidden multiple>
                                </label>
                                <input type="submit" class="btn btn-post" value="Send">
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
                                            <a class="dropdown-item" data-href="DeletePostServlet?id=<%=post.getIdPost()%>" data-target="#confirm-delete" class="trigger-btn" data-toggle="modal">Delete</a>
                                            <a class="dropdown-item" href="EditPostServlet?id=<%=post.getIdPost()%>">Edit</a>
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                            </div>

                        </div>
                        <div class="card-body">
                            <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o" aria-hidden="true"></i> <%=post.getDate()%> </div>
                            <a class="card-link" href="#">
                                <h5 class="card-title"><%=post.getTitle()%></h5>
                            </a>
                            <p class="card-text">
                                <%= post.getText()%>
                            </p>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="card-link"><i class="fa fa-gittip"></i> Like</a>
                            <a href="#" class="card-link"><i class="fa fa-comment"></i> Comentario </a>
                            <a href="#" class="card-link"><i class="fa fa-mail-forward"></i> Compartir </a>
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


                    <!-- POST -->


                    <div class="card gedf-card " style="width: 100%;">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="mr-2">
                                        <img class="rounded-circle" width="45" src="https://avatars0.githubusercontent.com/u/33965662?s=460&v=4" alt="">
                                    </div>
                                    <div class="ml-2">
                                        <div class="h5 m-0">@xFJA</div>
                                        <div class="h7 text-muted"> Franciso Jiménez Aguilera </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="dropdown">
                                        <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fa fa-ellipsis-h"></i>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                            <div class="h6 dropdown-header">Opciones</div>
                                            <a class="dropdown-item" href="#">Editar</a>
                                            <a class="dropdown-item" href="#">Eliminar</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="card-body">
                            <div class="text-muted h7 mb-2"> </div>
                            <a class="card-link" href="#">
                                <h5 class="card-title">Incendio en la catedral de Notre Dame de París</h5>
                            </a>

                            <p class="card-text">
                                La catedral de Notre Dame de París sufrió un incendio que se inició en la tarde del 15 de abril de 2019 
                                en el tejado del edificio y ocasionó daños considerables. La aguja de la catedral y el tejado se derrumbaron 
                                y tanto el espacio interior como muchos bienes muebles se dañaron gravemente. El incendio se produjo 
                                accidentalmente, por causas aún no conocidas. En 24 horas, se recaudaron más de 800 millones de euros para 
                                la reconstrucción.
                            </p>
                        </div>
                        <div class="card-footer">
                            <a href="#" class="card-link"><i class="fa fa-gittip"></i> Like</a>
                            <a href="#" class="card-link"><i class="fa fa-comment"></i> Comentario </a>
                            <a href="#" class="card-link"><i class="fa fa-mail-forward"></i> Compartir </a>
                        </div>
                    </div>

                    <!-- POST WITH IMAGE -->

                    <div class="card gedf-card " style="width: 100%;">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="mr-2">
                                        <img class="rounded-circle" width="45" src="https://avatars0.githubusercontent.com/u/33965662?s=460&v=4" alt="">
                                    </div>
                                    <div class="ml-2">
                                        <div class="h5 m-0">@xFJA</div>
                                        <div class="h7 text-muted"> Franciso Jiménez Aguilera</div>
                                    </div>
                                </div>
                                <div>
                                    <div class="dropdown">
                                        <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fa fa-ellipsis-h"></i>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                            <div class="h6 dropdown-header">Configuration</div>
                                            <a class="dropdown-item" href="#">Save</a>
                                            <a class="dropdown-item" href="#">Hide</a>
                                            <a class="dropdown-item" href="#">Report</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <a data-fancybox="gallery" href="https://cdn.theatlantic.com/assets/media/img/photo/2019/04/photos-notre-dame-cathedral-burns-p/n01_1137422977/main_900.jpg?1555413566">
                            <img class="card-img-top post-image" src="https://cdn.theatlantic.com/assets/media/img/photo/2019/04/photos-notre-dame-cathedral-burns-p/n01_1137422977/main_900.jpg?1555413566" alt="Card image">
                        </a>
                        <div class="card-body">
                            <div class="text-muted h7 mb-2"></div>
                            <a class="card-link" href="#">
                                <h5 class="card-title">Incendio en la catedral de Notre Dame de París</h5>
                            </a>

                            <p class="card-text">
                                La catedral de Notre Dame de París sufrió un incendio que se inició en la tarde del 15 de abril de 2019 
                                en el tejado del edificio y ocasionó daños considerables. La aguja de la catedral y el tejado se derrumbaron 
                                y tanto el espacio interior como muchos bienes muebles se dañaron gravemente. El incendio se produjo 
                                accidentalmente, por causas aún no conocidas. En 24 horas, se recaudaron más de 800 millones de euros para 
                                la reconstrucción.
                            </p>

                        </div>
                        <div class="card-footer">
                            <a href="#" class="card-link"><i class="fa fa-gittip"></i> Like</a>
                            <a href="#" class="card-link"><i class="fa fa-comment"></i> Comentario </a>
                            <a href="#" class="card-link"><i class="fa fa-mail-forward"></i> Compartir </a>
                        </div>
                    </div>

                    <!-- MULTIPLE IMAGES -->

                    <div class="card gedf-card " style="width: 100%;">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="mr-2">
                                        <img class="rounded-circle" width="45" src="https://avatars0.githubusercontent.com/u/33965662?s=460&v=4" alt="">
                                    </div>
                                    <div class="ml-2">
                                        <div class="h5 m-0">@xFJA</div>
                                        <div class="h7 text-muted"> Franciso Jiménez Aguilera</div>
                                    </div>
                                </div>
                                <div>
                                    <div class="dropdown">
                                        <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fa fa-ellipsis-h"></i>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                            <div class="h6 dropdown-header">Configuration</div>
                                            <a class="dropdown-item" href="#">Save</a>
                                            <a class="dropdown-item" href="#">Hide</a>
                                            <a class="dropdown-item" href="#">Report</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="carousel-item active post-image">
                                    <img class="d-block w-100" src="https://cdn.theatlantic.com/assets/media/img/photo/2019/04/photos-notre-dame-cathedral-burns-p/n01_1137422977/main_900.jpg?1555413566" >
                                </div>
                                <div class="carousel-item post-image">
                                    <a data-fancybox="gallery" href="https://cdn.theatlantic.com/assets/media/img/mt/2019/04/RTX6RY5R/lead_720_405.jpg?mod=1555434995">
                                        <img class="card-img-top post-image" src="https://cdn.theatlantic.com/assets/media/img/mt/2019/04/RTX6RY5R/lead_720_405.jpg?mod=1555434995" alt="Card image">
                                    </a>
                                </div>
                                <div class="carousel-item post-image">
                                    <a data-fancybox="gallery" href="https://i.cbc.ca/1.5099291.1555365522!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_780/france-notre-dame-fire.jpg">
                                        <img class="card-img-top post-image" src="https://i.cbc.ca/1.5099291.1555365522!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_780/france-notre-dame-fire.jpg" alt="Card image">
                                    </a>
                                </div>
                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Siguiente</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Anterior</span>
                            </a>
                        </div>
                        <div class="card-body">
                            <div class="text-muted h7 mb-2"></div>
                            <a class="card-link" href="#">
                                <h5 class="card-title">Incendio en la catedral de Notre Dame de París</h5>
                            </a>

                            <p class="card-text">
                                La catedral de Notre Dame de París sufrió un incendio que se inició en la tarde del 15 de abril de 2019 
                                en el tejado del edificio y ocasionó daños considerables. La aguja de la catedral y el tejado se derrumbaron 
                                y tanto el espacio interior como muchos bienes muebles se dañaron gravemente. El incendio se produjo 
                                accidentalmente, por causas aún no conocidas. En 24 horas, se recaudaron más de 800 millones de euros para 
                                la reconstrucción.
                            </p>

                        </div>
                        <div class="card-footer">
                            <a href="#" class="card-link"><i class="fa fa-gittip"></i> Like</a>
                            <a href="#" class="card-link"><i class="fa fa-comment"></i> Comentario </a>
                            <a href="#" class="card-link"><i class="fa fa-mail-forward"></i> Compartir </a>
                        </div>
                    </div>



                </div>



                <!-- Right colum -->

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

<!-- SCRIPT DELETE POST WARNING -->
<script>
    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));

        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
    });
</script>
