<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
    <link rel="stylesheet" type="text/css" href="delete-user-style.css">
</head>
<body>
<div id="form-container">
    <form action="/DeleteUserAction" method="post">
        Enter User ID: <input type="text" name="userId"><br>
        Enter password: <input type="password" name="password"><br>
        <input type="submit">
    </form>
    <p>${message}</p>
</div>
</body>
</html>
