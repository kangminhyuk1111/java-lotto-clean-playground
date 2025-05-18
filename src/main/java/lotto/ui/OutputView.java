package lotto.ui;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    public static void printLottos(List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto: lottos) {
            System.out.println(lotto.toString());
        }
    }
}
