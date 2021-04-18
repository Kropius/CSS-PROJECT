package css.be.service.operationImpl;

import css.be.common.NumberValidator;
import css.be.controllers.model.OperandsBody;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class SubstractionCalculatorService implements OperationCalculatorService {

    @Override
    public String operate(OperandsBody numbers) {
        NumberValidator.validate(numbers.getFirstNumber());
        NumberValidator.validate(numbers.getSecondNumber());

        checkIfSecondIsBigger(numbers);

        NumbersHelper.reverseNumbers(numbers);

        return performSubtraction(numbers);
    }

    private void checkIfSecondIsBigger(OperandsBody numbers) {
        char[] firstNumber = numbers.getFirstNumber().toCharArray();
        char[] secondNumber = numbers.getSecondNumber().toCharArray();
        if (firstNumber.length < secondNumber.length) {
            throw new IllegalArgumentException("Negative result");
        } else if (firstNumber.length == secondNumber.length) {
            int currentDigitIndex = 0;
            while (currentDigitIndex < firstNumber.length && firstNumber[currentDigitIndex] <= secondNumber[currentDigitIndex])
                currentDigitIndex ++;
            if (currentDigitIndex == firstNumber.length && firstNumber[currentDigitIndex - 1] < secondNumber[currentDigitIndex - 1]) {
                throw new IllegalArgumentException("Negative result");
            }
        }
    }

    private String performSubtraction(OperandsBody numbers) {
        char[] firstNumber = numbers.getFirstNumber().toCharArray();
        char[] secondNumber = numbers.getSecondNumber().toCharArray();
        char[] results = new char[firstNumber.length];
        int currentDigitIndex = 0;
        int carry = 0;

        int firstNumberInt;
        int secondNumberInt;
        for (; currentDigitIndex < secondNumber.length; currentDigitIndex++) {
            firstNumberInt = firstNumber[currentDigitIndex] - '0';
            secondNumberInt = secondNumber[currentDigitIndex] - '0';

            if (carry > 0 && firstNumberInt == 0) {
                firstNumberInt = 9;
            } else {
                firstNumberInt -= carry;
                carry = 0;
            }

            if (firstNumberInt < secondNumberInt) {
                carry = 1;
                firstNumberInt += 10;
            }

            results[currentDigitIndex] = (char) ((firstNumberInt - secondNumberInt) + '0');

        }

        for (; currentDigitIndex < firstNumber.length; currentDigitIndex++) {
            firstNumberInt = firstNumber[currentDigitIndex] - '0';
            if (carry > 0 && firstNumberInt == 0) {
                firstNumberInt = 9;
            } else {
                firstNumberInt -= carry;
                carry = 0;
            }

            results[currentDigitIndex] = (char) (firstNumberInt + '0');
        }
        int correctionLength = results.length - 1;
        while (results[correctionLength] == '0') {
            correctionLength --;
        }

        String result= new StringBuilder(new String(results)).toString().substring(0, correctionLength + 1);
        return new StringBuilder(result).reverse().toString();
    }

}