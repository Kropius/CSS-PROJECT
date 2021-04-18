package css.be.service.operationImpl;

import css.be.common.NumberValidator;
import css.be.controllers.model.OperandsBody;
import org.springframework.stereotype.Component;

@Component
public class SquareRootCalculatorService{

    public String operate(String number) {
        NumberValidator.validate(number);

        return performSQRT(number);
    }

    private  String performSQRT (String number){
     AdditionCalculatorService additionCalculatorService = new AdditionCalculatorService();
     SubstractionCalculatorService substractionCalculatorService = new SubstractionCalculatorService();
     DivisionCalculatorService divisionCalculatorService = new DivisionCalculatorService();
     MultiplicationCalculatorService multiplicationCalculatorService = new MultiplicationCalculatorService();
     String start = "1";
     String end = number;
     String result = "0";

     if (number.equals("0") || number.equals("1")) {
         return number;
     }

     while (NumbersHelper.checkIfSecondIsBigger(new OperandsBody(start, end)) || start.equals(end)) {
         String mid = additionCalculatorService.operate(new OperandsBody(start, end));
         mid = divisionCalculatorService.operate(new OperandsBody(mid, "2"));

         String doubleMid = multiplicationCalculatorService.operate(new OperandsBody(mid, mid));

         if (doubleMid.equals(number)) {
             return mid;
         }

         if (NumbersHelper.checkIfSecondIsBigger(new OperandsBody(doubleMid, number))) {
             start = additionCalculatorService.operate(new OperandsBody(mid, "1"));
             result = mid;
         }
         else {
             end = substractionCalculatorService.operate(new OperandsBody(mid, "1"));
         }
     }
    return  result;
    }
}
