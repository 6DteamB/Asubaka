package model;

public class Login {
  private String name;
  private String pass;

  public Login(String userId, String pass) {
    this.name = userId;
    this.pass = pass;
  }

  public String getUserId() {
    return name;
  }

  public String getPass() {
    return pass;
  }
}