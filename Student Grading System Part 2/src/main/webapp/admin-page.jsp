<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" type="text/css" href="admin-page-style.css">
</head>
<body>
<div id="container">
    <h3>What would you like to do?</h3>
    <div class="action-buttons">
        <a href="/CreateUserServlet" methods="get">
            <button>Create a user</button>
        </a>
        <br>
        <a href="/DeleteUserServlet" methods="get">
            <button>Delete a user</button>
        </a>
        <br>
        <a href="/CreateCourseServlet" methods="get">
            <button>Create a course</button>
        </a>
        <br>
        <a href="/DeleteCourseServlet" methods="get">
            <button>Delete a course</button>
        </a>
    </div>
</div>
</body>
</html>
