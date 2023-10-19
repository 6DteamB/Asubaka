<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>達成おめでとう！</title>
</head>
<body>
    <form method="post" action="RewardServlet">
        <input type="hidden" name="name" value="${account.name}">
    </form>
    <c:out value="${account.name}さん" />
    <c:out value="${account.reward}のご褒美" />
</body>
</html>
