package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;

public class NumbersHelper {

    public static void reverseNumbers(OperandsBody operandsBody) {
        operandsBody.setFirstNumber(new StringBuilder(operandsBody.getFirstNumber()).reverse().toString());
        operandsBody.setSecondNumber(new StringBuilder(operandsBody.getSecondNumber()).reverse().toString());

    }

}
