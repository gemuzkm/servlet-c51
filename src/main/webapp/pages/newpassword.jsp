<%--
  Created by IntelliJ IDEA.
  User: gemuz
  Date: 31.01.2022
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Password Page</title>
</head>
<body>
<a href="/">Home</a><br>
<form action="/updatepassword" method="POST">
    <input type="text " name="newpassword" placeholder="New Password"> ${requestScope.msgErrorPassword}
    <br>
    <input type="text " name="recword" placeholder="Recovery Word"> ${requestScope.msgErrorRecoveryWord}
    <br>
    RECOVERY WORD: ${sessionScope.recoveryword}
    <br>
    <button>Submit</button>
</form>
</body>
</html>