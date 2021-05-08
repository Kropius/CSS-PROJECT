package css.be.controllers.UT;

import css.be.controllers.XmlExpressionCalculatorController;
import css.be.service.expressionImpl.XmlExpressionCalculatorServiceImpl;
import css.be.xml_test_helpers.XmlConstants;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class XmlExpressionCalculatorControllerTest {

    private XmlExpressionCalculatorServiceImpl xmlExpressionCalculatorService;

    private XmlExpressionCalculatorController xmlExpressionCalculatorController;

    public XmlExpressionCalculatorControllerTest() {

        xmlExpressionCalculatorService = mock(XmlExpressionCalculatorServiceImpl.class);
        xmlExpressionCalculatorController = new XmlExpressionCalculatorController(xmlExpressionCalculatorService);
    }

    @Test
    public void calculateXmlExpressionShouldReturnCorrectResult() {
        String testExpression = XmlConstants.XML_PARENTHESES_OPERATION_EXAMPLE;
        String expectedResult = "50";
        Mockito.when(xmlExpressionCalculatorService.calculate(testExpression)).thenReturn("50");
        String actualResult = xmlExpressionCalculatorController.calculateExpression(testExpression);
        assertEquals(expectedResult, actualResult);
    }
}
