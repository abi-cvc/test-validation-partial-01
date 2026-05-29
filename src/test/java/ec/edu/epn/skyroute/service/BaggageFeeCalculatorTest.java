package ec.edu.epn.skyroute.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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
}
