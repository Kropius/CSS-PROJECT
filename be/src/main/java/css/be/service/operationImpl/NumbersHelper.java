package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;

public class NumbersHelper {

    public static void reverseNumbers(OperandsBody operandsBody) {
        operandsBody.setFirstNumber(new StringBuilder(operandsBody.getFirstNumber()).reverse().toString());
        operandsBody.setSecondNumber(new StringBuilder(operandsBody.getSecondNumber()).reverse().toString());

    }

    public static void swapNumbersIfSmaller(OperandsBody numbers) {
        if (numbers.getFirstNumber().length() < numbers.getSecondNumber().length()) {
            String firstNumber = numbers.getFirstNumber();
            numbers.setFirstNumber(numbers.getSecondNumber());
            numbers.setSecondNumber(firstNumber);
        }
        assert numbers.getFirstNumber().length() >= numbers.getSecondNumber().length() : "First number is smaller!";
    }

    public static boolean checkIfSecondIsBigger(OperandsBody numbers) {
        char[] firstNumber = numbers.getFirstNumber().toCharArray();
        char[] secondNumber = numbers.getSecondNumber().toCharArray();
        if (firstNumber.length < secondNumber.length) {
            return true;
        } else if (firstNumber.length == secondNumber.length) {
            int currentDigitIndex = 0;
            while (currentDigitIndex < firstNumber.length) {
                if (secondNumber[currentDigitIndex] > firstNumber[currentDigitIndex]) {
                    return true;
                }
                if (firstNumber[currentDigitIndex] > secondNumber[currentDigitIndex]) {
                    return false;
                }
                currentDigitIndex++;
            }
        }
        return false;
    }
}
