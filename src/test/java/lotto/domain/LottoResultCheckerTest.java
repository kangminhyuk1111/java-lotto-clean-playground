package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultCheckerTest {

  private final LottoResult lottoResult = new LottoResult(List.of(1, 2, 3, 4, 5, 6));
  private final LottoResultChecker lottoResultChecker = new LottoResultChecker(lottoResult);

  @ParameterizedTest
  @MethodSource("provideLottosAndWinningResult")
  void 여러_로또의_당첨_통계를_확인한다(List<Lotto> userLottos, Map<LottoRank, Integer> expectedStatistics) {
    WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);

    assertThat(winningResult.getWinningStatistics()).isEqualTo(expectedStatistics);
  }

  @Test
  void 당첨_금액_계산_테스트() {
    List<Lotto> userLottos = Arrays.asList(
        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
        new Lotto(List.of(1, 2, 3, 4, 5, 7)),
        new Lotto(List.of(1, 2, 3, 4, 7, 8)),
        new Lotto(List.of(1, 2, 3, 7, 8, 9))
    );

    WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);
    long totalPrizeMoney = winningResult.calculateTotalPrize();

    long expectedPrizeMoney = 2_000_000_000L + 1_500_000L + 50_000L + 5_000L;
    assertThat(totalPrizeMoney).isEqualTo(expectedPrizeMoney);
  }

  @Test
  void 당첨_통계_출력_테스트() {
    List<Lotto> userLottos = Arrays.asList(
        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
        new Lotto(List.of(1, 2, 3, 4, 5, 7)),
        new Lotto(List.of(1, 2, 3, 4, 7, 8)),
        new Lotto(List.of(1, 2, 3, 7, 8, 9)),
        new Lotto(List.of(7, 8, 9, 10, 11, 12))
    );

    WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);
    Map<LottoRank, Integer> statistics = winningResult.getWinningStatistics();

    assertThat(statistics.get(LottoRank.SIX)).isEqualTo(1);
    assertThat(statistics.get(LottoRank.FIVE)).isEqualTo(1);
    assertThat(statistics.get(LottoRank.FOUR)).isEqualTo(1);
    assertThat(statistics.get(LottoRank.THREE)).isEqualTo(1);
    assertThat(statistics.get(LottoRank.MISS)).isEqualTo(1);
  }

  private static Stream<Arguments> provideLottosAndWinningResult() {
    return Stream.of(
        Arguments.of(
            List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))),
            Map.of(LottoRank.SIX, 1, LottoRank.FIVE, 0, LottoRank.FOUR, 0, LottoRank.THREE, 0,
                LottoRank.TWO, 0, LottoRank.ONE, 0, LottoRank.MISS, 0)
        ),
        Arguments.of(
            List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 7, 8)),
                new Lotto(List.of(1, 2, 3, 7, 8, 9))
            ),
            Map.of(LottoRank.SIX, 1, LottoRank.FIVE, 1, LottoRank.FOUR, 1, LottoRank.THREE, 1,
                LottoRank.TWO, 0, LottoRank.ONE, 0, LottoRank.MISS, 0)
        ),
        Arguments.of(
            List.of(
                new Lotto(List.of(7, 8, 9, 10, 11, 12)),
                new Lotto(List.of(13, 14, 15, 16, 17, 18))
            ),
            Map.of(LottoRank.SIX, 0, LottoRank.FIVE, 0, LottoRank.FOUR, 0, LottoRank.THREE, 0,
                LottoRank.TWO, 0, LottoRank.ONE, 0, LottoRank.MISS, 2)
        )
    );
  }
}