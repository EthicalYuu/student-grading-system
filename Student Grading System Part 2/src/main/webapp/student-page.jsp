<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="student-page-style.css" />
    <title>Student Page</title>
</head>
<body>
<div id="container">
    <table class="grade-table">
        <thead>
        <tr>
            <th>Course Name</th>
            <th>Score</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${gradeList}">
            <tr>
                <td>${obj.courseName}</td>
                <td>${obj.score}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/LogoutServlet" methods="get">
        <button class="sign-out-button">Sign Out</button>
    </a>
</div>
</body>
</html>
