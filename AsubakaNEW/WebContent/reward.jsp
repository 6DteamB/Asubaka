<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.dao.AccountDAO" %>
<%@ page import="com.example.model.main.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>達成おめでとう！</title>
</head>
<body>
<c:out value="${account.name}さん"/>
<c:out value="${account.reward}のご褒美"/>
</body>
</html>