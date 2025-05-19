package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoStoreTest {

  private LottoStore lottoStore;

  @BeforeEach
  void setUp() {
    lottoStore = new LottoStore();
    lottoStore.mergeLottos(List.of(
        new Lotto(List.of(1,2,3,4,5,6))
    ));
  }

  @Test
  void 구입_금액에_맞게_로또가_발급되어야_한다() {
    int payment = 1000;
    List<Lotto> lottos = lottoStore.generateLottosByPayment(payment);

    assertThat(lottos).hasSize(1);
  }

  @ParameterizedTest
  @ValueSource(ints = {1000, 2000, 5000, 10000})
  void 금액에_해당하는_만큼_로또_수량이_정확해야_한다(int payment) {
    int expectedCount = payment / LottoStore.LOTTO_PRICE;

    List<Lotto> lottos = lottoStore.generateLottosByPayment(payment);

    assertThat(lottos).hasSize(expectedCount);
  }

  @Test
  void 로또_가격보다_적은_금액으로_구매하면_예외가_발생한다() {
    int invalidPayment = 900;

    assertThatThrownBy(() -> lottoStore.generateLottosByPayment(invalidPayment))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  void 생성된_로또_번호는_오름차순으로_정렬되어야_한다() {
    List<Lotto> lottos = lottoStore.generateLottosByPayment(1000);
    Lotto lotto = lottos.get(0);
    List<Integer> numbers = lotto.getNumbers();

    for (int i = 0; i < numbers.size() - 1; i++) {
      assertThat(numbers.get(i)).isLessThan(numbers.get(i + 1));
    }
  }
}