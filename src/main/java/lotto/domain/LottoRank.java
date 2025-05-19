package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum LottoRank {
  MISS(0, 0),
  ONE(1, 0),
  TWO(2, 0),
  THREE(3, 5_000),
  FOUR(4, 50_000),
  FIVE(5, 1_500_000),
  SIX(6, 2_000_000_000);

  private final int matchCount;
  private final int prizeMoney;

  private static final Map<Integer, LottoRank> MATCH_COUNT_TO_RANK =
      Arrays.stream(values())
          .collect(Collectors.toMap(rank -> rank.matchCount, rank -> rank));

  LottoRank(int matchCount, int prizeMoney) {
    this.matchCount = matchCount;
    this.prizeMoney = prizeMoney;
  }

  public int getMatchCount() {
    return matchCount;
  }

  public int getPrizeMoney() {
    return prizeMoney;
  }

  public static LottoRank getRankByMatchCount(int matchCount) {
    return MATCH_COUNT_TO_RANK.getOrDefault(matchCount, MISS);
  }
}