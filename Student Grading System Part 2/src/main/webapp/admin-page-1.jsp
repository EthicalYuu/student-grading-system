<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" type="text/css" href="student-style.css" />
  <title>Admin Page</title>
</head>
<body>
<h2>Logged in as ${role}</h2>

<a href="/AddCourseServlet" methods="get">
  <button>Add a course</button>
</a>
<br>
<a href="/LogoutServlet" methods="get">
  <button>Sign Out</button>
</a>
</body>
</html>
