package css.be.service.expressionImpl;

import css.be.controllers.model.ExpressionTree;
import css.be.controllers.model.Node;
import css.be.controllers.model.OperandsBody;
import css.be.service.operationImpl.*;
import org.json.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionCalculatorServiceImplTest {

    private AdditionCalculatorService additionCalculatorService;
    private DivisionCalculatorService divisionCalculatorService;
    private MultiplicationCalculatorService multiplicationCalculatorService;
    private PowerCalculatorService powerCalculatorService;
    private SquareRootCalculatorService squareRootCalculatorService;
    private SubstractionCalculatorService substractionCalculatorService;

    private ExpressionCalculatorServiceImpl expressionCalculatorService;

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
    public void onlyRightTreeNodeShouldBeCreatedForSqrtOperation() {
        List<String> sqrtTokens = Arrays.asList("sqrt", "9");
        ExpressionTree result = expressionCalculatorService.buildExpressionTree(sqrtTokens, expressionCalculatorService.createStartingTree().getLeft(), 0);
        Assert.assertEquals(result.getRoot().getValue(), "sqrt");
        Assert.assertNull(result.getLeft());
        Assert.assertEquals(result.getRight().getRoot().getValue(), "9");
    }

    @Test
    public void bothChildrenNodesShouldBeCreatedForTwoOperandOperation() {
        List<String> divisionTokens = Arrays.asList("21", "/", "9");
        ExpressionTree result = expressionCalculatorService.buildExpressionTree(divisionTokens, expressionCalculatorService.createStartingTree().getLeft(), 0);
        Assert.assertEquals(result.getRoot().getValue(), "/");
        Assert.assertEquals(result.getLeft().getRoot().getValue(), "21");
        Assert.assertEquals(result.getRight().getRoot().getValue(), "9");
    }

    @Test
    public void parenthesesShouldCreateSubtree() {
        List<String> sqrtTokens = Arrays.asList("(", "21", "/", "9", ")", "+", "10");
        ExpressionTree result = expressionCalculatorService.buildExpressionTree(sqrtTokens, expressionCalculatorService.createStartingTree().getLeft(), 0);
        Assert.assertEquals(result.getRoot().getValue(), "+");
        Assert.assertEquals(result.getLeft().getRoot().getValue(), "/");
        Assert.assertEquals(result.getLeft().getLeft().getRoot().getValue(), "21");
        Assert.assertEquals(result.getLeft().getRight().getRoot().getValue(), "9");
        Assert.assertEquals(result.getRight().getRoot().getValue(), "10");
    }

    @Test
    public void evaluateTreeAdditionShouldReturnCorrectResultAndSteps() {

        Mockito.when(additionCalculatorService.operate(new OperandsBody("5", "3"))).thenReturn("8");
        ExpressionTree testTree = expressionCalculatorService.createStartingTree();
        testTree.setRoot(new Node("+"));
        testTree.setLeft(new ExpressionTree(new Node("5"), null, null, testTree));
        testTree.setRight(new ExpressionTree(new Node("3"), null, null, testTree));
        String expectedResult = "8";
        List<String> expectedSteps = Arrays.asList("5 + 3 = 8");

        List<String> actualSteps = new ArrayList<>();
        String actualResult = expressionCalculatorService.evaluateExpressionTree(testTree, actualSteps);

        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void evaluateTreeSubstractionShouldReturnCorrectResultAndSteps() {

        Mockito.when(substractionCalculatorService.operate(new OperandsBody("5", "3"))).thenReturn("2");
        ExpressionTree testTree = expressionCalculatorService.createStartingTree();
        testTree.setRoot(new Node("-"));
        testTree.setLeft(new ExpressionTree(new Node("5"), null, null, testTree));
        testTree.setRight(new ExpressionTree(new Node("3"), null, null, testTree));
        String expectedResult = "2";
        List<String> expectedSteps = Arrays.asList("5 - 3 = 2");

        List<String> actualSteps = new ArrayList<>();
        String actualResult = expressionCalculatorService.evaluateExpressionTree(testTree, actualSteps);

        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void evaluateTreeMultiplicationShouldReturnCorrectResultAndSteps() {

        Mockito.when(multiplicationCalculatorService.operate(new OperandsBody("5", "3"))).thenReturn("15");
        ExpressionTree testTree = expressionCalculatorService.createStartingTree();
        testTree.setRoot(new Node("*"));
        testTree.setLeft(new ExpressionTree(new Node("5"), null, null, testTree));
        testTree.setRight(new ExpressionTree(new Node("3"), null, null, testTree));
        String expectedResult = "15";
        List<String> expectedSteps = Arrays.asList("5 * 3 = 15");

        List<String> actualSteps = new ArrayList<>();
        String actualResult = expressionCalculatorService.evaluateExpressionTree(testTree, actualSteps);

        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void evaluateTreeDivisionShouldReturnCorrectResultAndSteps() {

        Mockito.when(divisionCalculatorService.operate(new OperandsBody("5", "3"))).thenReturn("1");
        ExpressionTree testTree = expressionCalculatorService.createStartingTree();
        testTree.setRoot(new Node("/"));
        testTree.setLeft(new ExpressionTree(new Node("5"), null, null, testTree));
        testTree.setRight(new ExpressionTree(new Node("3"), null, null, testTree));
        String expectedResult = "1";
        List<String> expectedSteps = Arrays.asList("5 / 3 = 1");

        List<String> actualSteps = new ArrayList<>();
        String actualResult = expressionCalculatorService.evaluateExpressionTree(testTree, actualSteps);

        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void evaluateTreePowerShouldReturnCorrectResultAndSteps() {

        Mockito.when(powerCalculatorService.operate(new OperandsBody("5", "3"))).thenReturn("125");
        ExpressionTree testTree = expressionCalculatorService.createStartingTree();
        testTree.setRoot(new Node("^"));
        testTree.setLeft(new ExpressionTree(new Node("5"), null, null, testTree));
        testTree.setRight(new ExpressionTree(new Node("3"), null, null, testTree));
        String expectedResult = "125";
        List<String> expectedSteps = Arrays.asList("5 ^ 3 = 125");

        List<String> actualSteps = new ArrayList<>();
        String actualResult = expressionCalculatorService.evaluateExpressionTree(testTree, actualSteps);

        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void evaluateTreeSqrtShouldReturnCorrectResultAndSteps() {

        Mockito.when(squareRootCalculatorService.operate("25")).thenReturn("5");
        ExpressionTree testTree = expressionCalculatorService.createStartingTree();
        testTree.setRoot(new Node("sqrt"));
        testTree.setLeft(null);
        testTree.setRight(new ExpressionTree(new Node("25"), null, null, testTree));
        String expectedResult = "5";
        List<String> expectedSteps = Arrays.asList("sqrt 25 = 5");

        List<String> actualSteps = new ArrayList<>();
        String actualResult = expressionCalculatorService.evaluateExpressionTree(testTree, actualSteps);

        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSteps, actualSteps);
    }

    @Test
    public void calculateComplexExpressionShouldReturnCorrectResultAndSteps() throws JSONException {

        String testExpression = "(3+2)^(20 / 10)";
        Mockito.when(additionCalculatorService.operate(new OperandsBody("3", "2"))).thenReturn("5");
        Mockito.when(divisionCalculatorService.operate(new OperandsBody("20", "10"))).thenReturn("2");
        Mockito.when(powerCalculatorService.operate(new OperandsBody("5", "2"))).thenReturn("25");
        String result = expressionCalculatorService.calculate(testExpression);
        JSONObject jsonResult = null;
        try {
            jsonResult = new JSONObject(result);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        String expectedResult = "25";
        List<String> expectedSteps = Arrays.asList("3 + 2 = 5","20 / 10 = 2","5 ^ 2 = 25");
        String actualResult = jsonResult.getString("finalResult");
        JSONArray jsonSteps = jsonResult.getJSONArray("intermediaryOperations");
        List<String> actualSteps = new ArrayList<>();
        for (int i = 0; i<jsonSteps.length(); i++)
            actualSteps.add(jsonSteps.getString(i));
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualSteps, expectedSteps);
    }
}
