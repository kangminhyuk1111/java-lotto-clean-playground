package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoResult extends Lotto {

  public LottoResult(final List<Integer> numbers) {
    super(numbers);
  }

  public int matchCount(Lotto userLotto) {
    List<LottoNumber> winningNumbers = this.getNumbers();
    List<LottoNumber> userNumbers = userLotto.getNumbers();

    Set<LottoNumber> winningNumbersSet = new HashSet<>(winningNumbers);

    int count = 0;
    for (LottoNumber number : userNumbers) {
      if (winningNumbersSet.contains(number)) {
        count++;
      }
    }

    return count;
  }
}
