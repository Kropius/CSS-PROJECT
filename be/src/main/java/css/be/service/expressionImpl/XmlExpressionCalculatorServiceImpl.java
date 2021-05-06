package css.be.service.expressionImpl;

import css.be.common.ExpressionValidator;
import css.be.controllers.model.ExpressionResponse;
import css.be.controllers.model.ExpressionTree;
import css.be.service.ExpressionCalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class XmlExpressionCalculatorServiceImpl implements ExpressionCalculatorService {

    private ExpressionCalculatorServiceImpl expressionCalculatorService;

    @Autowired
    public XmlExpressionCalculatorServiceImpl (ExpressionCalculatorServiceImpl expressionCalculatorService) {
        this.expressionCalculatorService = expressionCalculatorService;
    }

    @Override
    public String calculate(String expression) {
        List<String> steps = new ArrayList<>();
        List<String> tokens = getListOfTokens(expression);
        ExpressionValidator.validate(tokens);
        ExpressionTree xmlExpressionTree = expressionCalculatorService.buildExpressionTree(tokens, expressionCalculatorService.createStartingTree().getLeft(), 0);
        String result = expressionCalculatorService.evaluateExpressionTree(xmlExpressionTree, steps);
        ExpressionResponse expressionResponse = new ExpressionResponse(steps, result);
        return expressionResponse.getXmlResponse();
    }

    @Override
    public List<String> getListOfTokens(String expression) {
        String continuousExpression = expression.replaceAll("[\\t|\\n|\\r|\\s]+", "");
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i<continuousExpression.length(); i++) {
            char c = continuousExpression.charAt(i);
            if (c == '<') {
                StringBuilder tag = new StringBuilder();
                for (int j = i + 1; j < continuousExpression.length(); j++) {
                    char nextc = continuousExpression.charAt(j);
                    if (nextc != '>')
                        tag.append(nextc);
                    else {
                        i = j;
                        break;
                    }
                }
                String tagValue = tag.toString();
                if (tagValue.equals("par"))
                    tokens.add("(");
                else if (tagValue.equals("/par"))
                    tokens.add(")");
                else if (tagValue.equals("operand") || tagValue.equals("operator")) {
                    StringBuilder token = new StringBuilder();
                    for (int j = i + 1; j < continuousExpression.length(); j++) {
                        char nextc = continuousExpression.charAt(j);
                        if (nextc != '<')
                            token.append(nextc);
                        else {
                            i = j;
                            break;
                        }
                    }
                    tokens.add(token.toString());
                    token.setLength(0);
                }

            }
        }
        return tokens;
    }

}
