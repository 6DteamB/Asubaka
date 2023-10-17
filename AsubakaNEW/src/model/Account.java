package model;

public class Account {
  private String name;
  private String pass;
  private String mail;
  private String objective;
  private String reward;
  private int day;

  public Account(String name, String pass, String mail,
		  String objective, String reward, int day) {
    this.name = name;
    this.pass = pass;
    this.mail = mail;
    this.objective = objective;
    this.reward = reward;
    this.day=day;
  } 

  public String getName() {
    return name;
  }

  public String getPass() {
    return pass;
  }

  public String getMail() {
    return mail;
  }

  public String getObjective() {
    return objective;
  }

  public String getReward() {
    return reward;
  }

  public int getDay() {
	  return day;
  }
  
}