package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

  @Test
  void 유효한_번호로_로또_번호_생성_테스트() {
    LottoNumber minNumber = new LottoNumber(1);
    assertThat(minNumber.number()).isEqualTo(1);

    LottoNumber maxNumber = new LottoNumber(45);
    assertThat(maxNumber.number()).isEqualTo(45);

    LottoNumber middleNumber = new LottoNumber(23);
    assertThat(middleNumber.number()).isEqualTo(23);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, -1, -5, 46, 100})
  void 유효하지_않은_번호로_로또_번호_생성시_예외_발생_테스트(int invalidNumber) {
    assertThatThrownBy(() -> new LottoNumber(invalidNumber))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("로또 번호는 1부터 45 사이의 숫자여야 합니다");
  }

  @Test
  void 동일한_번호의_로또_번호_동등성_테스트() {
    // given
    LottoNumber number1 = new LottoNumber(7);
    LottoNumber number2 = new LottoNumber(7);
    LottoNumber number3 = new LottoNumber(13);

    // then
    assertThat(number1).isEqualTo(number2);
    assertThat(number1).isNotEqualTo(number3);
    assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
  }

  @Test
  void 로또_번호_문자열_변환_테스트() {
    LottoNumber number = new LottoNumber(15);

    assertThat(number.toString()).isEqualTo("15");
  }

  @Test
  void 로또_번호_레코드_동작_테스트() {
    LottoNumber number = new LottoNumber(42);

    assertThat(number.number()).isEqualTo(42);

    LottoNumber sameNumber = new LottoNumber(42);
    assertThat(number).isEqualTo(sameNumber);

    assertThat(number.hashCode()).isEqualTo(sameNumber.hashCode());

    assertThat(number.toString()).isEqualTo("42");
  }
}