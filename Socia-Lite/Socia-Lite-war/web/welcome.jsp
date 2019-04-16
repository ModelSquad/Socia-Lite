<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
         <%@ include file="welcome.css"%>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
    <%@include file="welcome.js" %>
    </script>
  </head>
  <body>
  <nav class="navbar navbar-expand-lg navbar-dark">
<a class="navbar-brand" href="#">Navbar</a>
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
      <div class="col-sm-8 text-left">
        <div class="jumbotron jumbotron-post">
            <h4>Comparte tus experiencias</h4>
            <textarea class="form-control text-area-post" placeholder="Escribe algo aquí"></textarea>
            <div class="btn-group post-emoji post-actions" role="group">
              <button type="button" class="btn btn-post btn-emoji">❤️</button>
              <button type="button" class="btn btn-post btn-emoji">😉</button>
              <button type="button" class="btn btn-post btn-emoji">😘</button>
              <button type="button" class="btn btn-post btn-emoji">😂</button>
            </div>
            <div class="btn-group post-button post-actions" role="group">
              <button type="button" class="btn btn-post">Subir foto</button>
              <button type="button" class="btn btn-post">Enviar</button>
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

    <footer class="container-fluid text-center">
      <p>Footer Text</p>
    </footer>

  </body>
</html>
