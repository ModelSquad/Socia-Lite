<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="register.css" rel="stylesheet"">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img style="margin:10px" src="https://www.freeiconspng.com/uploads/no-image-icon-4.png" height=100 width=100 alt="User Icon" />
    </div>

    <!-- Register Form -->
    <form>
        <table class="center">
            <tr>
                <td>
                    <label for="email">Email</label>    
                </td>
                <td>
                    <input type="text" id="email" class="fadeIn second" name="email" placeholder="email">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">Password</label>                    
                </td>
                <td>
                    <input type="text" id="password" class="fadeIn third" name="password" placeholder="password">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="name">Name</label>
                </td>
                <td>
                    <input type="text" id="name" class="fadeIn second" name="name" placeholder="name">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Surname">Surname</label>
                </td>
                <td>
                    <input type="text" id="surname" class="fadeIn second" name="surname" placeholder="surname">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Nickname">Nickname</label>
                </td>
                <td>
                    <input type="text" id="nickname" class="fadeIn second" name="nickname" placeholder="nickname">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Birthdate">Birthdate</label>
                </td>
                <td>
                    <input type="date" id="birthdate" class="fadeIn second" name="birthdate" placeholder="birthdate">
                </td>
            </tr>  
        </table>
        <br>
        <input type="submit" class="fadeIn fourth" value="Register">
    </form>
  </div>
</div>