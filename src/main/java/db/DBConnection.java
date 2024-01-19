package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getInstance(){
        String username = "root";
        String password = "1234";
        String url = "jdbc:mariadb://127.0.0.1:3306/cosdb";

        // 프로토콜이 적용된 소켓
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("db connect success");
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e); // 호출하는 놈(JVM)에 예외를 던짐
        }
    }
}
