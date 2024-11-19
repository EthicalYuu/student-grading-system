<%--
  Created by IntelliJ IDEA.
  User: 96278
  Date: 7/25/2023
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if(session.getAttribute("username") == null){
            response.sendRedirect("login.jsp");
        }
    %>


<%--    Fetches username from session--%>
    <h1>Welcome ${username}</h1>
    <a href="www.google.com">GOOGLE FU!</a>
    <form action="LogoutServlet">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
