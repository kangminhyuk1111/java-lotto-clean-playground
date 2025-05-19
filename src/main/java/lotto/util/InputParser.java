package lotto.util;

import lotto.domain.LottoResult;

import java.util.Arrays;

public class InputParser {

  private InputParser() {
  }

  public static LottoResult parseLottoResult(final String input) {
    validateInput(input);

    return new LottoResult(Arrays.stream(input.split(","))
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
