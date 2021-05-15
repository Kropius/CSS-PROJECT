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
        assert expression!=null && !expression.equals("") : "Expression can't be empty!";

        List<String> steps = new ArrayList<>();
        List<String> tokens = getListOfTokens(expression);

        assert !tokens.isEmpty() : "Can't operate with empty list of tokens!";

        ExpressionValidator.validate(tokens);
        String result = evaluateExpressionTree(buildExpressionTree(tokens, createStartingTree().getLeft(), 0), steps);

        assert !steps.isEmpty() : "No operation was executed in this expression!";

        ExpressionResponse expressionResponse = new ExpressionResponse(steps, result);
        return expressionResponse.getJsonResponse();
    }

    public ExpressionTree createStartingTree() {
        ExpressionTree expressionTree = new ExpressionTree(new Node(""), null, null, null);
        ExpressionTree leftSubTree = new ExpressionTree(new Node(""), null, null, expressionTree);
        expressionTree.setLeft(leftSubTree);

        assert expressionTree.getLeft() != null: "Left child of expression tree is not set correctly!";
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

        String assertionRegex = "[^+\\-*/^sqrt()0123456789]+";
        for (String assertionToken: tokens)
            assert !assertionToken.matches(assertionRegex) : "Parser found incorrect characters!";

        return tokens;
    }

    public ExpressionTree buildExpressionTree(List<String> tokens, ExpressionTree root, int index) {

        assert index <= tokens.size() : "Algorithm should stop when index surpasses the number of elements in the tokens list!";

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

            if (token.equals("sqrt"))
                assert root.getLeft() == null : "SQRT node should have only a right child!";

            return buildExpressionTree(tokens, root.getRight(), index + 1);
        }
        else {
            assert token.matches("[0123456789]+") : "The 'else' branch from the expression tree building should process only numbers at this point!";

            root.getRoot().setValue(token);
            return buildExpressionTree(tokens, root.getParent(), index + 1);
        }
    }

    public String evaluateExpressionTree(ExpressionTree expressionTree, List<String> steps) {

        assert expressionTree.getRoot() != null : "Can't calculate expression using a tree with NULL root!";

        String leftResult = "";
        String rightResult = "";
        if (expressionTree.getLeft() != null) {
            leftResult = evaluateExpressionTree(expressionTree.getLeft(), steps);

            assert !leftResult.isEmpty() : "Operation result should not be empty!";
            assert leftResult.matches("[0123456789]+") : "Result should contain only numbers!";
        }
        if (expressionTree.getRight() != null) {
            rightResult = evaluateExpressionTree(expressionTree.getRight(), steps);

            assert !rightResult.isEmpty() : "Operation result should not be empty!";
            assert rightResult.matches("[0123456789]+") : "Result should contain only numbers!";
        }

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
