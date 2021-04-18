package css.be.common;

import css.be.exceptions.InvalidExpressionException;

import java.util.List;

public class ExpressionValidator {
    private ExpressionValidator(){

    }

    public static void validate(List<String> tokens){
        validateEqualNumberOfParantheses(tokens);
        validateNoParanthesesAtStartAndEnd(tokens);
        validateNumberOfOperationsBetweenParantheses(tokens);
    }

    private static void validateEqualNumberOfParantheses(List<String> tokens){
        int nrOpenParantheses = 0;
        for (String token: tokens) {
            if (token.equals("("))
                nrOpenParantheses++;
            else if (token.equals(")"))
                nrOpenParantheses--;
            if (nrOpenParantheses < 0)
                throw new InvalidExpressionException("Parantheses closed before opening!");
        }
        if (nrOpenParantheses != 0)
            throw new InvalidExpressionException("The number of open parantheses is not equal to the number of closed parantheses!");
    }

    private static boolean checkOperatorOutsideParantheses(List<String> tokens) {
        int nrOpenParantheses = 0;
        for (String token: tokens) {
            if (token.equals("("))
                nrOpenParantheses++;
            else if (token.equals(")"))
                nrOpenParantheses--;
            if (nrOpenParantheses == 0 && "+-*/^sqrt".contains(token))
                return true;
        }
        return false;
    }

    private static void validateNoParanthesesAtStartAndEnd(List<String> tokens) {
        if (tokens.get(0).equals("(") && tokens.get(tokens.size() - 1).equals(")") && !checkOperatorOutsideParantheses(tokens))
            throw new InvalidExpressionException("The expression in its entirety must NOT be surrounded by parantheses!");
    }

    private static void validateNumberOfOperationsBetweenParantheses(List<String> tokens){
        int nrOperatorsBetweenParantheses = 0;
        for (String token: tokens) {

            if ("+-*/^sqrt".contains(token))
                nrOperatorsBetweenParantheses++;

            else if (token.equals(")"))
                nrOperatorsBetweenParantheses--;

        }
        if (nrOperatorsBetweenParantheses != 1)
            throw new InvalidExpressionException("Please place parantheses to define the desired operation order!");
    }
}
