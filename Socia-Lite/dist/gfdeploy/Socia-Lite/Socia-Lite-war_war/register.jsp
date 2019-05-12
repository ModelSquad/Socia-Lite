<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String email = (String) request.getAttribute("email");
    String password = (String) request.getAttribute("password");
    String repeatPassword = (String) request.getAttribute("repeatPassword");
    String name = (String) request.getAttribute("name");
    String surname = (String) request.getAttribute("surname");
    String nickname = (String) request.getAttribute("nickname");
    String birthdate = (String) request.getAttribute("birthdate");
    String strErrors = (String) request.getAttribute("strErrors");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <style>
            <%@ include file="stylesheets/register.css"%>
        </style>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>Register</title>
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <!-- Icon -->
                <div class="fadeIn first">
                    <img style="margin:10px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/S-Bahn_Austria.svg/128px-S-Bahn_Austria.svg.png" height=100 width=100 alt="User Icon" />
                </div>

                <!-- Register Form -->
                <form method="post" action="RegisterServlet" name="registerUser" >
                    <table class="center">
                        <tr>
                            <td>
                                <label for="email">Email</label>    
                            </td>
                            <td>
                                <input type="email" id="email" class="fadeIn second" name="email" placeholder="email" <% if(email!=null) %> value="<%= email%>" />
                            </td>
                        </tr>                   
                        <% if(strErrors !=null && strErrors.contains("emailNull")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            Enter your email
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        <% if(strErrors !=null && strErrors.contains("emailExists")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            This email already exists, please enter another email or sign in.
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        <tr>
                            <td>
                                <label for="password">Password</label>                    
                            </td>
                            <td>
                                <input type="password" id="password" class="fadeIn third" name="password" placeholder=" password" <% if(password!=null) %> value="<%= password%>"/>
                            </td>
                        </tr>
                        <% if(strErrors !=null && strErrors.contains("password")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            Please enter a password
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        <tr>
                            <td>
                                <label for="repeatPassword">Repeat password</label>                    
                            </td>
                            <td>
                                <input type="password" id="repeatPassword" class="fadeIn third" name="repeatPassword" placeholder="repeat password" <% if(repeatPassword!=null) %> value="<%= repeatPassword%>"/>
                            </td>
                        </tr>
                        <% if(strErrors !=null && strErrors.contains("repeatPassword")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            Your passwords do not match
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        <tr>
                            <td>
                                <label for="name">Name</label>
                            </td>
                            <td>
                                <input type="text" id="name" class="fadeIn second" name="name" placeholder="name" <% if(name!=null) %> value="<%= name%>" />
                            </td>
                        </tr>
                        <% if(strErrors !=null && strErrors.contains("justname")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            Enter your name
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        <tr>
                            <td>
                                <label for="Surname">Surname</label>
                            </td>
                            <td>
                                <input type="text" id="surname" class="fadeIn second" name="surname" placeholder="surname" <% if(surname!=null) %> value="<%= surname%>" />
                            </td>
                        </tr>
                        <% if(strErrors !=null && strErrors.contains("surname")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            Enter your surname
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        <tr>
                            <td>
                                <label for="Nickname">Nickname</label>
                            </td>
                            <td>
                                <input type="text" id="nickname" class="fadeIn second" name="nickname" placeholder="nickname" <% if(nickname!=null) %> value="<%= nickname%>" />
                            </td>
                        </tr>
                        <% if(strErrors !=null && strErrors.contains("nickname")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            Enter your nickname
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                        <tr>
                            <td>
                                <label for="Birthdate">Birthdate</label>
                            </td>
                            <td>
                                <input type="date" id="birthdate" class="fadeIn second" name="birthdate" placeholder="birthdate" <% if(birthdate!=null) %> value="<%= birthdate%>" />
                            </td>
                        </tr>
                        <% if(strErrors !=null && strErrors.contains("birthdate")) { %>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="loginMessage alert alert-danger" role="alert">
                                        <a id="message">
                                            Enter your birthdate
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                    </table>
                    <br>
                    <input type="submit" class="fadeIn fourth" value="Register"/>
                </form>
            </div>
        </div>
    </body>
</html>