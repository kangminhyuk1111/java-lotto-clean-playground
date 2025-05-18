package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoStore;
import lotto.ui.InputView;
import lotto.ui.OutputView;

import java.util.List;

public class LottoController {

    private final LottoStore lottoStore = new LottoStore();

    public void run() {
        final int payment = InputView.inputPayment();

        final List<Lotto> lottos = lottoStore.generateLottosByPayment(payment);

        OutputView.printLottos(lottos);
    }
}
