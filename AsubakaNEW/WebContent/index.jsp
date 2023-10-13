<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="style.css">

<html>
	<head>
		<meta charset="UTF-8">
		<title>あすばかログイン</title>
	</head>

	<body>
		<h4>ねこちゃんとがんばる<br>
		習慣づけ管理アプリ</h4><br>
		<h1>明日やろうは馬鹿野郎</h1>
		
		
		<!-- ログインフォーム -->
		
		
		<form action="/AsubakaNEW/main.jsp" method="post">
		<span class="form-label">ユーザー名：</span><input type="text" name="name"><br>
		<span class="form-label">パスワード：</span><input type="password" name="pass"><br>
		<a href="/AsubakaNEW/PassForget.jsp">パスワードを忘れた場合</a><br>
		<input type="submit" value="ログイン"><br>
		<!-- 新規登録へのリンク -->
		<a href="/AsubakaNEW/Register.jsp">アカウントを新規作成</a>
		</form>
		
		<hr> <!-- 水平線 -->
		<p>習慣化に必要な期間は平均66日間と言われています。<br>
		まずは66日間取り組んでみてください。</p>
	</body>
</html> 
 