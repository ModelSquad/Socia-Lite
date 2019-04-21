<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<style>
     <%@ include file="login.css"%>
</style>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title> SociaLite - Login </title>

<%
    Boolean errorLogin = (Boolean)request.getAttribute("errorLogin");
%>
    </head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img style="margin:10px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png" height=100 width=100 alt="User Icon" />
    </div>

    <!-- Login Form -->
    <form action="#" method="POST">
      <input type="email" id="login" class="fadeIn second" name="email" placeholder="user@email.com">
      <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
      <input type="submit" class="fadeIn fourth" value="Log In">
    </form>

    <% if(errorLogin != null && errorLogin) { %>
    <div class="loginMessage alert alert-danger" role="alert">
        <a id="message">
            Usuario o contrase&ntilde;a incorrecta
        </a>
    </div>
    <% } %>

    <!-- Remind Password -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a>
    </div>
  </div>
</div>
</body>
</html>
