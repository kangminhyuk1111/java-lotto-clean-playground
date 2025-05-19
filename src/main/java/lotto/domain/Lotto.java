package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

  private static final int LOTTO_SIZE = 6;
  private static final int MIN_LOTTO_NUMBER = 1;
  private static final int MAX_LOTTO_NUMBER = 45;

  private final List<Integer> numbers;

  public Lotto(final List<Integer> numbers) {
    validateSize(numbers);
    validateDuplicateNumbers(numbers);
    validateNumberRange(numbers);
    this.numbers = numbers;
  }

  public String toString() {
    return numbers.toString();
  }

  public int size() {
    return numbers.size();
  }

  public List<Integer> getNumbers() {
    return numbers;
  }

  private void validateSize(List<Integer> numbers) {
    if (numbers.size() != LOTTO_SIZE) {
      throw new RuntimeException("로또 번호는 6개여야 합니다.");
    }
  }

  private void validateDuplicateNumbers(List<Integer> numbers) {
    Set<Integer> uniqueNumbers = new HashSet<>(numbers);
    if (uniqueNumbers.size() != numbers.size()) {
      throw new RuntimeException("로또 번호에 중복된 숫자가 있습니다.");
    }
  }

  private void validateNumberRange(List<Integer> numbers) {
    for (int number : numbers) {
      if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
        throw new RuntimeException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
      }
    }
  }
}
