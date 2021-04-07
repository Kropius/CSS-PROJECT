package css.be.service.expressionImpl;

import css.be.service.ExpressionCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class ExpressionCalculatorServiceImpl implements ExpressionCalculatorService {

    @Override
    public String calculate(String expression) {
        return String.format("Calculating the following expression %s", expression);
    }
}
