<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Classes</title>
    <link rel="stylesheet" type="text/css" href="teacher-view-style.css" />
</head>
<body>
<div id="container">
<%--    <c:forEach items="${courseList}" var="course">--%>
<%--        <h3>${course.courseName}</h3>--%>
    <c:forEach var="combined" items="${gradeListList}" varStatus="status">
        <table class="grade-table">
            <thead>
            <tr>
                <th>Course ID</th>
                <th>Student ID</th>
                <th>Score</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${combined}" var="item">
                <tr>
                    <td>${item.courseId}</td>
                    <td>${item.userId}</td>
                    <td>${item.score}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h3>Min Grade: ${classrooms[status.index].minGrade}</h3>
        <h3>Max Grade: ${classrooms[status.index].maxGrade}</h3>
    </c:forEach>

</div>
</body>
</html>
