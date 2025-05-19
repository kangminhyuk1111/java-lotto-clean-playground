package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoRankTest {

  @ParameterizedTest
  @CsvSource({
      "0, MISS",
      "1, ONE",
      "2, TWO",
      "3, THREE",
      "4, FOUR",
      "5, FIVE",
      "6, SIX"
  })
  void 일치하는_번호_개수에_따른_로또_등수_반환_테스트(int matchCount, LottoRank expectedRank) {
    LottoRank actualRank = LottoRank.getRankByMatchCount(matchCount);

    assertThat(actualRank).isEqualTo(expectedRank);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 7, 8, 10})
  void 유효하지_않은_일치_개수는_MISS를_반환해야_함(int invalidMatchCount) {
    LottoRank actualRank = LottoRank.getRankByMatchCount(invalidMatchCount);

    assertThat(actualRank).isEqualTo(LottoRank.MISS);
  }

  @Test
  void 각_등수별_상금_반환_테스트() {
    assertEquals(0, LottoRank.MISS.getPrizeMoney());
    assertEquals(0, LottoRank.ONE.getPrizeMoney());
    assertEquals(0, LottoRank.TWO.getPrizeMoney());
    assertEquals(5_000, LottoRank.THREE.getPrizeMoney());
    assertEquals(50_000, LottoRank.FOUR.getPrizeMoney());
    assertEquals(1_500_000, LottoRank.FIVE.getPrizeMoney());
    assertEquals(2_000_000_000, LottoRank.SIX.getPrizeMoney());
  }

  @Test
  void 각_등수별_일치_개수_반환_테스트() {
    assertEquals(0, LottoRank.MISS.getMatchCount());
    assertEquals(1, LottoRank.ONE.getMatchCount());
    assertEquals(2, LottoRank.TWO.getMatchCount());
    assertEquals(3, LottoRank.THREE.getMatchCount());
    assertEquals(4, LottoRank.FOUR.getMatchCount());
    assertEquals(5, LottoRank.FIVE.getMatchCount());
    assertEquals(6, LottoRank.SIX.getMatchCount());
  }

  @Test
  void 상수_값_검증_테스트() {
    assertThat(LottoRank.MISS.ordinal()).isEqualTo(0);
    assertThat(LottoRank.ONE.ordinal()).isEqualTo(1);
    assertThat(LottoRank.TWO.ordinal()).isEqualTo(2);
    assertThat(LottoRank.THREE.ordinal()).isEqualTo(3);
    assertThat(LottoRank.FOUR.ordinal()).isEqualTo(4);
    assertThat(LottoRank.FIVE.ordinal()).isEqualTo(5);
    assertThat(LottoRank.SIX.ordinal()).isEqualTo(6);

    assertThat(LottoRank.MISS.name()).isEqualTo("MISS");
    assertThat(LottoRank.ONE.name()).isEqualTo("ONE");
    assertThat(LottoRank.TWO.name()).isEqualTo("TWO");
    assertThat(LottoRank.THREE.name()).isEqualTo("THREE");
    assertThat(LottoRank.FOUR.name()).isEqualTo("FOUR");
    assertThat(LottoRank.FIVE.name()).isEqualTo("FIVE");
    assertThat(LottoRank.SIX.name()).isEqualTo("SIX");
  }
}