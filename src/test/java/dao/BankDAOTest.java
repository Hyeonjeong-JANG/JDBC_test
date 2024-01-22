package dao;

import db.DBConnection;
import model.Account;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BankDAOTest {

    @Test
    public void deleteByNumber_test() {
        // given
        int number = 100;

        // when
        BankDAO dao = new BankDAO();
        int result = dao.deleteByNumber(number);

        // then
        if (result == 1) {
            System.out.println("삭제 성공");
        } else if (result == 0) {
            System.out.println(number + "번호를 찾을 수 없습니다.");
        } else {
            System.out.println("삭제 실패");
        }
    }

    @Test
    public void insert() {
        // given
        String password = "1234";
        int balance = 5000;

        // when
        BankDAO dao = new BankDAO();
        int result = dao.insert(password, balance);

        // then
        if (result == 1) {
            System.out.println("삽입 성공");
        } else if (result == 0) {
            System.out.println("password를 확인해 주세요.");
        } else {
            System.out.println("삽입 실패");
        }
    }

    @Test
    public void updateByNumber_test() {
        // given
        int balance = 7000;
        int number = 1;

        // when
        BankDAO dao = new BankDAO();
        int result = dao.updateByNumber(balance, number);

        // then
        if (result == 1) {
            System.out.println("업데이트 성공");
        } else if (result == 0) {
            System.out.println("계좌번호를 확인해 주세요.");
        } else {
            System.out.println("업데이트 실패");
        }
    }

    @Test
    public void selectByNumber_test() {
        // given
        int number = 3;

        // when
        BankDAO dao = new BankDAO();
        Account account = dao.selectByNumber(number);

        // then
        if (account == null) {
            System.out.println("조회된 값이 없습니다.");
        } else {
            System.out.println(account); // @ToString를 써서 자동으로 투스트링 만들어짐.
//            System.out.println(account.getNumber());
//            System.out.println(account.getPassword());
//            System.out.println(account.getBalance());
//            System.out.println(account.getCreatedAt());
        }
    }

    @Test
    public void selectAll_test() {
        // given

        // when
        BankDAO dao = new BankDAO();
        List<Account> accountList = dao.selectAll();
        System.out.println(accountList.size());
        // then
    }
}
