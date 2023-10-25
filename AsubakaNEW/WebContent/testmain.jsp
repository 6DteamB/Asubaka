<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Main</title>
</head>
<body>
    <h2>Test Main Page</h2>
    <form action="/TestMainServlet" method="get">
        アカウント名：<input type="text" name="name" value="neko"><br>
        日数：<input type="number" name="days" value="67"><br> <!-- 66日以上の値をデフォルトで設定 -->
        <input type="submit" value="Go to Main">
    </form>
</body>
</html>
