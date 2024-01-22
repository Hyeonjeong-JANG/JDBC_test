package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * DB에 select 한 데이터를 담기 위한 오브젝트
 */
@ToString
@AllArgsConstructor // 모든 생성자를 자동으로 만들어 준다.
@Getter
public class Account {
    private int number;
    private String password;
    private int balance;

    // java.sql의 Timestamp
    // 카멜표기법 사용하기
    private Timestamp createdAt;
    // 데이터에서 create_at이라고 되어 있지만 createAt으로 해야함.
}
