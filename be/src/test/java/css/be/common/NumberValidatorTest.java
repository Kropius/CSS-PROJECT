package css.be.common;

import css.be.exceptions.InvalidNumberException;
import org.junit.Test;

public class NumberValidatorTest {

    @Test(expected = InvalidNumberException.class)
    public void validate_WhenNumberIsMissing(){
        NumberValidator.validate("");
    }

    @Test(expected = InvalidNumberException.class)
    public void validate_WhenFirstDigitIs0(){
        NumberValidator.validate("0232");
    }


    @Test(expected = InvalidNumberException.class)
    public void validate_WhenNumberContainsAlphaCharacters(){
        NumberValidator.validate("2a32");
    }

    @Test
    public void validate_shouldNotThrowException(){
        NumberValidator.validate("32");
    }


    @Test(expected = InvalidNumberException.class)
    public void validateDivisionBy0_shouldThrowException(){
        NumberValidator.validateDivisionBy0("0");
    }

    @Test()
    public void validateDivisionBy0_shouldNotThrowException(){
        NumberValidator.validateDivisionBy0("1");
    }
}
