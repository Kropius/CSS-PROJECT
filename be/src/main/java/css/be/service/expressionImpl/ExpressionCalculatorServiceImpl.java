package css.be.service.expressionImpl;

import css.be.common.ExpressionValidator;
import css.be.controllers.model.ExpressionResponse;
import css.be.controllers.model.ExpressionTree;
import css.be.controllers.model.Node;
import css.be.controllers.model.OperandsBody;
import css.be.service.ExpressionCalculatorService;
import css.be.service.operationImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Component
public class ExpressionCalculatorServiceImpl implements ExpressionCalculatorService {

    private AdditionCalculatorService additionCalculatorService;
    private DivisionCalculatorService divisionCalculatorService;
    private MultiplicationCalculatorService multiplicationCalculatorService;
    private SquareRootCalculatorService squareRootCalculatorService;
    private PowerCalculatorService powerCalculatorService;
    private SubstractionCalculatorService substractionCalculatorService;

    @Autowired
    public ExpressionCalculatorServiceImpl (AdditionCalculatorService additionCalculatorService,
                                            DivisionCalculatorService divisionCalculatorService,
                                            MultiplicationCalculatorService multiplicationCalculatorService,
                                            SquareRootCalculatorService squareRootCalculatorService,
                                            PowerCalculatorService powerCalculatorService,
                                            SubstractionCalculatorService substractionCalculatorService) {
        this.additionCalculatorService = additionCalculatorService;
        this.divisionCalculatorService = divisionCalculatorService;
        this.multiplicationCalculatorService = multiplicationCalculatorService;
        this.squareRootCalculatorService = squareRootCalculatorService;
        this.powerCalculatorService = powerCalculatorService;
        this.substractionCalculatorService = substractionCalculatorService;
    }

    @Override
    public String calculate(String expression) {
        List<String> steps = new ArrayList<>();
        List<String> tokens = getListOfTokens(expression);
        ExpressionValidator.validate(tokens);
        String result = evaluateExpressionTree(buildExpressionTree(tokens, createStartingTree().getLeft(), 0), steps);
        ExpressionResponse expressionResponse = new ExpressionResponse(steps, result);
        return expressionResponse.getJsonResponse();
//        return buildExpressionTree(getListOfTokens(expression), expressionTree, 0).toString();
    }

    public ExpressionTree createStartingTree() {
        ExpressionTree expressionTree = new ExpressionTree(new Node(""), null, null, null);
        ExpressionTree leftSubTree = new ExpressionTree(new Node(""), null, null, expressionTree);
        expressionTree.setLeft(leftSubTree);
        return expressionTree;
    }

    @Override
    public List<String> getListOfTokens(String expression) {
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

    public ExpressionTree buildExpressionTree(List<String> tokens, ExpressionTree root, int index) {
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

    public String evaluateExpressionTree(ExpressionTree expressionTree, List<String> steps) {

        String leftResult = "";
        String rightResult = "";
        if (expressionTree.getLeft() != null)
            leftResult = evaluateExpressionTree(expressionTree.getLeft(), steps);
        if (expressionTree.getRight() != null)
            rightResult = evaluateExpressionTree(expressionTree.getRight(), steps);

        if (expressionTree.getRoot().getValue().equals("+")) {
            String thisResult = additionCalculatorService.operate(new OperandsBody(leftResult, rightResult));
            steps.add(leftResult + " + " + rightResult + " = " + thisResult);
            return thisResult;
        }
        else if (expressionTree.getRoot().getValue().equals("-")) {
            String thisResult = substractionCalculatorService.operate(new OperandsBody(leftResult, rightResult));
            steps.add(leftResult + " - " + rightResult + " = " + thisResult);
            return thisResult;
        }
        else if (expressionTree.getRoot().getValue().equals("*")) {
            String thisResult = multiplicationCalculatorService.operate(new OperandsBody(leftResult, rightResult));
            steps.add(leftResult + " * " + rightResult + " = " + thisResult);
            return thisResult;
        }
        else if (expressionTree.getRoot().getValue().equals("/")) {
            String thisResult = divisionCalculatorService.operate(new OperandsBody(leftResult, rightResult));
            steps.add(leftResult + " / " + rightResult + " = " + thisResult);
            return thisResult;
        }
        else if (expressionTree.getRoot().getValue().equals("^")) {
            String thisResult = powerCalculatorService.operate(new OperandsBody(leftResult, rightResult));
            steps.add(leftResult + " ^ " + rightResult + " = " + thisResult);
            return thisResult;
        }
        else if (expressionTree.getRoot().getValue().equals("sqrt")) {
            String thisResult = squareRootCalculatorService.operate(rightResult);
            steps.add("sqrt " + rightResult + " = " + thisResult);
            return thisResult;
        }
        return expressionTree.getRoot().getValue();
    }
}
