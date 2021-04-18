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
    }

    public static boolean checkIfSecondIsBigger(OperandsBody numbers) {
        char[] firstNumber = numbers.getFirstNumber().toCharArray();
        char[] secondNumber = numbers.getSecondNumber().toCharArray();
        if (firstNumber.length < secondNumber.length) {
            return true;
        } else if (firstNumber.length == secondNumber.length) {
            int currentDigitIndex = 0;
            while (currentDigitIndex < firstNumber.length && firstNumber[currentDigitIndex] <= secondNumber[currentDigitIndex])
                currentDigitIndex ++;
            if (currentDigitIndex == firstNumber.length && firstNumber[currentDigitIndex - 1] < secondNumber[currentDigitIndex - 1]) {
                return true;
            }
        }
        return false;
    }

}
