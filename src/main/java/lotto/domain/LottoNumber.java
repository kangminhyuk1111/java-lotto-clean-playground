package lotto.domain;

import java.util.Objects;

public record LottoNumber(int number) {

  private static final int MIN_LOTTO_NUMBER = 1;
  private static final int MAX_LOTTO_NUMBER = 45;

  public LottoNumber {
    validateNumberRange(number);
  }

  private void validateNumberRange(int number) {
    if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
      throw new IllegalArgumentException(
          String.format("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.",
              MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
      );
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LottoNumber that = (LottoNumber) o;
    return number == that.number;
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }

  @Override
  public String toString() {
    return String.valueOf(number);
  }
}
