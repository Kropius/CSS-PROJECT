package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import css.be.exceptions.SquareRootFormatException;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SquareRootCalculatorService implements OperationCalculatorService {

    @Override
    public String operate(OperandsBody numbers) {
        if(!StringUtils.isEmpty(numbers.getSecondNumber()) || numbers.getFirstNumber() == null){
            throw new SquareRootFormatException("Square root format not respected! First number should not be null! There should be no second number!");
        }

        return String.format("Executing square on %s", numbers.getFirstNumber());
    }

}
