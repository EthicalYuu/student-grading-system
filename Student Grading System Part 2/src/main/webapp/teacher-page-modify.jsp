<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Grade Modify</title>
    <link rel="stylesheet" type="text/css" href="teacher-modify-style.css"/>
</head>
<body>
<div id="form-container">
    <form action="/ModifyAction" method="post">
        Enter Course ID: <input type="text" name="courseId"><br>
        Enter User ID: <input type="text" name="userId"><br>
        Enter New Score: <input type="text" name="newScore"><br>
        <input type="submit" value="modify">
    </form>
    <p>${message}</p>
</div>
</body>
</html>