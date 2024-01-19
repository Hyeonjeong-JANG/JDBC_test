package db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest { // 테스트코드에서는 main의 본 코드 이름 뒤에 Test

    // return 타입을 적을 수 없다.
    // 매개변수를 적을 수 없다.
    // @Test 붙이면 메서드별로 실행 가능
    @Test // main 안 만들어도 됨
    /**
     * testImplementation platform('org.junit:junit-bom:5.9.1')
     * testImplementation 'org.junit.jupiter:junit-jupiter'얘가 build.gradle에 있어야 하는데 gradle으로 하면 자동으로 된다.
     *
     */
    public void getInstance_test(){ // _test를 붙인다.
        String username = "root";
        String password = "1234";
        String url = "jdbc:mariadb://127.0.0.1:3306/cosdb";

        // 프로토콜이 적용된 소켓
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
//            return conn; // 리턴 못 함
        } catch (SQLException e) {
            throw new RuntimeException(e); // 호출하는 놈(JVM)에 예외를 던짐
        }
    }
}
