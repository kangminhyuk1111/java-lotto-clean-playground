package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {

  private final Map<Rank, Integer> rankStats;

  public WinningResult() {
    rankStats = new EnumMap<>(Rank.class);

    for (Rank rank : Rank.values()) {
      rankStats.put(rank, 0);
    }
  }

  public void addRank(Rank rank) {
    rankStats.put(rank, rankStats.get(rank) + 1);
  }

  public Map<Rank, Integer> getWinningResult() {
    return new EnumMap<>(rankStats);
  }

  public double calculateReturnRate(int totalPurchaseAmount) {
    return (double) calculateTotalPrize() / totalPurchaseAmount;
  }

  public long calculateTotalPrize() {
    long totalPrize = 0;

    for (Map.Entry<Rank, Integer> entry : rankStats.entrySet()) {
      totalPrize += (long) entry.getKey().getPrizeMoney() * entry.getValue();
    }

    return totalPrize;
  }
}
