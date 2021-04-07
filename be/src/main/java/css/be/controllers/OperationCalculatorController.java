package css.be.controllers;

import css.be.controllers.model.OperandsBody;
import css.be.service.operationImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "operation")
public class OperationCalculatorController {

    private AdditionCalculatorService additionService;
    private SubstractionCalculatorService substractionService;
    private MultiplicationCalculatorService multiplicationService;
    private DivisionCalculatorService divisionService;
    private SquareRootCalculatorService squareRootService;
    private PowerCalculatorService powerService;

    @Autowired
    public OperationCalculatorController(AdditionCalculatorService additionService,
                                         SubstractionCalculatorService substractionService,
                                         MultiplicationCalculatorService multiplicationService,
                                         DivisionCalculatorService divisionService,
                                         SquareRootCalculatorService squareRootService,
                                         PowerCalculatorService powerService) {
        this.additionService = additionService;
        this.substractionService = substractionService;
        this.multiplicationService = multiplicationService;
        this.divisionService = divisionService;
        this.squareRootService = squareRootService;
        this.powerService = powerService;
    }


    @GetMapping(value = "addition")
    @ResponseStatus(HttpStatus.OK)
    public String addNumbers(@Valid @RequestBody OperandsBody numbers) {
        return additionService.operate(numbers);
    }

    @GetMapping(value = "substraction")
    @ResponseStatus(HttpStatus.OK)
    public String subtractNumbers(@Valid @RequestBody OperandsBody numbers) {
        return substractionService.operate(numbers);
    }

    @GetMapping(value = "division")
    @ResponseStatus(HttpStatus.OK)
    public String divideNumbers(@Valid @RequestBody OperandsBody numbers) {
        return divisionService.operate(numbers);
    }

    @GetMapping(value = "multiplication")
    @ResponseStatus(HttpStatus.OK)
    public String multiplyNumbers(@Valid @RequestBody OperandsBody numbers) {
        return multiplicationService.operate(numbers);
    }

    @GetMapping(value = "power")
    @ResponseStatus(HttpStatus.OK)
    public String powerNumbers(@Valid @RequestBody OperandsBody numbers) {
        return powerService.operate(numbers);
    }

    @GetMapping(value = "square")
    @ResponseStatus(HttpStatus.OK)
    public String squareNumber(@RequestBody OperandsBody numbers) {
        return squareRootService.operate(numbers);
    }
}
