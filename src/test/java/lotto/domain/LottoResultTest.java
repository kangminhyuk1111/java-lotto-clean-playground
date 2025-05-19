package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultTest {

  @Test
  void 로또_결과_객체_생성_테스트() {
    List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

    LottoResult lottoResult = new LottoResult(winningNumbers);

    assertThat(lottoResult.getNumbers()).containsExactlyElementsOf(winningNumbers);
  }

  @ParameterizedTest
  @MethodSource("provideMatchCountTestData")
  void 일치하는_번호_개수_계산_테스트(List<Integer> winningNumbers, List<Integer> userNumbers, int expectedMatchCount) {
    LottoResult lottoResult = new LottoResult(winningNumbers);
    Lotto userLotto = new Lotto(userNumbers);

    int actualMatchCount = lottoResult.matchCount(userLotto);

    assertEquals(expectedMatchCount, actualMatchCount);
  }

  @Test
  void 동일한_번호_다른_순서로_일치_여부_테스트() {
    List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> userNumbers = Arrays.asList(6, 5, 4, 3, 2, 1);

    LottoResult lottoResult = new LottoResult(winningNumbers);
    Lotto userLotto = new Lotto(userNumbers);

    int matchCount = lottoResult.matchCount(userLotto);

    assertThat(matchCount).isEqualTo(6);
  }

  @Test
  void 번호가_하나도_일치하지_않는_경우_테스트() {
    List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> userNumbers = Arrays.asList(7, 8, 9, 10, 11, 12);

    LottoResult lottoResult = new LottoResult(winningNumbers);
    Lotto userLotto = new Lotto(userNumbers);

    int matchCount = lottoResult.matchCount(userLotto);

    assertThat(matchCount).isZero();
  }

  private static Stream<Arguments> provideMatchCountTestData() {
    return Stream.of(
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6), 6),
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 7, 8, 9), 3),
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(7, 8, 9, 10, 11, 12), 0),
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(6, 5, 4, 3, 2, 1), 6)
    );
  }
}