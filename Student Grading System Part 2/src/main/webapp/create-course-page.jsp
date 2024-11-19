<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Course</title>
    <link rel="stylesheet" type="text/css" href="create-course-style.css">
</head>
<body>
<div id="form-container">
<form action="/CreateCourseAction" method="post">
        Enter Course Name: <input type="text" name="courseName"><br>
        Enter Teacher ID: <input type="text" name="teacherId"><br>
        <input type="submit">
    </form>
    <p>${message}</p>
</div>
</body>
</html>
