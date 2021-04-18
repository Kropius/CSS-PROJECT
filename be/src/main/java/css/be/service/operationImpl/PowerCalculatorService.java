package css.be.service.operationImpl;

import css.be.common.NumberValidator;
import css.be.controllers.model.OperandsBody;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class PowerCalculatorService implements OperationCalculatorService {
    @Override
    public String operate(OperandsBody numbers) {
        NumberValidator.validate(numbers.getFirstNumber());
        NumberValidator.validate(numbers.getSecondNumber());

        return performPower(numbers);
    }

    private String performPower(OperandsBody numbers) {
        MultiplicationCalculatorService multiplicationCalculatorService = new MultiplicationCalculatorService();
        DivisionCalculatorService divisionCalculatorService = new DivisionCalculatorService();
        String result = "1";
        String firstNumber = numbers.getFirstNumber();
        String secondNumber = numbers.getSecondNumber();

        OperandsBody mulNumbers = new OperandsBody(result, firstNumber);
        OperandsBody divNumbers = new OperandsBody(secondNumber, "2");

        while (!secondNumber.equals("0")) {

            int secondLastDigit = secondNumber.charAt(secondNumber.length() - 1) - '0';
            if (secondLastDigit % 2 == 1) {
                mulNumbers.setFirstNumber(result);
                mulNumbers.setSecondNumber(firstNumber);
                result = multiplicationCalculatorService.operate(mulNumbers);
            }

            secondNumber = divisionCalculatorService.operate(divNumbers);
            divNumbers.setFirstNumber(secondNumber);

            System.out.println(secondNumber);

            mulNumbers.setFirstNumber(firstNumber);
            mulNumbers.setSecondNumber(firstNumber);
            firstNumber = multiplicationCalculatorService.operate(mulNumbers);


        }

        return result;
    }

}
