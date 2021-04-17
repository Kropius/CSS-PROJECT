package css.be.service.operationImpl;

import org.springframework.stereotype.Component;

@Component
public class SquareRootCalculatorService{

    public String operate(String number) {
        return String.format("Executing square on %s", number);
    }

}
