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
    Boolean error = (Boolean)request.getAttribute("error");
%>
    </head>
<body>
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img style="margin:10px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/S-Bahn_Austria.svg/128px-S-Bahn_Austria.svg.png" height=100 width=100 alt="User Icon" />
    </div>

    <!-- Login Form -->
    <form action="<%= request.getContextPath() %>/ForgetPasswordServlet" method="POST">
      <input type="email" id="login" class="fadeIn second" name="email" placeholder="user@email.com">
      <input type="submit" class="fadeIn fourth" value="Send email">
    </form>

    <% if(error != null && error) { %>
    <div class="loginMessage alert alert-danger" role="alert">
        <a id="message">
            This mail is not associated with any account.
        </a>
    </div>
    <% } else if(error != null && !error){ %>
    <div class="loginMessage alert alert-success" role="alert">
        <a id="message">
            An email was sent. Check your inbox.
        </a>
    </div>
    <% } %>

    <!-- Remind Password -->
    <div id="formFooter">
      <a class="underlineHover" href="<%= request.getContextPath() %>/IndexServlet">Back to login</a>
    </div>
  </div>
</div>
</body>
</html>
