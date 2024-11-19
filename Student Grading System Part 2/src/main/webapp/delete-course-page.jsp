<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Course</title>
    <link rel="stylesheet" type="text/css" href="delete-course-style.css">
</head>
<body>
<div id="form-container">
    <form action="/DeleteCourseAction" method="post">
        Enter Course ID: <input type="text" name="courseId"><br>
        <input type="submit">
    </form>
    <p>${message}</p>
</div>
</body>
</html>
