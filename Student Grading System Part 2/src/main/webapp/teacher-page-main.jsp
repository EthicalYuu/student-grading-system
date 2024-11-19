<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="student-style.css"/>
    <title>Student Page</title>
    <link rel="stylesheet" type="text/css" href="teacher-main-style.css" />
</head>
<body>
<div id="container">
    <h3>What would you like to do?</h3>
    <div class="action-buttons">
        <a href="/ModifyServlet" methods="get">
            <button>Modify a grade</button>
        </a>
        <a href="/ViewClassesServlet" methods="get">
            <button>View your classes</button>
        </a>
        <a href="/LogoutServlet" methods="get">
            <button>Sign Out</button>
        </a>
    </div>
</div>
</body>
</html>
