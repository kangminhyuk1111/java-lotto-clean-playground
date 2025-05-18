package lotto.ui;

import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    /*
    * static 클래스로 사용되기 때문에 인스턴스 생성을 제한한다.
    * */
    private InputView() {

    }

    public static int inputPayment() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }
}
