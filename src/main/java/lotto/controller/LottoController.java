package lotto.controller;

import lotto.domain.*;
import lotto.ui.InputView;
import lotto.ui.OutputView;

import java.util.List;

public class LottoController {

  private final LottoStore lottoStore = new LottoStore();

  public void run() {
    final int payment = InputView.inputPayment();

    final List<Lotto> userLottos = lottoStore.generateLottosByPayment(payment);

    OutputView.printLottos(userLottos);

    final LottoResult lottoResult = InputView.inputLottoResult();

    final LottoResultChecker lottoResultChecker = new LottoResultChecker(lottoResult);

    final WinningResult matchLottosResult = lottoResultChecker.matchLottos(userLottos);

    OutputView.printResults(matchLottosResult);

    OutputView.printPrize(matchLottosResult, payment);
  }
}
