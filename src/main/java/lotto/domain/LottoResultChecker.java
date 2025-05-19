package lotto.domain;

import java.util.List;

public class LottoResultChecker {

  private final LottoResult winningNumbers;

  public LottoResultChecker(final LottoResult winningNumbers) {
    this.winningNumbers = winningNumbers;
  }

  public WinningResult matchLottos(List<Lotto> userLottos) {
    WinningResult winningResult = new WinningResult();

    for (Lotto lotto : userLottos) {
      int count = matchCount(lotto);
      winningResult.addMatchCount(count);
    }

    return winningResult;
  }

  private int matchCount(Lotto userLotto) {
    return winningNumbers.matchCount(userLotto);
  }
}
