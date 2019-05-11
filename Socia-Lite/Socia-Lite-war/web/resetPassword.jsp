<%@page import="socialite.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<style>
     <%@ include file="stylesheets/login.css"%>
</style>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title> SociaLite - Forget Password </title>

<%
    Boolean expired = (Boolean)request.getAttribute("expired");
    Boolean error = (Boolean)request.getAttribute("error");
    Boolean success = (Boolean)request.getAttribute("success");
    User user = (User)request.getAttribute("user");
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
    <% if(success != null && success) { %>
    
    <div class="loginMessage alert alert-success" role="alert">
            <a id="message">
                Password changed successfully.
            </a>
        </div>
    
    <% } else { %>
     <% if(expired != null && expired) { %>
        <div class="loginMessage alert alert-danger" role="alert">
            <a id="message">
                Error. The action could not be taken. The link may has expired.
            </a>
        </div>
    <% } else { %>
        <form action="<%= request.getContextPath() %>/ResetPasswordServlet" method="POST">
          <a class="fadeIn second">
              Password:
          </a>
          <input type="hidden" name="userId" value="<%= user.getIdUser() %>">
          <input type="password" id="login" class="fadeIn second" name="password" placeholder="user@email.com">
           <a class="fadeIn second">
              Repeat password:
          </a>
          <input type="password" id="login" class="fadeIn second" name="checkPassword" placeholder="user@email.com">
          <input type="submit" class="fadeIn fourth" value="Reset password">
        </form>
            <% if(error != null && error) {%>
            <div class="loginMessage alert alert-danger" role="alert">
            <a id="message">
                Passwords don't match. Try again.
            </a>
            </div>
            <% } %>
        <% } %>
    <% } %> 
    <!-- Remind Password -->
    <div id="formFooter">
      <a class="underlineHover" href="<%= request.getContextPath() %>/IndexServlet">Back to login</a>
    </div>
  </div>
</div>
</body>
</html>
