package css.be.service.impl;

import css.be.controllers.model.OperandsBody;
import css.be.service.OperationService;
import org.springframework.stereotype.Component;

@Component
public class MultiplicationService implements OperationService {
    @Override
    public String operate(OperandsBody numbers) {
        return String.format("Executing multiplication on %s and %s", numbers.getFirstNumber(), numbers.getSecondNumber());

    }
}
