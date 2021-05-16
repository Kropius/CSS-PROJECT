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
        assert expression!=null && !expression.equals("") : "Expression can't be empty!";

        List<String> steps = new ArrayList<>();
        List<String> tokens = getListOfTokens(expression);

        assert !tokens.isEmpty() : "Can't operate with empty list of tokens!";
        ExpressionValidator.validate(tokens);
        ExpressionTree xmlExpressionTree = expressionCalculatorService.buildExpressionTree(tokens, expressionCalculatorService.createStartingTree().getLeft(), 0);
        String result = expressionCalculatorService.evaluateExpressionTree(xmlExpressionTree, steps);

        assert !steps.isEmpty() : "No operation was executed in this expression!";

        ExpressionResponse expressionResponse = new ExpressionResponse(steps, result);
        return expressionResponse.getXmlResponse();
    }

    @Override
    public List<String> getListOfTokens(String expression) {
        String continuousExpression = expression.replaceAll("[\\t|\\n|\\r|\\s]+", "");
        List<String> tokens = new ArrayList<>();

        assert !continuousExpression.matches(".*[\\t\\n\\r\\s].*") : "The elimination of indentation and spaces from the initial expression has failed!";

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

        String assertionRegex = "[+\\-*/^sqrt()0-9]+";
        for (String assertionToken: tokens)
            assert assertionToken.matches(assertionRegex) : "Parser found incorrect characters!";
        return tokens;
    }

}
