<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurant Reservation System</title>
</head>
<body>

<div class="index-link-wrapper">
    <a href="index">
        <div class="index-link">
            <h2>Restaurant Reservation System</h2>
        </div>
    </a>
</div>

<div class="log-in">
    <form action="dispatcher" method="POST">
        <input type="text" name="login" size="15" value="" placeholder="Login">
        <input type="password" name="password" size="15" value="" placeholder="Password">
        <select name="usertype">
            <option value="user">User</option>
            <option value="restaurant">Restaurant</option>
        </select>
        <input type="submit" name="action" value="Login">
        or
        <input type="submit" name="action" value="Sign up">
    </form>
</div>
</body>
</html>
