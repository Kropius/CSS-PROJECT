package css.be.common;

import css.be.exceptions.InvalidExpressionException;
import org.junit.Test;

import java.util.Arrays;

public class ExpressionValidatorTest {

    @Test (expected = InvalidExpressionException.class)
    public void checkExpressionInvalidWhenOpenParenthesesNotEqualToClosedParentheses() throws InvalidExpressionException{
        ExpressionValidator.validate(Arrays.asList("(", "(", "5", "+", "2", ")", "-", "1"));
    }

    @Test (expected = InvalidExpressionException.class)
    public void checkExpressionInvalidWhenClosedParenthesesBeforeOpenParentheses() throws InvalidExpressionException{
        ExpressionValidator.validate(Arrays.asList(")", "5", "+", "2", "("));
    }

    @Test (expected = InvalidExpressionException.class)
    public void checkExpressionInvalidWhenMainOperationInParentheses() throws InvalidExpressionException{
        ExpressionValidator.validate(Arrays.asList("(", "(", "5", "+", "2", ")", "/", "3", ")"));
    }

    @Test (expected = InvalidExpressionException.class)
    public void checkExpressionInvalidWhenOperationOrderNotDefinedWithParentheses() throws InvalidExpressionException{
        ExpressionValidator.validate(Arrays.asList("5", "+", "2", "/", "3"));
    }

    @Test
    public void checkExpressionValidWhenInputIsCorrect() {
        ExpressionValidator.validate(Arrays.asList("(", "5", "+", "2", ")", "/", "(", "3", "^", "5", ")"));
    }
}
