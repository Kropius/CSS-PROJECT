package css.be.service;

import java.util.List;

public interface ExpressionCalculatorService {
    String calculate(String expression);
    List<String> getListOfTokens(String expression);
}
