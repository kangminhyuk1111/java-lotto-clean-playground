package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

  private static final int LOTTO_SIZE = 6;

  private final List<LottoNumber> numbers;

  public Lotto(final List<Integer> numbers) {
    validateSize(numbers);
    validateDuplicateNumbers(numbers);

    List<LottoNumber> lottoNumbers = new ArrayList<>();

    for (Integer number : numbers) {
      lottoNumbers.add(new LottoNumber(number));
    }

    this.numbers = new ArrayList<>(lottoNumbers);
  }

  public String toString() {
    return numbers.toString();
  }

  public int size() {
    return numbers.size();
  }

  public List<LottoNumber> getNumbers() {
    return new ArrayList<>(numbers);
  }

  public boolean isContainBonusBall(final LottoNumber bonusBall) {
    return numbers.contains(bonusBall);
  }

  private void validateSize(List<Integer> numbers) {
    if (numbers.size() != LOTTO_SIZE) {
      throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
    }
  }

  private void validateDuplicateNumbers(List<Integer> numbers) {
    Set<Integer> uniqueNumbers = new HashSet<>(numbers);
    if (uniqueNumbers.size() != numbers.size()) {
      throw new IllegalArgumentException("로또 번호에 중복된 숫자가 있습니다.");
    }
  }
}