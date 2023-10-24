<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.AccountDAO"%>
<%@ page import="model.Login"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>達成おめでとう！</title>
</head>
<body>
	<form method="post" action="RewardServlet">
		<input type="hidden" name="name" value="${account.name}"> 
		<input type="hidden" name="pass" value="${account.pass}">
	</form>
	<c:out value="${account.name}さん" />
	<c:out value="達成した目標: ${account.objective}" />
	<c:out value="ご褒美: ${account.reward}" />
</body>
</html>
