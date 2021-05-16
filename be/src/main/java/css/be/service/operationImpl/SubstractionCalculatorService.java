package css.be.service.operationImpl;

import css.be.common.NumberValidator;
import css.be.controllers.model.OperandsBody;
import css.be.exceptions.InvalidNumberException;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SubstractionCalculatorService implements OperationCalculatorService {

    @Override
    public String operate(OperandsBody numbers) {
        NumberValidator.validate(numbers.getFirstNumber());
        NumberValidator.validate(numbers.getSecondNumber());

        if (NumbersHelper.checkIfSecondIsBigger(numbers)) {
            throw new InvalidNumberException("Negative Result");
        }

        NumbersHelper.reverseNumbers(numbers);

        return performSubtraction(numbers);
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
        while (results[correctionLength] == '0' && correctionLength > 0) {
            correctionLength --;
        }

        String result= new StringBuilder(new String(results)).substring(0, correctionLength + 1);

        result =  new StringBuilder(result).reverse().toString();
        assert NumbersHelper.checkIfSecondIsBigger(new OperandsBody(Arrays.toString(firstNumber), result));
        return result;
    }

}