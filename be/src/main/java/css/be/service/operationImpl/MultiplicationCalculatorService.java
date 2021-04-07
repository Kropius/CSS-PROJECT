package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import css.be.service.OperationCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class MultiplicationCalculatorService implements OperationCalculatorService {
    @Override
    public String operate(OperandsBody numbers) {
        return String.format("Executing multiplication on %s and %s", numbers.getFirstNumber(), numbers.getSecondNumber());

    }
}
