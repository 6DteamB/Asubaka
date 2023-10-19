<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>あすばかログイン</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h4>ねこちゃんとがんばる習慣づけ管理アプリ</h4>
    <h1>明日やろうは馬鹿野郎</h1>
 
    <!-- ログインフォーム -->
    <form action="MainServlet.java" method="post">
        <span class="form-label">ユーザー名</span><input type="text" name="name"><br>
        <span class="form-label">パスワード</span><input type="password" name="pass"><br>
        <a href="PassForget.jsp" class="left">パスワードを忘れた場合</a><br>
        <input type="submit" value="ログイン"><br>
        <!-- 新規登録へのリンク -->
    <a href="Register.jsp">アカウントを新規作成</a>
    <!-- エラーメッセージを表示 -->
	<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<% if (errorMessage != null) { %>
    	<div class="error-message"><%= errorMessage %></div>
	<% } %>
    </form>

    <hr> <!-- 水平線 -->