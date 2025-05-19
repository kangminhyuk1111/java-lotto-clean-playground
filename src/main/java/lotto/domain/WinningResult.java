package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class WinningResult {

  private final Map<Integer, Integer> matchCountStats;

  public WinningResult() {
    matchCountStats = new HashMap<>();
    for (int i = 0; i <= 6; i++) {
      matchCountStats.put(i, 0);
    }
  }

  public void addMatchCount(int matchCount) {
    matchCountStats.put(matchCount, matchCountStats.get(matchCount) + 1);
  }

  public Map<LottoRank, Integer> getWinningStatistics() {
    Map<LottoRank, Integer> winningStats = new HashMap<>();

    for (int matchCount = 0; matchCount <= 6; matchCount++) {
      LottoRank rank = LottoRank.getRankByMatchCount(matchCount);
      int count = matchCountStats.getOrDefault(matchCount, 0);
      winningStats.put(rank, count);
    }

    return winningStats;
  }

  public double calculateReturnRate(int totalPurchaseAmount) {
    return (double) calculateTotalPrize() / totalPurchaseAmount;
  }

  public long calculateTotalPrize() {
    long totalPrize = 0;

    for (int matchCount = 0; matchCount <= 6; matchCount++) {
      int count = getCountFor(matchCount);
      int prize = LottoRank.getRankByMatchCount(matchCount).getPrizeMoney();
      totalPrize += (long) prize * count;
    }

    return totalPrize;
  }


  private int getCountFor(int matchCount) {
    return matchCountStats.getOrDefault(matchCount, 0);
  }
}
