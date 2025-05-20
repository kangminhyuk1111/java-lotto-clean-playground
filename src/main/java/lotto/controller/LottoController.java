package lotto.controller;

import lotto.domain.*;
import lotto.ui.InputView;
import lotto.ui.OutputView;

public class LottoController {

  private final LottoStore lottoStore = new LottoStore();

  public void run() {
    final int payment = InputView.inputPayment();

    final int manualLottoCount = InputView.inputManualLottoCount();

    final Lottos manualLottos = InputView.inputManualLottos(manualLottoCount);

    final Lottos userLottos = lottoStore.generateLottosWithManualLottos(manualLottos, payment);

    OutputView.printLottos(userLottos);

    final LottoResult lottoResult = InputView.inputLottoResult();

    final LottoNumber bonusBall = InputView.inputBonusBall();

    final LottoResultChecker lottoResultChecker = new LottoResultChecker(lottoResult, bonusBall);

    final WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);

    OutputView.printResults(winningResult);

    OutputView.printPrize(winningResult, payment);
  }
}
