<%--
  Created by IntelliJ IDEA.
  User: gemuz
  Date: 30.01.2022
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User Page</title>
</head>
<body>
<a href="/">Home</a><br>
<form action="/createuser" method="post">
    <input type="text" name="name" placeholder="Name">
    ${requestScope.msgErrorName}
    <br>
    <input type="text" name="login" placeholder="Login">
    ${requestScope.msgErrorLogin}
    <br>
    <input type="text" name="password" placeholder="Password">
    ${requestScope.msgErrorPassword}
    <br>
    <button>Submit</button>
    <p>${requestScope.msgErrorUser}</p>
</form>
</body>
</html>
