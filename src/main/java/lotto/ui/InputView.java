package lotto.ui;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.util.InputParser;

import java.util.Scanner;

public class InputView {

  private static final Scanner sc = new Scanner(System.in);

  private InputView() {

  }

  public static int inputPayment() {
    System.out.println("구입금액을 입력해 주세요.");
    return Integer.parseInt(sc.nextLine());
  }

  public static LottoResult inputLottoResult() {
    System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    return InputParser.parseLottoResult(sc.nextLine());
  }

  public static LottoNumber inputBonusBall() {
    System.out.println("보너스 볼을 입력해 주세요.");
    return LottoNumber.of(Integer.parseInt(sc.nextLine()));
  }

  public static int inputManualLottoCount() {
    System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
    return Integer.parseInt(sc.nextLine());
  }

  public static Lottos inputManualLottos(final int manualLottoCount) {
    final Lottos lottos = new Lottos(new ArrayList<>());

    if (manualLottoCount == 0) {
      return lottos;
    }

    System.out.println("수동으로 구매할 번호를 입력해 주세요.");

    for (int i = 0; i < manualLottoCount; i++) {
      lottos.add(InputParser.parseLotto(sc.nextLine()));
    }

    return lottos;
  }
}
