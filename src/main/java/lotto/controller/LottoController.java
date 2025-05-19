package lotto.controller;

import lotto.domain.*;
import lotto.ui.InputView;
import lotto.ui.OutputView;

import java.util.List;

public class LottoController {

  private final LottoStore lottoStore = new LottoStore();

  public void run() {
    final int payment = InputView.inputPayment();

    final int manualLottoCount = InputView.inputManualLottoCount();

    final List<Lotto> manualLottos = InputView.inputManualLottos(manualLottoCount);

    lottoStore.mergeLottos(manualLottos);

    final List<Lotto> userLottos = lottoStore.generateLottosByPayment(payment);

    OutputView.printLottos(userLottos);

    final LottoResult lottoResult = InputView.inputLottoResult();

    final int bonusBall = InputView.inputBonusBall();

    final LottoResultChecker lottoResultChecker = new LottoResultChecker(lottoResult, bonusBall);

    final WinningResult winningResult = lottoResultChecker.matchLottos(userLottos);

    OutputView.printResults(winningResult);

    OutputView.printPrize(winningResult, payment);
  }
}
