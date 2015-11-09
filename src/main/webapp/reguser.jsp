<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration: user</title>
    <link href="css/primary-style.css" rel="stylesheet">
    <link href="css/registration.css" rel="stylesheet">
    <link href="css/index-style.css" rel="stylesheet">

</head>
<body>
<%
    String error = null;
    if (request.getAttribute("error") != null) {
        error = request.getAttribute("error").toString();
        request.removeAttribute("error");
    }
    if (error != null) {
%>
<script language="JavaScript">
    alert("<%=error%>");
</script>
<%
    }
%>
<div class="wrapper">
    <header class="header">
        <div class="index-link-wrapper">
            <a href="index">
                <div class="index-link">
                    <h2>Restaurant Reservation System</h2>
                </div>
            </a>
        </div>

    </header>

    <div class="content-wrapper">

        <div class="registration-form-wrapper">

            <form action="/registrationuser" method="POST">

                <div class="info-block">
                    <div class="label">Login:</div>
                    <div class="labeled"><input type="text" required name="login" value=""></div>
                </div>

                <div class="info-block">
                    <div class="label">Password:</div>
                    <div class="labeled"><input type="text" required name="password" value=""></div>
                </div>

                <div class="info-block">
                    <div class="label">Name:</div>
                    <div class="labeled"><input type="text" required name="name" value=""></div>
                </div>

                <div class="info-block">
                    <div class="label">Phone number:</div>
                    <div class="labeled"><input type="tel" required name="phonenumber">
                    </div>
                </div>

                <input type="submit" name="action" value="Register">
            </form>
            <form action="/index" method="POST">
                <input type="submit" name="action" value="Cancel">
            </form>

        </div>
    </div>
</div>
</body>
</html>
