<%--
  Created by IntelliJ IDEA.
  User: sysadmin
  Date: 11.07.17
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="classesJava.servlet.controller.QuizAllController" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>QUIZ</h1>
<ul>
    <li>ID: ${QuizAllController.idd}</li>
    <li>Login: ${QuizAllController.logg}</li>
    <li>Email: ${QuizAllController.emaill}</li>
    <li>UserColl: ${QuizAllController.usercol}</li>
</ul>
<h3><a href="allUsers.jsp" methods="GET">selectAll</a></h3>
</body>
</html>
