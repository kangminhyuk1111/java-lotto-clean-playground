package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoResult extends Lotto {

    public LottoResult(final List<Integer> numbers) {
        super(numbers);
    }

    public int matchCount(Lotto userLotto) {
        List<Integer> winningNumbers = this.getNumbers();
        List<Integer> userNumbers = userLotto.getNumbers();

        Set<Integer> winningNumbersSet = new HashSet<>(winningNumbers);

        int count = 0;
        for (Integer number : userNumbers) {
            if (winningNumbersSet.contains(number)) {
                count++;
            }
        }

        return count;
    }
}
