package dao;

// 데이터 베이스에 접근하기 위한 데이터 액세스 오브젝트를 만든다. 전 세계 공통!!

import db.DBConnection;
import lombok.AllArgsConstructor;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller: 안내원, 라우터
 * DAO: Data Access Object, 데이터베이스와 상호작용 하는 책임만 가지고 있다.
 * SRP: 단일 책임의 원칙
 */
public class BankDAO {
    public int deleteByNumber(int number) {

        /**
         * 1. 디비 소켓 가져오기
         * 2. 버퍼에 쿼리 담기
         * 3. 쿼리 전송
         * 4. 결과 리턴
         */
        Connection conn = DBConnection.getInstance(); // getInstance()는 스테이틱 메서드이기 때문에 이렇게 부를 수 있어.
        try {
            String sql = "delete from account_tb where number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // delete
            pstmt.setInt(1, number);

            int num = pstmt.executeUpdate(); // flush();
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int insert(String password, int balance) {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "insert into account_tb(password, balance, created_at) values(?, ?,now())";
            PreparedStatement pstmt = conn.prepareStatement(sql); // 코드는 다르지만 얘가 버퍼임
            // 연결된 선에다가               버퍼를 달고             쿼리를 넣는다. 그런데 그 쿼리가 완성되지 않았어.

            // insert
            pstmt.setString(1, password); // 몇 번째 파라미터를 완성할거냐. 1, 2, 3,... 0이 없음
            pstmt.setInt(2, balance); // 몇 번째 파라미터를 완성할거냐. 1, 2, 3,... 0이 없음

            int num = pstmt.executeUpdate(); // flush();
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int updateByNumber(int balance, int number) {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "update account_tb set balance = ? where number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // update
            pstmt.setInt(1, balance);
            pstmt.setInt(2, number);

            int num = pstmt.executeUpdate(); // flush();
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Account selectByNumber(int number) { // 계좌번호는 프라이머리키이기 때문에 하나만 있으니까 클래스에 담에서..... 요런 느낌.
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "select * from account_tb where number =?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, number);

            ResultSet rs = pstmt.executeQuery(); // ResultSet: 테이블 형태의 데이터
//            Account rs = pstmt.executeQuery(); // 로 하지 않은 이유!! 뭐가 어떻게 만들어질지 알고? 일단 ResultSet으로 받아야 한다.
//            boolean isRow = rs.next(); // 커서 한 칸 내리기, boolean이다. 커서를 내렸는데 뭐가 있으면 true, 없으면 false
//            System.out.println(isRow);

            while (rs.next()){
                // Account 클래스에 담는다. 그러지 않으면 테스트코드에서 또 rs어쩌구 해야함.
                // 계좌가 최초로 생성될 때는 생성자로 아래의 값들을 만든다.
                Account account = new Account(
                        rs.getInt("number"),
                        rs.getString("password"),
                        rs.getInt("balance"),
                        rs.getTimestamp("created_at")
                );
                return account;
            }

//            // 커서를 한 칸 옆으로 옮기는 것! 프로젝션: 컬럼을 골라내는 것
//            System.out.println(rs.getInt("number"));
//            System.out.println(rs.getString("password"));
//            System.out.println(rs.getInt("balance"));
//            System.out.println(rs.getTimestamp("created_at"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> selectAll() {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "select * from account_tb order by number desc"; // 디폴트는 오름차순, 최근에 만든 것부터 보려면 내림차순! order by number desc 해줘야 함.
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(); // ResultSet: 테이블 형태의 데이터

            /**
             * 와일문을 돌면 어카운트가 하나씩 만들어지는데 그것을 add하면 account는 이미 담겼기 때문에 지워져도 되지만 어카운트리스트는 계속 새로 만들어지면 안 됨.
             */
            List<Account> accountList = new ArrayList<>();
            while (rs.next()){
                // Account 클래스에 담는다. 그러지 않으면 테스트코드에서 또 rs어쩌구 해야함.
                // 계좌가 최초로 생성될 때는 생성자로 아래의 값들을 만든다.
                Account account = new Account(
                        rs.getInt("number"),
                        rs.getString("password"),
                        rs.getInt("balance"),
                        rs.getTimestamp("created_at")
                );
                accountList.add(account);
            }
            return accountList;

//            // 커서를 한 칸 옆으로 옮기는 것! 프로젝션: 컬럼을 골라내는 것
//            System.out.println(rs.getInt("number"));
//            System.out.println(rs.getString("password"));
//            System.out.println(rs.getInt("balance"));
//            System.out.println(rs.getTimestamp("created_at"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
