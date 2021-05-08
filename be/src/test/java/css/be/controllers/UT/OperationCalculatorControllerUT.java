package css.be.controllers.UT;

import css.be.controllers.OperationCalculatorController;
import css.be.controllers.model.OperandsBody;
import css.be.service.operationImpl.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

public class OperationCalculatorControllerUT {

    private AdditionCalculatorService additionService;
    private SubstractionCalculatorService substractionService;
    private MultiplicationCalculatorService multiplicationService;
    private DivisionCalculatorService divisionService;
    private SquareRootCalculatorService squareRootService;
    private PowerCalculatorService powerService;

    private OperationCalculatorController operationCalculatorController;

    public OperationCalculatorControllerUT() {

        additionService = Mockito.mock(AdditionCalculatorService.class, this::getDefaultAnswer);
        substractionService = Mockito.mock(SubstractionCalculatorService.class, this::getDefaultAnswer);
        multiplicationService = Mockito.mock(MultiplicationCalculatorService.class, this::getDefaultAnswer);
        divisionService = Mockito.mock(DivisionCalculatorService.class, this::getDefaultAnswer);
        squareRootService = Mockito.mock(SquareRootCalculatorService.class, this::getDefaultAnswer);
        powerService = Mockito.mock(PowerCalculatorService.class, this::getDefaultAnswer);

        operationCalculatorController = new OperationCalculatorController(additionService,
                substractionService,
                multiplicationService,
                divisionService,
                squareRootService,
                powerService);
    }

    @Test
    public void test() {
        operationCalculatorController.addNumbers(new OperandsBody("1", "2"));
    }

    private Object getDefaultAnswer(InvocationOnMock invocationOnMock) {
        throw new RuntimeException(String.format("Mock exception on %s, method: %s", invocationOnMock.getMock().getClass().getName(), invocationOnMock.getMethod().getName()));
    }
}
