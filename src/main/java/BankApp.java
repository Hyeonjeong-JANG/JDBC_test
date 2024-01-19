import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankApp {
    public static void main(String[] args) {
        Connection conn = DBConnection.getInstance();
        try {
            String insert="insert into account_tb(password, balance, created_at) values(?,?,now())";
            String delete= "DELETE FROM account_tb WHERE number = ?";
            String update= "update account_tb set balance = balance + ? where number = ?";
            PreparedStatement pstmt = conn.prepareStatement(insert); // 코드는 다르지만 얘가 버퍼임
            // 연결된 선에다가               버퍼를 달고             쿼리를 넣는다. 그런데 그 쿼리가 완성되지 않았어.

            // insert
            pstmt.setString(1, "1234"); // 몇 번째 파라미터를 완성할거냐. 1, 2, 3,... 0이 없음
            pstmt.setInt(2, 1000); // 몇 번째 파라미터를 완성할거냐. 1, 2, 3,... 0이 없음

//            // delete
//            pstmt.setInt(1, 6);

//            // update
//            pstmt.setInt(1, 3000);
//            pstmt.setInt(2, 2);

           int num =  pstmt.executeUpdate(); // flush();
            System.out.println(num);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
