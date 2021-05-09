package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplicationCalculatorServiceTest {

    private final MultiplicationCalculatorService multiplicationCalculatorService;

    public MultiplicationCalculatorServiceTest() {
        this.multiplicationCalculatorService = new MultiplicationCalculatorService();
    }

    @Test
    public void operate_whenNumbersAreEqualsTo1_thenNoCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("1", "1");

        assertThat(multiplicationCalculatorService.operate(givenOperandsBody)).isEqualTo("1");
    }

    @Test
    public void operate_whenNumbersAreEqualsTo99_thenCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("99", "99");

        assertThat(multiplicationCalculatorService.operate(givenOperandsBody)).isEqualTo("9801");
    }

    @Test
    public void operate_whenNumbersFirstNumberIs99AndSecondIs90_thenCarryAndOneLoopIsSkipped() {
        OperandsBody givenOperandsBody = new OperandsBody("99", "90");

        assertThat(multiplicationCalculatorService.operate(givenOperandsBody)).isEqualTo("8910");
    }

}
