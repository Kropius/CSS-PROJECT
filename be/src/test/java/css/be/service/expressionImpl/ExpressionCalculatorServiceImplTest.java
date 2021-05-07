package css.be.service.expressionImpl;

import css.be.controllers.model.OperandsBody;
import css.be.service.ExpressionCalculatorService;
import css.be.service.operationImpl.*;
import org.json.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

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
    public void checkListOfTokensForExpressionWithTwoOperands() {
        String testExpression = "5 ^ 2";
        List<String> expected = Arrays.asList("5", "^", "2");
        Assert.assertEquals(expected, expressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    public void checkListOfTokensForExpressionWithOneOperand() {
        String testExpression = "sqrt 9";
        List<String> expected = Arrays.asList("sqrt", "9");
        Assert.assertEquals(expected, expressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    public void checkListOfTokensForExpressionWithParentheses() {
        String testExpression = "(4 / 2)+1";
        List<String> expected = Arrays.asList("(", "4", "/", "2", ")", "+", "1");
        Assert.assertEquals(expected, expressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    public void checkListOfTokensForWrongCharacterIgnoring() {
        String testExpression = "asb10 -(%&5 @+ !@2)";
        List<String> expected = Arrays.asList("10", "-", "(", "5", "+", "2", ")");
        Assert.assertEquals(expected, expressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    public void fivePlusTwoExpressionEqualsSeven() throws JSONException {
        Mockito.when(additionCalculatorService.operate(new OperandsBody("5", "2"))).thenReturn("7");
        String result = expressionCalculatorService.calculate("5+2");
        JSONObject resultObject = null;
        try {
            resultObject = new JSONObject(result);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("7", resultObject.getString("finalResult"));
    }
}
