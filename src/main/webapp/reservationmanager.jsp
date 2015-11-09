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
    <link href="css/reservation.css" rel="stylesheet">
</head>
<body>
<script language="JavaScript">
    function SubmitForm(id) {
        document.accept.idReservation.value = id;
        document.accept.submit();
    }
</script>
<form name="accept" action="/accept" method="POST">
    <input type="hidden" name="idReservation">
</form>
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

            <div class="info-block">
                <div class="label">Number of persons:</div>
                <div class="labeled"><%=reservation.getPersons()%>
                </div>
            </div>

            <div class="info-block">

                <div class="label">Date:</div>
                <div class="labeled"><%=reservation.getDate()%>
                </div>
            </div>

            <div class="info-block">

                <div class="label">Time:</div>
                <div class="labeled"><%=reservation.getTime()%>
                </div>
            </div>

            <%if (reservation.getIsAccepted() == 0) {%>
            <a href="javascript:SubmitForm(<%=reservation.getIdReservation()%>)">Accept this reservation</a>
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
