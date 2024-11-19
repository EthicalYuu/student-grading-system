<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
    <link rel="stylesheet" type="text/css" href="create-user-style.css">
</head>
<body>
<div id="form-container">
    <form action="/CreateUserAction" method="post">
      Enter User ID: <input type="text" name="userId"><br>
      Enter First Name: <input type="text" name="firstName"><br>
      Enter Last Name: <input type="text" name="lastName"><br>
      Enter Role: <input type="text" name="roleName"><br>
      Enter password: <input type="password" name="password"><br>
      <input type="submit">
    </form>
    <p>${message}</p>
</div>
</body>
</html>
