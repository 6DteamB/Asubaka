<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>達成おめでとう！</title>
</head>
<body>
    <form method="post" action="RewardServlet">
        <input type="hidden" name="name" value="${loggedInAccount.name}">
        <input type="hidden" name="pass" value="${loggedInAccount.pass}">
    </form>
    <c:out value="${loggedInAccount.name}さん" />
    <c:out value="達成した目標: ${loggedInAccount.objective}" />
    <c:out value="ご褒美: ${loggedInAccount.reward}" />
</body>
</html>
