package css.be.service.expressionImpl;

import css.be.controllers.model.OperandsBody;
import css.be.service.ExpressionCalculatorService;
import css.be.service.operationImpl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ExpressionCalculatorServiceImplTest {

    private AdditionCalculatorService additionCalculatorService;
    private DivisionCalculatorService divisionCalculatorService;
    private MultiplicationCalculatorService multiplicationCalculatorService;
    private PowerCalculatorService powerCalculatorService;
    private SquareRootCalculatorService squareRootCalculatorService;
    private SubstractionCalculatorService substractionCalculatorService;

    private ExpressionCalculatorService expressionCalculatorService;

    public ExpressionCalculatorServiceImplTest() {
        additionCalculatorService = Mockito.mock(AdditionCalculatorService.class);
        divisionCalculatorService = Mockito.mock(DivisionCalculatorService.class);
        multiplicationCalculatorService = Mockito.mock(MultiplicationCalculatorService.class);
        powerCalculatorService = Mockito.mock(PowerCalculatorService.class);
        squareRootCalculatorService = Mockito.mock(SquareRootCalculatorService.class);
        substractionCalculatorService = Mockito.mock(SubstractionCalculatorService.class);
        expressionCalculatorService = new ExpressionCalculatorServiceImpl(
                additionCalculatorService,
                divisionCalculatorService,
                multiplicationCalculatorService,
                squareRootCalculatorService,
                powerCalculatorService,
                substractionCalculatorService
        );
    }

    @Test
    public void FivePlusTwoExpressionEqualsSeven() {
        Mockito.when(additionCalculatorService.operate(new OperandsBody("5", "2"))).thenReturn("7");
        Assert.assertEquals("7", expressionCalculatorService.calculate("5+2"));
    }
}
