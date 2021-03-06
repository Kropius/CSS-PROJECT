package css.be.controllers.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class OperandsBody {

    @NotEmpty(message = "First number of an operation is always mandatory")
    private String firstNumber;

    @NotEmpty(message = "Second number of an operation is always mandatory")
    private String secondNumber;

    public OperandsBody(@NotEmpty(message = "First number of an operation is always mandatory") String firstNumber, @NotEmpty(message = "Second number of an operation is always mandatory") String secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public OperandsBody() {
    }
}
