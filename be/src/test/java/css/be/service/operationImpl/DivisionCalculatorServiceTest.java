package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DivisionCalculatorServiceTest {

    private final DivisionCalculatorService divisionCalculatorService;

    public DivisionCalculatorServiceTest() {
        this.divisionCalculatorService = new DivisionCalculatorService();
    }

    @Test
    public void operate_whenDivisorIsSmall_thenPerformDivisionForSmallDivisorWillBeCalled() {
        OperandsBody givenOperandsBody = new OperandsBody("1", "1");

        assertThat(divisionCalculatorService.operate(givenOperandsBody)).isEqualTo("1");
    }

    @Test
    public void operate_whenDivisorIsBig_thenPerformDivisionForBigDivisorWillBeCalled() {
        OperandsBody givenOperandsBody = new OperandsBody("10000000000000000000", "1000000000000000000");

        assertThat(divisionCalculatorService.operate(givenOperandsBody)).isEqualTo("10");
    }

    @Test
    public void operate_whenDivisorIsBigger_thenResultShouldBe0() {
        OperandsBody givenOperandsBody = new OperandsBody("1", "1000000000000000000");

        assertThat(divisionCalculatorService.operate(givenOperandsBody)).isEqualTo("0");
    }

}
