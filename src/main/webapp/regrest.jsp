<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration restaurant</title>

    <link href="css/primary-style.css" rel="stylesheet">
    <link href="css/registration.css" rel="stylesheet">
    <link href="css/index-style.css" rel="stylesheet">
</head>
<body>

<div class="wrapper">
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

            <form action="/registrationrestaurant" method="POST">

                <div class="info-block">
                    <div class="label">Name:</div>
                    <div class="labeled"><input type="text" name="name" value=""></div>
                </div>


                <div class="info-block">
                    <div class="label">Address:</div>
                    <div class="labeled"><input type="text" name="address" value=""></div>
                </div>


                <div class="info-block">
                    <div class="label">Closest metro station:</div>
                    <div class="labeled"><input type="text" name="closeststation"></div>
                </div>


                <div class="info-block">
                    <div class="label">Phone number:</div>
                    <div class="labeled"><input type="tel" name="phonenumber"></div>
                </div>


                <div class="info-block">
                    <div class="label">Opening hours:</div>
                    <div class="labeled"><input type="text" name="openinghours" value=""></div>
                </div>


                <div class="info-block">
                    <div class="label">Average bill:</div>
                    <div class="labeled"><input type="text" name="averagebill" value=""></div>
                </div>


                <div class="info-block">
                    <div class="label">Short description:</div>
                    <div class="labeled"><textarea type="text" name="shortdescription" value=""></textarea></div>
                </div>


                <div class="info-block">
                    <div class="label">Full description:</div>
                    <div class="labeled"><textarea type="text" name="fulldescription" value=""></textarea></div>
                </div>


                <div class="info-block">
                    <div class="label">Login:</div>
                    <div class="labeled"><input type="text" name="login"></div>
                </div>


                <div class="info-block">
                    <div class="label">Password:</div>
                    <div class="labeled"><input type="text" name="password"></div>
                </div>

                <div class="info-block">
                    <div class="label">Dish Tag:</div>
                    <div class="labeled"><input type="text" name="dishtag1"></div>
                </div>

                <div class="info-block">
                    <div class="label">Dish Tag:</div>
                    <div class="labeled"><input type="text" name="dishtag2"></div>
                </div>

                <div class="info-block">
                    <div class="label">Dish Tag:</div>
                    <div class="labeled"><input type="text" name="dishtag3"></div>
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
