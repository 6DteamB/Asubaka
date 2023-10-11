package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ユーザー名を入力してください: ");
        String inputUsername = scanner.nextLine();

        System.out.print("パスワードを入力してください: ");
        String inputPassword = scanner.nextLine();

        try {
            Connection conn = DriverManager.getConnection("jdbc:yourdatabaseurl", "yourdatabaseusername", "yourdatabasepassword");
            String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, inputUsername);
            pstmt.setString(2, inputPassword);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                System.out.println("ログイン成功！");
            } else {
                System.out.println("ログイン失敗。ユーザー名かパスワードが違います。");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}