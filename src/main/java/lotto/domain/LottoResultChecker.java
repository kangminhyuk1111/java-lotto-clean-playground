package lotto.domain;

import java.util.List;

public class LottoResultChecker {

  private final LottoResult winningNumbers;
  private final LottoNumber bonusBall;

  public LottoResultChecker(final LottoResult winningNumbers, final LottoNumber bonusBall) {
    this.winningNumbers = winningNumbers;
    this.bonusBall = bonusBall;
  }

  public WinningResult matchLottos(Lottos userLottos) {
    WinningResult winningResult = new WinningResult();

    for (Lotto lotto : userLottos) {
      Rank rank = getRankByCount(lotto);
      winningResult.addRank(rank);
    }

    return winningResult;
  }

  private Rank getRankByCount(final Lotto lotto) {
    int count = matchCount(lotto);

    if (lotto.isContainBonusBall(bonusBall)) {
      return Rank.getRankByMatchCount(count, true);
    }

    return Rank.getRankByMatchCount(count, false);
  }

  private int matchCount(Lotto userLotto) {
    return winningNumbers.matchCount(userLotto);
  }
}
