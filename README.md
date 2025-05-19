# 로또 - 클린코드

## 1단계 - 로또 자동 구매

### 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
  - 사용자에게 input으로 금액을 입력받아 파싱한다.


- 로또 1장의 가격은 1000원이다.
  - 로또 구매 금액을 입력 받는다. ex) 14,000원
  - 1,000원에 1개의 로또를 살 수 있다. ex) 14,000원의 경우 14장의 로또가 지급된다.


- 로또 자동 생성은 **Collections.shuffle()** 메소드 활용한다.
  - **shuffle 메서드**를 사용해야하는 이유는, NumberGenerate 함수를 만들어서 사용시에, 중복되는 숫자가 존재 할 경우가 존재함.
  - **shuffle 메서드**는 1~45 까지의 숫자를 먼저 random하게 섞고 이후 앞의 수 6개를 가져올 수 있기 때문에 중복 수가 존재하지 않도록 관리가 가능함.


- Collections.sort() 메소드를 활용해 정렬 가능하다.
  - Collections.shuffle()을 통해 숫자를 가져온 후, Collections.sort()로 정렬 후 반환.


- ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

## 2단계 - 로또 당첨

### 기능 요구 사항
- 로또 당첨 번호를 받아 일치한 번호 수에 따라 당첨 결과를 보여준다.
  - 로또 당첨 번호를 체크하는 LottoResultChecker 객체에서 로또 당첨 결과를 반환한다.
  - InputParser를 통해 입력값을 체크한다.
- 객체 책임
  - LottoRank: 로또 당첨 등수를 나타내는 Enum으로 miss부터 six까지의 등급을 가짐
  - LottoResult: 당첨 번호를 가지는 객체 `Lotto`를 상속하여 같은 로또이지만 당첨 결과값이라는 부분이 다름 비교하여 몇개가 맞는지 matchCount를 반환함
  - LottoResultChecker: LottoResult를 기반으로 결과를 반환하도록 함.
  - WinningResult: LottoResultChecker에서 반환한 결과로, 금액 및 통계 계산을 해줌.
