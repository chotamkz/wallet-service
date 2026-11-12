package kz.donerlong.wallet.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    void amountsWithDifferentScaleAreEqual() {
        assertThat(Money.of("10")).isEqualTo(Money.of("10.00"));
    }

    @Test
    void rejectsMoreThanTwoDecimalPlaces() {
        assertThatThrownBy(() -> Money.of("10.005")).isInstanceOf(InvalidAmountException.class);
    }

    @Test
    void minusBelowZeroIsRejected() {
        assertThatThrownBy(() -> Money.of("5.00").minus(Money.of("5.01"))).isInstanceOf(InvalidAmountException.class);
    }
}
