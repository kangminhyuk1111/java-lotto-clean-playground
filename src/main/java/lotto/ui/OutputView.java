package lotto.ui;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningResult;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printLottos(final List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public static void printResults(final WinningResult winningResult) {
        System.out.println("\n당첨 통계\n---------");

        Map<LottoRank, Integer> winningStats = winningResult.getWinningStatistics();

        for (int matchCount = 3; matchCount <= 6; matchCount++) {
            LottoRank rank = LottoRank.valueOf(matchCount);
            int count = winningStats.getOrDefault(rank, 0);
            int prize = rank.getPrizeMoney();

            System.out.printf("%d개 일치 (%,d원)- %d개\n", matchCount, prize, count);
        }
    }

    public static void printPrize(final WinningResult winningResult, final int payment) {
        System.out.printf("총 수익율은 %.2f 입니다.", winningResult.calculateReturnRate(payment));
    }
}
