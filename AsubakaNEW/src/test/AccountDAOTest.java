package test;

import dao.AccountDAO;
import model.Account;
import model.Login;

public class AccountDAOTest {
  public static void main(String[] args) {
    testFindByLogin1(); // ユーザーが見つかる場合のテスト
//    testFindByLogin2(); // ユーザーが見つからない場合のテスト
    }

  public static void testFindByLogin1() {
	    Login login = new Login("田中", "Asmin123");
	    AccountDAO dao = new AccountDAO();
	    Account result = dao.findByLogin(login);
	    if (result != null &&
	            result.getName().equals("田中") &&
	            result.getPass().equals("Asmin123") &&
	            result.getMail().equals("tanaka@au.com") &&
	            result.getObjective().equals("毎日100円貯金") &&
	            result.getReward().equals("パンを食べる")) {
	      System.out.println("findByLogin1:成功しました");
	    } else {
	      System.out.println("findByLogin1:失敗しました");
	    }
	  }

  public static void testFindByLogin2() {
    Login login = new Login("kenta", "12345");
    AccountDAO dao = new AccountDAO();
    Account result = dao.findByLogin(login);
    if (result == null) {
      System.out.println("findByLogin2:成功しました");
    } else {
      System.out.println("findByLogin2:失敗しました");
    }
  }
}