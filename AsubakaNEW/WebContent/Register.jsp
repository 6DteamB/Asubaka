<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="Register.css">

<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>明日やろうは馬鹿野郎 - 新規登録</title>
<script>
  function validatePassword() {
    var password = document.getElementById("pass").value;

    // パスワードが6文字以上で、大文字と数字を含むかを検証
    if (password.length < 6 || !/[A-Z]/.test(password) || !/[0-9]/.test(password)) {
      alert("パスワードは大文字と数字を含めた6文字以上にしてください。");
      return false;
    }

    var mail = document.getElementsByName("mail")[0].value;
    var objective = document.getElementsByName("objective")[0].value;
    var reward = document.getElementsByName("reward")[0].value;

    // バリデーション: mail、objective、reward が空の場合はアラートを表示してフォームを送信しない
    if (mail === "" || objective === "" || reward === "") {
      alert("メールアドレス、目標、報酬を入力してください。");
      return false;
    }

    return true;
  }
  
  function showDuplicateError() {
    alert("同じユーザーが既に存在しています。");
  }
</script>

</head>
<body>

<!-- 背景 -->	
<div class="bg_pattern Crown"></div>

<h1>新規登録</h1>

<!-- 新規登録フォーム -->
<form action="RegisterServlet.java" method="post" onsubmit="return validatePassword();">
ユーザー名：<input type="text" name="name"><br>
パスワード：<input type="password" name="pass" id="pass"><br>
メールアドレス：<input type="mail" name="mail"><br>
達成したい目標：<input type="text" name="objective"><br>
達成報酬：<input type="text" name="reward"><br>
継続日数:<br>
<select name="day">
  <option value="66">66日</option>
</select><br>
<input type="hidden" name="count" value="0" style="display: none;"> <!-- 非表示の count フィールド -->
<br>
<input type="submit" value="新規登録" onclick="showDuplicateError(); return false;">
</form>

<hr> <!-- 水平線を追加 -->

<!-- ログインへのリンク -->
<a href="/AsubakaNEW/index.jsp">ログインページへ戻る</a>

</body> 
</html>
