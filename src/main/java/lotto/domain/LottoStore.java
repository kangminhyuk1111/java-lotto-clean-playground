package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoStore {

    private static final int LOTTO_LENGTH = 6;
    private static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_PRICE = 1000;

    private final List<Integer> lottoNumbers;

    public LottoStore() {
        this.lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    public List<Lotto> generateLottosByPayment(int payment) {
        validatePayment(payment);

        final List<Lotto> lottos = new ArrayList<>();

        int amount = payment / LOTTO_PRICE;

        for (int i = 0; i < amount; i++) {
            lottos.add(generateLotto());
        }

        return lottos;
    }

    private Lotto generateLotto() {
        Collections.shuffle(lottoNumbers);

        List<Integer> selectedNumbers = new ArrayList<>(lottoNumbers.subList(0, LOTTO_LENGTH));

        Collections.sort(selectedNumbers);

        return new Lotto(selectedNumbers);
    }

    private void validatePayment(int payment) {
        if (payment < LOTTO_PRICE) {
            throw new RuntimeException("로또 구매 금액은 최소 1000원 이상입니다.");
        }
    }
}
