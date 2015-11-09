<%@ page import="core.Reservation" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilia_Sholokhov
  Date: 13/10/15
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservations</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="css/primary-style.css" rel="stylesheet">
    <link href="css/index-style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <header class="header">
        <jsp:include page="/header"></jsp:include>
    </header>
    <div class="reservation-bars-wrapper">
        <%
            if (request.getAttribute("reservations") != null) {
                List reservations = (List) request.getAttribute("reservations");
                for (Object objReservation : reservations) {
                    Reservation reservation = (Reservation) objReservation;
        %>
        <div class="reservation-bar">
            <p>Number of persons:<%=reservation.getPersons()%>
            </p>

            <p>Date: <%=reservation.getDate()%>
            </p>

            <p>Time: <%=reservation.getTime()%>
            </p>
            <%if (reservation.getIsAccepted() == 0) {%>
            Not accepted.
            <%}%>
            <%if (reservation.getIsAccepted() != 0) {%>
            Accepted.
            <%}%>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
