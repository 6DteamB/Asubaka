
<%@ page import="java.util.List" %>
<%@ page import="model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>管理画面</title>
</head>
<body>
    <h1>アカウント情報一覧</h1>
    <table border="1">
        <tr> 
            <th>ユーザー名</th>
            <th>メール</th>
            <th>目標</th> 
            <th>残り日数</th>
            <th>達成度</th>
            <!-- その他のカラムをここに追加 -->
        </tr>
        <% 
            List<Account> accounts = (List<Account>) request.getAttribute("accounts");
            if (accounts != null) {
                for (Account account : accounts) {
        %>
                    <tr>
                        <td><%= account.getName() %></td>
                        <td><%= account.getMail() %></td>
                        <td><%= account.getObjective() %></td>
                        <td><%= account.getDay() %></td>
                        <td><%= account.getCount() %></td>
                        <!-- その他のカラムの値をここに表示 -->
                    </tr>
        <%
                } // forループの終わり
            } // if文の終わり
        %>
    </table>
</body>
</html>

