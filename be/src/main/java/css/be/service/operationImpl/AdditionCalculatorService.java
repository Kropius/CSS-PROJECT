package css.be.service.operationImpl;

import css.be.common.NumberValidator;
import css.be.controllers.model.OperandsBody;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class AdditionCalculatorService implements OperationCalculatorService {

    @Override
    public String operate(OperandsBody numbers) {

        NumberValidator.validate(numbers.getFirstNumber());
        NumberValidator.validate(numbers.getSecondNumber());

        NumbersHelper.swapNumbersIfSmaller(numbers);

        assert !NumbersHelper.checkIfSecondIsBigger(numbers) : "First number should be bigger";

        NumbersHelper.reverseNumbers(numbers);

        return performAddition(numbers);
    }

    private String performAddition(OperandsBody numbers) {
        char[] firstNumber = numbers.getFirstNumber().toCharArray();
        char[] secondNumber = numbers.getSecondNumber().toCharArray();
        char[] results = new char[firstNumber.length];
        int currentDigitIndex = 0;
        int carry = 0;

        for (; currentDigitIndex < secondNumber.length; currentDigitIndex++) {

            int currentDigitResult = (firstNumber[currentDigitIndex] - '0') + (secondNumber[currentDigitIndex] - '0') + carry;

            results[currentDigitIndex] = (char) (currentDigitResult % 10 + '0');
            carry = currentDigitResult / 10;
        }

        for (; currentDigitIndex < firstNumber.length; currentDigitIndex++) {
            int currentDigitResult = (firstNumber[currentDigitIndex] - '0' + carry);
            results[currentDigitIndex] = (char) (currentDigitResult % 10 + '0');
            carry = currentDigitResult / 10;
        }


        assert results.length >= secondNumber.length : "result should be bigger then the second number";

        if (carry != 0) {

            return new StringBuilder(new String(results)+(char)(carry+'0')).reverse().toString();
        }

        return new StringBuilder(new String(results)).reverse().toString();

    }


}
