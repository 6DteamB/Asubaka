package model;

public class User {
    private String username;
    private String password;
    // 他の属性を追加することもできます（例：email, fullNameなど）

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // ユーザー名のgetterとsetter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // パスワードのgetterとsetter
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 他の属性のためのgetterとsetterも追加できます
}
