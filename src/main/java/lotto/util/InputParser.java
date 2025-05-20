package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;

import java.util.Arrays;

public class InputParser {

  private InputParser() {
  }

  public static LottoResult parseLottoResult(final String input) {
    validateInput(input);

    return LottoResult.of(Arrays.stream(input.split(","))
        .map(String::trim)
        .mapToInt(Integer::parseInt)
        .boxed()
        .sorted()
        .toList());
  }

  public static Lotto parseLotto(final String input) {
    validateInput(input);

    return Lotto.of(Arrays.stream(input.split(","))
        .map(String::trim)
        .mapToInt(Integer::parseInt)
        .boxed()
        .sorted()
        .toList());
  }

  private static void validateInput(final String input) {
    if (input == null || input.trim().isEmpty()) {
      throw new RuntimeException("입력값이 비어있습니다.");
    }
  }
}
