package db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {

    @Test
    public void getInstance_test() {
        // given: 파라미터(테스트에는 파라미터를 못 적는다. 그래서 여기서 파라미터를 쓴다?)


        // when: 본코드 실행, 본코드의 메서드를 바로 호출한다.
        Connection conn = DBConnection.getInstance(); // 리턴되는 값 conn
        // then: 눈 검증
        if(conn ==null){
            System.out.println("실패");
        } else {
            System.out.println("성공");
        }
    }
}
