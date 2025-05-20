package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoStore {

  private static final int LOTTO_LENGTH = 6;
  private static final int MIN_LOTTO_NUMBER = 1;
  private static final int MAX_LOTTO_NUMBER = 45;
  public static final int LOTTO_PRICE = 1000;

  private final List<LottoNumber> lottoNumberPool;

  public LottoStore() {
    this.lottoNumberPool = new ArrayList<>();
    for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
      lottoNumberPool.add(LottoNumber.of(i));
    }
  }

  public Lottos generateLottosWithManualLottos(final Lottos manualLottos, final int payment) {
    validatePayment(payment);

    int autoLottoCount = payment / LOTTO_PRICE;
    List<Lotto> combinedLottos = new ArrayList<>(manualLottos.lottos());

    for (int i = 0; i < autoLottoCount - manualLottos.size(); i++) {
      combinedLottos.add(generateLotto());
    }

    return new Lottos(combinedLottos);
  }

  private Lotto generateLotto() {
    List<LottoNumber> numberPool = new ArrayList<>(lottoNumberPool);

    Collections.shuffle(numberPool);

    List<LottoNumber> selectedNumbers = numberPool.subList(0, LOTTO_LENGTH);

    List<LottoNumber> sortedNumbers = new ArrayList<>(selectedNumbers);
    sortedNumbers.sort(Comparator.comparing(LottoNumber::number));

    return new Lotto(sortedNumbers);
  }

  private void validatePayment(int payment) {
    if (payment < LOTTO_PRICE) {
      throw new RuntimeException("로또 구매 금액은 최소 1000원 이상입니다.");
    }
  }
}
