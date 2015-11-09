<%@ page import="core.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Welcome!</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="css/primary-style.css" rel="stylesheet">
    <link href="css/index-style.css" rel="stylesheet">
</head>

<body>
<div class="wrapper">
    <%
        String message = null;
        if (request.getAttribute("message") != null) {
            message = request.getAttribute("message").toString();
            request.removeAttribute("message");
        }
        if (message != null) {
    %>
    <script language="JavaScript">
        alert("<%=message%>");
    </script>
    <%
        }
    %>
    <%
        String errorauthentication = null;
        if (request.getAttribute("errorauthentication") != null) {
            errorauthentication = request.getAttribute("errorauthentication").toString();
            request.removeAttribute("errorauthentication");
        }
        if (errorauthentication != null) {
    %>
    <script language="JavaScript">
        alert("<%=errorauthentication%>");
    </script>
    <%
        }
    %>

    <script language="JavaScript">
        function SubmitForm(id) {
            document.referToProfile.id.value = id;
            document.referToProfile.submit();
        }
    </script>
    <form name="referToProfile" action="/profile" method="GET">
        <input type="hidden" name="id">
    </form>

    <div class="wrapper">

        <header class="header">
            <jsp:include page="/header"></jsp:include>
        </header>

        <div class="content-wrapper">
            <div class="site-description">Site description</div>

            <div class="filter-wrapper">
                <div class="filter">
                    <jsp:include page="/filter"></jsp:include>
                </div>
            </div>
        </div>
        <div class="restaurants-bars-wrapper">
            <%
                List allRestaurants = (List) request.getAttribute("allRestaurants");
                for (Object restaurantFromList : allRestaurants) {
                    Restaurant restaurant = (Restaurant) restaurantFromList;
            %>
            <div class="restaurant-bar">

                <p><%=restaurant.getName()%>
                </p>

                <p><%=restaurant.getShortDescription()%>
                </p>
                <a href="javascript:SubmitForm(<%=restaurant.getIdRestaurant()%>)">More information</a>
            </div>
            <%}%>
        </div>
    </div>
</div>
</body>
</html>