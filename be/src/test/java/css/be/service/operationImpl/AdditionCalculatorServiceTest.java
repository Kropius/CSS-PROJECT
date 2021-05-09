package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import css.be.service.operationImpl.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


public class AdditionCalculatorServiceTest {

    private final AdditionCalculatorService additionCalculatorService;

    public AdditionCalculatorServiceTest() {
        this.additionCalculatorService = new AdditionCalculatorService();
    }

    @Test
    public void operate_whenNumbersAreOfSameOrder_NoCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("1", "1");

        additionCalculatorService.operate(givenOperandsBody);

        assertThat(additionCalculatorService.operate(givenOperandsBody)).isEqualTo("2");
    }

    @Test
    public void operate_whenNumbersAreOfDifferentOrder_NoCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("1", "12");
        String expectedResult = "13";

        String actualResult = additionCalculatorService.operate(givenOperandsBody);

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    public void operate_whenNumbersAreOfSameOrder_WithCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("19", "12");
        String expectedResult = "31";

        String actualResult = additionCalculatorService.operate(givenOperandsBody);

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    public void operate_whenNumbersAreOfDifferentOrder_WithCarry() {
        OperandsBody givenOperandsBody = new OperandsBody("199", "12");
        String expectedResult = "211";

        String actualResult = additionCalculatorService.operate(givenOperandsBody);

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    public void operate_whenNumbersAreOfDifferentOrder_WithCarry_ResultShouldBeOfHigherOrder() {
        OperandsBody givenOperandsBody = new OperandsBody("999", "12");
        String expectedResult = "1011";

        String actualResult = additionCalculatorService.operate(givenOperandsBody);

        assertThat(expectedResult).isEqualTo(actualResult);
    }


}
