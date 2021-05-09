package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import css.be.exceptions.InvalidNumberException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class SubstractionCalculatorServiceTest {
    private final SubstractionCalculatorService substractionCalculatorService;
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    public SubstractionCalculatorServiceTest() {
        this.substractionCalculatorService = new SubstractionCalculatorService();
    }

    @Test
    public void operate_whenNumbersAreEqualsTo1_thenNoCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("1", "1");

        assertThat(substractionCalculatorService.operate(givenOperandsBody)).isEqualTo("0");
    }

    @Test
    public void operate_whenFirstNumberIs100andSecondIs9_thenCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("100", "9");

        assertThat(substractionCalculatorService.operate(givenOperandsBody)).isEqualTo("91");
    }

    @Test
    public void operate_whenFirstNumberIs1000andSecondIs99_thenCarryMultipleTimes() {
        OperandsBody givenOperandsBody = new OperandsBody("1000", "99");

        assertThat(substractionCalculatorService.operate(givenOperandsBody)).isEqualTo("901");
    }

    @Test
    public void operate_secondNumberIsBigger_thenThrowNegativeResultException() {
        OperandsBody givenOperandsBody = new OperandsBody("1", "10");
        expectedEx.expect(InvalidNumberException.class);
        expectedEx.expectMessage("Negative Result");
        substractionCalculatorService.operate(givenOperandsBody);
    }

}
