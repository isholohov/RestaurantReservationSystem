<%@ page import="core.RegisteredUser" %>
<%@ page import="core.Restaurant" %>
<%@ page import="core.Review" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");%>
    <title><%=restaurant.getName()%>
    </title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="css/primary-style.css" rel="stylesheet">
    <link href="css/restaurant-profile-style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/anytime.5.1.2.min.css"/>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/anytime.5.1.2.min.js"></script>
</head>

<body>
<div class="wrapper">

    <header class="header">
        <jsp:include page="/header"></jsp:include>
    </header>

    <div class="content-wrapper">
        <div class="upper-block-wrapper">

            <div class="upper-block">

                <div class="restaurant-information-bar">


                    <div class="info-block">
                        <div class="label"><h4>Name:</h4></div>
                        <div class="labeled"><h5><%=restaurant.getName()%>
                        </h5></div>
                    </div>

                    <div class="info-block">
                        <div class="label"><h4>Description:</h4></div>
                        <div class="labeled"><h5><%=restaurant.getFullDescription()%>
                        </h5></div>
                    </div>

                    <div class="info-block">
                        <div class="label"><h4>Average bill:</h4></div>
                        <div class="labeled"><h5><%=restaurant.getAverageBill()%>
                        </h5></div>
                    </div>

                    <div class="info-block">
                        <div class="label"><h4>Closest station:</h4></div>
                        <div class="labeled"><h5><%=restaurant.getClosestStation()%>
                        </h5></div>
                    </div>

                    <div class="info-block">
                        <div class="label"><h4>Address:</h4></div>
                        <div class="labeled"><h5><%=restaurant.getAddress()%>
                        </h5></div>
                    </div>

                    <div class="info-block">
                        <div class="label"><h4>Phone number:</h4></div>
                        <div class="labeled"><h5><%=restaurant.getPhoneNumber()%>
                        </h5></div>
                    </div>

                    <div class="info-block">
                        <div class="label"><h4>Opening hours:</h4></div>
                        <div class="labeled"><h5><%=restaurant.getOpeningHours()%>
                        </h5></div>
                    </div>


                    <%if (restaurant.getRating() != 0) {%>

                    <div class="info-block">
                        <div class="label"><h4>Rating: </h4></div>
                        <div class="labeled"><h5><%=restaurant.getRating()%>
                        </h5></div>
                    </div>

                    <%
                        }
                    %>
                </div>

                <div class="reservation-bar">
                    <%
                        if (request.getSession().getAttribute("usertype") != null) {
                            if (request.getSession().getAttribute("usertype").toString().equals("registereduser")) {
                                RegisteredUser registeredUser = (RegisteredUser) request.getSession().getAttribute("registereduser");
                    %>
                    <form name="reservation" action="/reservation" method="POST">

                        <div class="reservation-info">

                            <div class="reservation-field">Number of persons:</div>
                            <div class="reservation-value">
                                <input type="text" name="persons" value="" size="2">
                            </div>
                        </div>

                        <div class="reservation-info">

                            <div class="reservation-field">Date:</div>

                            <div class="reservation-value">
                                <input type="text" id="date" name="date" size="8">
                                <button id="datePicker">
                                    <img src="sources/calendar.png" alt="[calendar icon]"/>
                                </button>
                            </div>
                        </div>

                        <div class="reservation-info">

                            <div class="reservation-field">Time:</div>

                            <div class="reservation-value">
                                <input type="text" id="time" name="time" size="8">
                                <button id="timePicker">
                                    <img src="sources/clock.png" alt="[clock icon]"/>
                                </button>
                            </div>
                        </div>

                        <script>
                            var dateFormat = "%Y-%m-%d";
                            var timeFormat = "%H:%i";
                            var dateConverter = new AnyTime.Converter({format: dateFormat})
                            $('#datePicker').click(
                                    function (e) {
                                        $('#date').AnyTime_noPicker().AnyTime_picker(
                                                {
                                                    earliest: dateConverter.format(new Date()),
                                                    format: dateFormat,
                                                    firstDow: 1,
                                                    labelTitle: "Choose date"
                                                }).focus();
                                        e.preventDefault();
                                    });
                            $('#timePicker').click(
                                    function (e) {
                                        $('#time').AnyTime_noPicker().AnyTime_picker(
                                                {
                                                    format: timeFormat,
                                                    labelTitle: "Choose time"
                                                }).focus();
                                        e.preventDefault();
                                    });
                        </script>
                        <input type="hidden" name="idRestaurant" value="<%=restaurant.getIdRestaurant()%>">
                        <input type="hidden" name="idRegisteredUser" value="<%=registeredUser.getIdRegisteredUser()%>">

                        <div class="reservation-submit">
                            <input type="submit" name="submit" value="Reserve">
                        </div>

                    </form>
                    <%
                        }
                    } else {
                    %>
                    <h4 style="padding-top: 40px;">Only authorized users can make a reservation.</h4>
                    <%
                        }
                    %>
                </div>

            </div>
        </div>


        <div class="reviews-bars-wrapper">
            <%
                if (request.getAttribute("reviews") != null) {

                    List allReview = (List) request.getAttribute("reviews");

                    if (!allReview.isEmpty()) {
            %>
            <div id="review-bar-title"><h5>Reviews:</h5></div>

            <%
            } else {
            %>
            <div id="review-bar-empty"><h4>You can make first review.</h4></div>
            <%
                }

                for (Object objReview : allReview) {
                    Review review = (Review) objReview;
            %>
            <div class="review-bar">
                &#32;<%=review.getRating() + "/5 "%>
                &#32;-&#32;at&#32;<%=review.getDate()%>

                <p>Comment: <%=review.getComment()%>
                </p>
            </div>
            <%
                    }
                }

                if (request.getSession().getAttribute("usertype") != null) {
                    if (request.getSession().getAttribute("usertype").toString().equals("registereduser")) {
                        RegisteredUser registeredUser = (RegisteredUser) request.getSession().getAttribute("registereduser");
            %>
            <div class="review-bar-wrapper">
                <form name="review" action="/review" method="POST">
                    <div id="rating-field">
                        <div class="label"><h4>Change rating:</h4></div>
                        <select name="rating">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </div>

                    <Br>

                    <div class="label"><h4>Comment: </h4></div>
                    <textarea id="comment" type="text" name="comment" value="" required></textarea><Br>

                    <input type="hidden" name="idRestaurant" value="<%=restaurant.getIdRestaurant()%>">
                    <input type="hidden" name="idRegisteredUser" value="<%=registeredUser.getIdRegisteredUser()%>">
                    <input id="review-button" type="submit" name="review" value="Add review">
                </form>
            </div>
            <%
                    }
                }
            %>


        </div>
    </div>
</div>

</body>
</html>
