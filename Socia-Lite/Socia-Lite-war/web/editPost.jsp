
<%@page import="socialite.entity.User"%>
<%@page import="socialite.entity.Post"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SociaLite - Edit Post</title>
        <style>
            <%@ include file="editPost.css"%>
        </style>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous">
    </head>
    <body>
        <%
            String currentPath = request.getContextPath();
            Post post = null;
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(currentPath);
            } else {
                post = (Post) request.getAttribute("post");
            }
        %>
        <form action="SavePostServlet" method="POST" id="editForm">
            <input type="hidden" name="id" value="<%= post.getIdPost()%>" />

            <div id="cardElem">
                <div class="card gedf-card">
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
                            <div>
                            </div>
                        </div>

                    </div>
                    <div class="card-body">
                        <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o" aria-hidden="true"></i> <%=post.getDate()%> </div>
                        <div class="md-form">
                            <a href="#"><i class="fas fa-pencil-alt prefix"> Title</i></a>
                            <textarea name="title" id="formTitle" class="md-textarea form-control card-title " rows="3" form="editForm"><%=post.getTitle()%></textarea>
                        </div>
                        <div class="md-form">
                            <a href="#"><i class="fas fa-pencil-alt prefix"> Body</i></a>
                            <textarea name="text-body" id="formText" class="md-textarea form-control" rows="3" form="editForm"><%=post.getText()%></textarea>
                        </div>
                    </div>
                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary save-btn">Save</button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
