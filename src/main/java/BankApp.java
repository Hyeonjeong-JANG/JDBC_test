import dao.BankDAO;
import model.Account;

import java.util.List;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 디비한테 데이터를 요청할 때는 /뒤에 식별자를 쓴다.
        // http://bank.com/account GET 계좌 전체 줘
        // http://bank.com/account/10 GET 10번(프라이머리키) 계좌 줘
        // http://bank.com/account POST 계좌를 만드는 것!
        // -> 이 정보만으로는 포스트 못 함
        // http://bank.com/account/1 DELETE 1번(프라이머리키) 계좌 삭제해 줘!
        // http://bank.com/account/1 PUT
        // -> 이 정보만으로는 풋 못 함

        // 겟 딜리트 요청은 http에 바디가 없다.
        // 포스트, 풋 요청은 http에 바디가 없다.
        // 풋, 딜리트, 업데이터는 write요청이다. -> 응답에 바디가 없다. 오케이만 해주면 됨.

        System.out.println("http 메서드를 입력하세요");
        String method = sc.nextLine();

        System.out.println("식별자를 입력하세요");
        String action = sc.nextLine();

        String body = "";

        BankDAO bankDAO = new BankDAO();

//        GET: 조회
        if (method.equalsIgnoreCase("GET")) {
            if (action.equalsIgnoreCase("/account")) {
                List<Account> accountList = bankDAO.selectAll();
                System.out.println(accountList);
            } else if (action.contains("/account/")) {
                String[] actionSpl = action.split("/");
                String actionNumStr = actionSpl[2];
                int actionNum = Integer.parseInt(actionNumStr);
                Account account = bankDAO.selectByNumber(actionNum);
                System.out.println("account: " + account);
            }
        }

//        POST: 입력
        else if (method.equalsIgnoreCase("POST")) {
            if (action.equalsIgnoreCase("/account")) {
                System.out.println("body 데이터를 입력하세요.");
                body = sc.nextLine();
                System.out.println("/account 입력됨");
                String[] step1 = body.split("&");
                String password = step1[0].split("=")[1];
                String balanceStr = step1[1].split("=")[1];
                int balance = Integer.parseInt(balanceStr);
                int account = bankDAO.insert(password, balance); // 왜 int로 해야 되는거지?
            }
        }

//        PUT: 수정
        else if (method.equalsIgnoreCase("PUT")) {
            if (action.equalsIgnoreCase("/account")) {
                System.out.println("body 데이터를 입력하세요"); // balance=70000000&number=3
                body = sc.nextLine();

                String[] step1 = body.split("&");
                String balanceStr = step1[0].split("=")[1];
                int balance = Integer.parseInt(balanceStr);

                // number 찾기
                String numberStr = step1[1].split("=")[1];
                int number = Integer.parseInt(numberStr);
                int account = bankDAO.updateByNumber(balance, number);
            }
        }

//        DELETE: 삭제
        else if (method.equalsIgnoreCase("DELETE")) {
            if (action.equalsIgnoreCase("/account")) {
                System.out.println("삭제하고 싶은 번호를 입력하세요.");
                int deleteNum = sc.nextInt();
                int account = bankDAO.deleteByNumber(deleteNum);
            }
        }
    }
}