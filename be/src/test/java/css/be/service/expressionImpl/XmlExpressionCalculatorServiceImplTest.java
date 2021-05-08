package css.be.service.expressionImpl;

import css.be.controllers.model.ExpressionResponse;
import css.be.controllers.model.ExpressionTree;
import css.be.controllers.model.Node;
import css.be.xml_test_helpers.XmlConstants;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XmlExpressionCalculatorServiceImplTest {

    private XmlExpressionCalculatorServiceImpl xmlExpressionCalculatorService;

    @Mock
    private ExpressionCalculatorServiceImpl expressionCalculatorService;


    public XmlExpressionCalculatorServiceImplTest() {
        expressionCalculatorService = Mockito.mock(ExpressionCalculatorServiceImpl.class);
        xmlExpressionCalculatorService = new XmlExpressionCalculatorServiceImpl(expressionCalculatorService);
    }

    @Test
    public void checkListOfTokensForExpressionWithTwoOperands() {
        String testExpression = XmlConstants.XML_TWO_OPERAND_OPERATION;
        List<String> expected = Arrays.asList("2", "+", "3");
        Assert.assertEquals(expected, xmlExpressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    public void checkListOfTokensForExpressionWithOneOperand() {
        String testExpression = XmlConstants.XML_ONE_OPERAND_OPERATION;
        List<String> expected = Arrays.asList("sqrt", "9");
        Assert.assertEquals(expected, xmlExpressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    public void checkListOfTokensForExpressionWithParentheses() {
        String testExpression = XmlConstants.XML_PARENTHESES_OPERATION_EXAMPLE;
        List<String> expected = Arrays.asList("(", "2", "+", "3", ")", "*", "(", "5", "+", "5", ")");
        Assert.assertEquals(expected, xmlExpressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    @Ignore("XML Parsing functionality does not ignore wrong characters. Validators will throw errors after parsing.")
    public void checkListOfTokensForWrongCharacterIgnoring() {
        String testExpression = XmlConstants.XML_WRONG_CHARACTERS_INPUT;
        List<String> expected = Arrays.asList("(", "2", "+", "3", ")", "*", "(", "5", "+", "5", ")");
        Assert.assertEquals(expected, xmlExpressionCalculatorService.getListOfTokens(testExpression));
    }

    @Test
    @Ignore("Fails because we have no reference to the 'steps' list. We can't mock it.")
    public void calculateComplexExpressionShouldReturnCorrectXmlResultAndSteps() {
        String testExpression = XmlConstants.XML_PARENTHESES_OPERATION_EXAMPLE;
        ExpressionTree testExpressionTree = new ExpressionTree(new Node(""), null, null, null);
        ExpressionTree testLeftSubTree = new ExpressionTree(new Node(""), null, null, testExpressionTree);
        testExpressionTree.setLeft(testLeftSubTree);

        ExpressionTree resultTree = new ExpressionTree(new Node("*"), null, null, null);
        ExpressionTree leftResultSubTree = new ExpressionTree(new Node("+"), null, null, resultTree);
        ExpressionTree rightResultSubTree = new ExpressionTree(new Node("+"), null, null, resultTree);
        resultTree.setLeft(leftResultSubTree);
        resultTree.setRight(rightResultSubTree);
        ExpressionTree leftOperationLeftSubTree = new ExpressionTree(new Node("2"), null, null, leftResultSubTree);
        ExpressionTree leftOperationRightSubTree = new ExpressionTree(new Node("3"), null, null, leftResultSubTree);
        leftResultSubTree.setLeft(leftOperationLeftSubTree);
        leftResultSubTree.setRight(leftOperationRightSubTree);
        ExpressionTree rightOperationLeftSubTree = new ExpressionTree(new Node("5"), null, null, rightResultSubTree);
        ExpressionTree rightOperationRightSubTree = new ExpressionTree(new Node("5"), null, null, rightResultSubTree);
        rightResultSubTree.setLeft(rightOperationLeftSubTree);
        rightResultSubTree.setRight(rightOperationRightSubTree);

        Mockito.when(expressionCalculatorService.createStartingTree()).thenReturn(testExpressionTree);
        Mockito.when(expressionCalculatorService.buildExpressionTree(xmlExpressionCalculatorService.getListOfTokens(testExpression), testExpressionTree, 0)).thenReturn(resultTree);
        Mockito.when(expressionCalculatorService.evaluateExpressionTree(Mockito.any(), Mockito.any())).thenReturn("50");

        Assert.assertEquals(xmlExpressionCalculatorService.calculate(XmlConstants.XML_PARENTHESES_OPERATION_EXAMPLE), XmlConstants.XML_RESPONSE_EXAMPLE);
    }
}
