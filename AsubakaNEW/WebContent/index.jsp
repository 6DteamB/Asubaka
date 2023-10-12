
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="style.css">

<html>
<head>
	<meta charset="UTF-8">
	<title>あすばかログイン</title>
</head>

<body>
	<h4>わんちゃん＆ねこちゃんとがんばる<br>
	習慣づけ管理アプリ</h4><br>
	<h1>明日やろうは馬鹿野郎</h1>
	
	<!-- ログインフォーム -->
	
	
	<form action="/AsubakaNEW/LoginServlet" method="post">
	<span class="form-label">ユーザー名：</span><input type="text" name="name"><br>
	<span class="form-label">パスワード：</span><input type="password" name="pass"><br>
	<input type="submit" value="ログイン">
	</form>
	
	<a href="/AsubakaNEW/PassForget.jsp">パスワードを忘れた方</a>
	
	<hr> <!-- 水平線を追加 -->
	
	<!-- 新規登録へのリンク -->
	<a href="/AsubakaNEW/Register.jsp">新規登録</a>
	
</body>
</html> 
 