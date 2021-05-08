package css.be.controllers.UT;

import css.be.controllers.OperationCalculatorController;
import css.be.controllers.model.OperandsBody;
import css.be.service.operationImpl.*;
import org.junit.Test;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import org.mockito.invocation.InvocationOnMock;

import static org.junit.Assert.*;

public class OperationCalculatorControllerTest {

    private AdditionCalculatorService additionService;
    private SubstractionCalculatorService substractionService;
    private MultiplicationCalculatorService multiplicationService;
    private DivisionCalculatorService divisionService;
    private SquareRootCalculatorService squareRootService;
    private PowerCalculatorService powerService;

    private OperationCalculatorController operationCalculatorController;

    public OperationCalculatorControllerTest() {

        additionService = mock(AdditionCalculatorService.class, this::getDefaultAnswer);
        substractionService = mock(SubstractionCalculatorService.class, this::getDefaultAnswer);
        multiplicationService = mock(MultiplicationCalculatorService.class, this::getDefaultAnswer);
        divisionService = mock(DivisionCalculatorService.class, this::getDefaultAnswer);
        squareRootService = mock(SquareRootCalculatorService.class, this::getDefaultAnswer);
        powerService = mock(PowerCalculatorService.class, this::getDefaultAnswer);

        operationCalculatorController = new OperationCalculatorController(additionService,
                substractionService,
                multiplicationService,
                divisionService,
                squareRootService,
                powerService);
    }

    @Test
    public void addNumbers_shouldReturnCorrectResult() {
        OperandsBody givenInput = new OperandsBody("1", "2");
        String expectedResult = "3";
        doAnswer(iom->expectedResult).when(additionService).operate(givenInput);

        String actualResult = operationCalculatorController.addNumbers(givenInput);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void subtractNumbers_shouldReturnCorrectResult() {
        OperandsBody givenInput = new OperandsBody("2", "0");
        String expectedResult = "2";
        doAnswer(iom->expectedResult).when(substractionService).operate(givenInput);

        String actualResult = operationCalculatorController.subtractNumbers(givenInput);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void divideNumbers_shouldReturnCorrectResult() {
        OperandsBody givenInput = new OperandsBody("2", "1");
        String expectedResult = "2";
        doAnswer(iom->expectedResult).when(divisionService).operate(givenInput);

        String actualResult = operationCalculatorController.divideNumbers(givenInput);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void multiplyNumbers_shouldReturnCorrectResult() {
        OperandsBody givenInput = new OperandsBody("2", "0");
        String expectedResult = "0";
        doAnswer(iom->expectedResult).when(multiplicationService).operate(givenInput);

        String actualResult = operationCalculatorController.multiplyNumbers(givenInput);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void powerNumbers_shouldReturnCorrectResult() {
        OperandsBody givenInput = new OperandsBody("2", "0");
        String expectedResult = "1";
        doAnswer(iom->expectedResult).when(powerService).operate(givenInput);

        String actualResult = operationCalculatorController.powerNumbers(givenInput);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void squareNumber_shouldReturnResult() {
        String givenInput = "4";
        String expectedResult = "2";
        doAnswer(iom->expectedResult).when(squareRootService).operate(givenInput);

        String actualResult = operationCalculatorController.squareNumber(givenInput);

        assertEquals(expectedResult, actualResult);
    }

    private Object getDefaultAnswer(InvocationOnMock invocationOnMock) {
        throw new RuntimeException(String.format("Mock exception on %s, method: %s", invocationOnMock.getMock().getClass().getName(), invocationOnMock.getMethod().getName()));
    }
}
