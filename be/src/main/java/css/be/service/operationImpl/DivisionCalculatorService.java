package css.be.service.operationImpl;

import css.be.common.NumberValidator;
import css.be.controllers.model.OperandsBody;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class DivisionCalculatorService implements OperationCalculatorService {

    @Override
    public String operate(OperandsBody numbers) {
        NumberValidator.validate(numbers.getFirstNumber());
        NumberValidator.validate(numbers.getSecondNumber());
        NumberValidator.validateDivisionBy0(numbers.getSecondNumber());
        try {
            Integer.parseInt(numbers.getSecondNumber());
            return performDivisionForSmallDivisor(numbers);
        } catch (NumberFormatException e) {
            return performDivisionForBigDivisor(numbers);
        }
    }

    private String performDivisionForBigDivisor(OperandsBody numbers) {
        SubstractionCalculatorService substractionCalculatorService = new SubstractionCalculatorService();
        AdditionCalculatorService additionCalculatorService = new AdditionCalculatorService();
        String result = "0";
        String firstNumber = "";
        String secondNumber = numbers.getSecondNumber();
        while (!NumbersHelper.checkIfSecondIsBigger(numbers)) {
            firstNumber = substractionCalculatorService.operate(numbers);
            numbers.setFirstNumber(firstNumber);
            numbers.setSecondNumber(secondNumber);

            OperandsBody additionNumbers = new OperandsBody(result, "1");
            result = additionCalculatorService.operate(additionNumbers);
        }
        return result;
    }

    private String performDivisionForSmallDivisor(OperandsBody numbers) {

        StringBuilder result = new StringBuilder();

        char[] firsNumber = numbers.getFirstNumber().toCharArray();
        int secondNumber = Integer.parseInt(numbers.getSecondNumber());
        int carry = 0;

        for (int currentDigitIndex = 0; currentDigitIndex < firsNumber.length; currentDigitIndex++) {

            int currentDivisionDigit = carry * 10 + (firsNumber[currentDigitIndex] - '0');

            result.append(currentDivisionDigit / secondNumber);

            carry = currentDivisionDigit % secondNumber;
        }

        for (int currentDigitIndex = 0; currentDigitIndex < result.length(); currentDigitIndex++) {
            if (result.charAt(currentDigitIndex) != '0') {
                result.delete(0, currentDigitIndex);
                break;
            }
        }

        return result.toString();
    }
}
