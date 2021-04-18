package css.be.service.operationImpl;

import css.be.common.NumberValidator;
import css.be.controllers.model.OperandsBody;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class MultiplicationCalculatorService implements OperationCalculatorService {
    @Override
    public String operate(OperandsBody numbers) {

        System.out.println("Multiplication");
        NumberValidator.validate(numbers.getFirstNumber());
        NumberValidator.validate(numbers.getSecondNumber());

        NumbersHelper.swapNumbersIfSmaller(numbers);

        NumbersHelper.reverseNumbers(numbers);

        return performMultiplication(numbers);
    }

    private String performMultiplication(OperandsBody numbers) {
        AdditionCalculatorService additionCalculatorService = new AdditionCalculatorService();
        char[] firstNumber = numbers.getFirstNumber().toCharArray();
        char[] secondNumber = numbers.getSecondNumber().toCharArray();
        String result = "0";
        int carry;
        for (int currentDigitIndexSecond = 0; currentDigitIndexSecond < secondNumber.length; currentDigitIndexSecond++) {
            carry = 0;
            if ((secondNumber[currentDigitIndexSecond] - '0') == 0) {
                continue;
            }

            StringBuilder numberToAdd = new StringBuilder();
            for (int currentDigitIndexFirst = 0; currentDigitIndexFirst < firstNumber.length; currentDigitIndexFirst++) {
                int currentMulNumber = (secondNumber[currentDigitIndexSecond] - '0') * (firstNumber[currentDigitIndexFirst] - '0') + carry;
                numberToAdd.append(currentMulNumber % 10);
                carry = currentMulNumber / 10;

            }
            if (carry != 0) {
                numberToAdd.append(carry);
            }

            numberToAdd = numberToAdd.reverse();
            for (int i = 0; i < currentDigitIndexSecond; i++) {
                numberToAdd.append(0);
            }

            if (result.equals("0")) {
                result = numberToAdd.toString();
            } else {
                OperandsBody operandsBody = new OperandsBody(result, numberToAdd.toString());
                result = additionCalculatorService.operate(operandsBody);
            }
        }

        return result;
    }


}
