<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>明日やろうは馬鹿野郎 - 新規登録</title>
<script>
  function validatePassword() {
    var password = document.getElementById("pass").value;
    
    // パスワードが6文字以上で、大文字と数字を含むかを検証
    if (password.length < 6 || !/[A-Z]/.test(password) || !/[0-9]/.test(password)) {
      alert("パスワードは大文字と数字を含めた6文字以上にしてください。");
      return false;
    }
    
    return true;
  }
</script>
</head>
<body>
<h1>新規登録</h1>

<!-- 新規登録フォーム -->
<form action="RegisterServlet.java" method="post" onsubmit="return validatePassword();">
ユーザー名：<input type="text" name="name"><br>
パスワード：<input type="password" name="pass" id="pass"><br>
メールアドレス：<input type="email" name="mail"><br>
達成したい目標：<input type="text" name="objective"><br>
達成報酬：<input type="text" name="reward"><br>
継続日数:
<select name="day">
	<option value="66">66日</option>
	<option value="100">100日</option>
</select>
<br>

<input type="submit" value="新規登録">
</form>

<hr> <!-- 水平線を追加 -->

<!-- ログインへのリンク -->
<a href="/AsubakaNEW/index.jsp">ログイン</a>

</body> 
</html>