package lotto.domain;

import java.util.List;

public class LottoResultChecker {

    private final LottoResult winningNumbers;

    public LottoResultChecker(final LottoResult winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public WinningResult matchLottos(List<Lotto> userLottos) {
        WinningResult lottoStatistics = new WinningResult();

        for (Lotto lotto : userLottos) {
            int count = matchCount(lotto);
            lottoStatistics.addMatchCount(count);
        }

        return lottoStatistics;
    }

    private int matchCount(Lotto userLotto) {
        return winningNumbers.matchCount(userLotto);
    }
}
