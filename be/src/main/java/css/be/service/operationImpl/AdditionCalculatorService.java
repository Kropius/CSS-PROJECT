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

        swapNumbersIfSmaller(numbers);

        reverseNumbers(numbers);

        return performAddition(numbers);
    }

    private void swapNumbersIfSmaller(OperandsBody numbers) {
        if (numbers.getFirstNumber().length() < numbers.getSecondNumber().length()) {
            String firstNumber = numbers.getFirstNumber();
            numbers.setFirstNumber(numbers.getSecondNumber());
            numbers.setSecondNumber(firstNumber);
        }
    }

    private void reverseNumbers(OperandsBody operandsBody) {
        operandsBody.setFirstNumber(new StringBuilder(operandsBody.getFirstNumber()).reverse().toString());
        operandsBody.setSecondNumber(new StringBuilder(operandsBody.getSecondNumber()).reverse().toString());

    }

    private String performAddition(OperandsBody numbers) {
        char[] firstNumber = numbers.getFirstNumber().toCharArray();
        char[] secondNumber = numbers.getSecondNumber().toCharArray();
        char[] results = new char[firstNumber.length + 1];
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
        if (carry != 0) {
            results[currentDigitIndex] = (char) (carry + '0');
        }

        return new StringBuilder(new String(results)).reverse().toString();

    }


}
