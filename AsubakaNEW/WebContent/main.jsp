<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.AccountDAO" %>
<%@ page import="model.Login" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>あすばか</title>
<link rel="stylesheet" type="text/css" href="style_main.css">
</head>
<body>
	<h1>明日やろうは馬鹿野郎</h1>

	<!-- ランダムな犬・猫の画像 -->
	
	<div class="image-container">
    	<img src="${animalImagePath}" alt="動物の画像" />
	</div>

	<!-- ランダムな名言 -->
   	<c:out value="${randomQuoteAndAuthor}" />


<div class="center-container">
	<!-- 目標の詳細-->
	<div>
		<h2>目標</h2>
		<c:out value="${account.objective}" />
	</div>

	<!-- その日の目標達成を確認するボタン -->
	<div>
		<h2>今日の習慣</h2>
		<form method="post" action="DayServlet.java">
		<button id="achievedButton">やった！</button>
		<button id="notAchievedButton">やってない</button>
	</div>

	<!-- 66日間の継続達成度の表示 プログレスバー-->
	<div>
		<h2>達成度</h2>
		<progress value="<c:out value="${account.day}" />" max="66"></progress>
		<c:out value="${account.day}" /> /日 / 66日
	</div>

	<!-- 残り日数-->
	<p>
		残り日数:
		<c:out value="${account.day}" />
		日
	</p>

	<h1 id="calendar-title"></h1>
	<div id="calendar" class="calendar-wrap"></div>
	<button id="prevMonth">前の月</button>
	<button id="nextMonth">次の月</button>
</div>

	<script src="script.js"></script>
</body>
</html>
