package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {
  NONE(0, false, 0),
  FIFTH(3, false, 5_000),
  FOURTH(4, false, 50_000),
  THIRD(5, false, 1_500_000),
  SECOND(5, true, 30_000_000),
  FIRST(6, false, 2_000_000_000);

  private final int matchCount;
  private final boolean bonusMatch;
  private final int prizeMoney;

  private static final Map<Integer, Rank> MATCH_COUNT_TO_RANK =
      Arrays.stream(values())
          .filter(rank -> rank != SECOND)
          .collect(Collectors.toMap(rank -> rank.matchCount, rank -> rank));

  Rank(int matchCount, boolean bonusMatch, int prizeMoney) {
    this.matchCount = matchCount;
    this.bonusMatch = bonusMatch;
    this.prizeMoney = prizeMoney;
  }

  public int getMatchCount() {
    return this.matchCount;
  }

  public int getPrizeMoney() {
    return this.prizeMoney;
  }

  public static Rank getRankByMatchCount(int matchCount, boolean bonusMatch) {
    if (matchCount == 5 && bonusMatch) {
      return SECOND;
    }
    return getRankByMatchCount(matchCount);
  }

  public boolean isBonusMatch() {
    return this.bonusMatch;
  }

  public static Rank getRankByMatchCount(int matchCount) {
    return MATCH_COUNT_TO_RANK.getOrDefault(matchCount, NONE);
  }
}