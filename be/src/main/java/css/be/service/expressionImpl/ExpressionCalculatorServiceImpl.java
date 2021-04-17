package css.be.service.expressionImpl;

import css.be.controllers.model.ExpressionTree;
import css.be.controllers.model.Node;
import css.be.controllers.model.OperandsBody;
import css.be.service.ExpressionCalculatorService;
import css.be.service.operationImpl.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpressionCalculatorServiceImpl implements ExpressionCalculatorService {

    private AdditionCalculatorService additionCalculatorService = new AdditionCalculatorService();
    private DivisionCalculatorService divisionCalculatorService = new DivisionCalculatorService();
    private MultiplicationCalculatorService multiplicationCalculatorService = new MultiplicationCalculatorService();
    private SquareRootCalculatorService squareRootCalculatorService = new SquareRootCalculatorService();
    private PowerCalculatorService powerCalculatorService = new PowerCalculatorService();
    private SubstractionCalculatorService substractionCalculatorService = new SubstractionCalculatorService();

    @Override
    public String calculate(String expression) {
        ExpressionTree expressionTree = new ExpressionTree(new Node(""), null, null, null);
        ExpressionTree leftSubTree = new ExpressionTree(new Node(""), null, null, expressionTree);
        expressionTree.setLeft(leftSubTree);
        return evaluateExpressionTree(buildExpressionTree(getListOfTokens(expression), leftSubTree, 0));
    }

    private List<String> getListOfTokens(String expression) {
        String digits = "0123456789";
        String operators = "(+-*/^)";
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        for (int letterIndex = 0; letterIndex < expression.length(); letterIndex++) {
            char c = expression.charAt(letterIndex);
            if (operators.indexOf(c) > -1) {
                tokens.add(String.valueOf(c));

            }
            else if (digits.indexOf(c) > -1) {
                int i = letterIndex;
                while (i < expression.length()) {
                    if (digits.indexOf(expression.charAt(i)) > -1)
                        token.append(expression.charAt(i));
                    else
                        break;
                    i++;
                }
                letterIndex = i - 1;
                tokens.add(token.toString());
                token.setLength(0);
            }

            else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                int i = letterIndex;
                while (i < expression.length()) {
                    c = expression.charAt(i);
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                        token.append(c);
                    else
                        break;
                    i++;
                }
                letterIndex = i - 1;
                if (token.toString().equalsIgnoreCase("sqrt"))
                    tokens.add(token.toString());
                token.setLength(0);
            }

        }

        return tokens;
    }

    private ExpressionTree buildExpressionTree(List<String> tokens, ExpressionTree root, int index) {
        if (index >= tokens.size()) {
            return root;
        }
        String token = tokens.get(index);
        if (token.equals("(")) {
            root.setLeft(new ExpressionTree(new Node(""), null, null, root));
            return buildExpressionTree(tokens, root.getLeft(), index + 1);
        }
        if (token.equals(")")) {
            return buildExpressionTree(tokens, root.getParent(), index + 1);
        }
        if ("+-/*^sqrt".contains(token)) {
            root.getRoot().setValue(token);
            root.setRight(new ExpressionTree(new Node(""), null, null, root));
            return buildExpressionTree(tokens, root.getRight(), index + 1);
        }
        else {
            root.getRoot().setValue(token);
            return buildExpressionTree(tokens, root.getParent(), index + 1);
        }
    }

    private String evaluateExpressionTree(ExpressionTree expressionTree) {
        if (expressionTree.getRoot().getValue().equals("+")) {
//            return additionCalculatorService.operate(new OperandsBody(evaluateExpressionTree(expressionTree.getLeft()), evaluateExpressionTree(expressionTree.getRight())));
            return String.valueOf(Integer.parseInt(evaluateExpressionTree(expressionTree.getLeft())) + Integer.parseInt(evaluateExpressionTree(expressionTree.getRight())));
        }
        else if (expressionTree.getRoot().getValue().equals("-")) {
            return substractionCalculatorService.operate(new OperandsBody(evaluateExpressionTree(expressionTree.getLeft()), evaluateExpressionTree(expressionTree.getRight())));
        }
        else if (expressionTree.getRoot().getValue().equals("*")) {
            return multiplicationCalculatorService.operate(new OperandsBody(evaluateExpressionTree(expressionTree.getLeft()), evaluateExpressionTree(expressionTree.getRight())));
        }
        else if (expressionTree.getRoot().getValue().equals("/")) {
            return divisionCalculatorService.operate(new OperandsBody(evaluateExpressionTree(expressionTree.getLeft()), evaluateExpressionTree(expressionTree.getRight())));
        }
        else if (expressionTree.getRoot().getValue().equals("^")) {
            return powerCalculatorService.operate(new OperandsBody(evaluateExpressionTree(expressionTree.getLeft()), evaluateExpressionTree(expressionTree.getRight())));
        }
        else if (expressionTree.getRoot().getValue().equals("sqrt")) {
            //squareRootCalculatorService.operate(evaluateExpressionTree(expressionTree.getRight()));
        }
        return expressionTree.getRoot().getValue();
    }
}
