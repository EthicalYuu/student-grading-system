<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="login-page-style.css">
</head>
<body>
<div id="form-container">
    <form action="LoginServlet" method="post">
        Enter username: <input type="text" name="username"><br>
        Enter password: <input type="password" name="password"><br>
        <input type="submit" value="login">
    </form>
</div>
</body>
</html>