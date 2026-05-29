package ec.edu.epn.skyroute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BaggageFeeCalculatorTest {
    @Mock
    private PassengerService passengerService;
    
    @InjectMocks
    private BaggageFeeCalculator calculator;

    @Test
    void shouldThrow_whenPassengerIdIsNull() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> calculator.calculateFee(20.0, 1, null));
    }

    @Test
    void shouldThrow_whenWeightIsZero() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> calculator.calculateFee(0.0, 1, 1L));
    }

    @ParameterizedTest 
    @ValueSource(doubles = {-0.01, -1.0, -100.0})
    void shouldThrow_whenWeightIsNegative(double negativeWeight) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> calculator.calculateFee(negativeWeight, 1, 1L));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    void shouldThrow_whenBagCountIsLessThanOne(int invalidCount) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> calculator.calculateFee(20.0, invalidCount, 1L));
    }

    @Nested
    class StandarPassenger {
        
        @BeforeEach
        void setup() {
            when(passengerService.isVip(1L)).thenReturn(false);
        }

        @Test
        void oneBag_normalWeight_shouldReturn30(){
            double fee = calculator.calculateFee(20.0, 1, 1L);
            assertThat(fee).isEqualTo(30.0);
        }
    }
}
