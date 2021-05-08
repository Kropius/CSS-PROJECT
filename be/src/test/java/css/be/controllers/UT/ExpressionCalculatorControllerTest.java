package css.be.controllers.UT;

import css.be.controllers.ExpressionCalculatorController;
import css.be.service.expressionImpl.ExpressionCalculatorServiceImpl;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ExpressionCalculatorControllerTest {

    private ExpressionCalculatorServiceImpl expressionCalculatorService;

    private ExpressionCalculatorController expressionCalculatorController;

    public ExpressionCalculatorControllerTest() {

        expressionCalculatorService = mock(ExpressionCalculatorServiceImpl.class);
        expressionCalculatorController = new ExpressionCalculatorController(expressionCalculatorService);
    }

    @Test
    public void calculateStringExpressionShouldReturnCorrectResult() {
        String testExpression = "sqrt (3+6)";
        String expectedResult = "3";
        Mockito.when(expressionCalculatorService.calculate(testExpression)).thenReturn("3");
        String actualResult = expressionCalculatorController.calculateExpression(testExpression);
        assertEquals(expectedResult, actualResult);
    }
}
