package css.be.service.expressionImpl;

import css.be.controllers.model.ExpressionTree;
import css.be.controllers.model.Node;
import css.be.service.ExpressionCalculatorService;
import org.springframework.stereotype.Component;

@Component
public class XmlExpressionCalculatorServiceImpl implements ExpressionCalculatorService {

    @Override
    public String calculate(String expression) {
        return "Nothing";
    }
}
