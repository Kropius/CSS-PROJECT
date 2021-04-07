package css.be.controllers;

import css.be.controllers.model.OperandsBody;
import css.be.service.impl.AdditionService;
import css.be.service.impl.DivisionService;
import css.be.service.impl.MultiplicationService;
import css.be.service.impl.SubstractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "operation")
public class OperationController {

    private AdditionService additionService;
    private SubstractionService substractionService;
    private MultiplicationService multiplicationService;
    private DivisionService divisionService;

    @Autowired
    public OperationController(AdditionService additionService, SubstractionService substractionService, MultiplicationService multiplicationService, DivisionService divisionService) {
        this.additionService = additionService;
        this.substractionService = substractionService;
        this.multiplicationService = multiplicationService;
        this.divisionService = divisionService;
    }

    @GetMapping(value = "addition")
    @ResponseStatus(HttpStatus.OK)
    public String addNumbers(@RequestBody OperandsBody numbers) {
        return additionService.operate(numbers);
    }

    @GetMapping(value = "substraction")
    @ResponseStatus(HttpStatus.OK)
    public String subtractNumbers(@RequestBody OperandsBody numbers) {
        return substractionService.operate(numbers);
    }

    @GetMapping(value = "division")
    @ResponseStatus(HttpStatus.OK)
    public String divideNumbers(@RequestBody OperandsBody numbers) {
        return divisionService.operate(numbers);
    }

    @GetMapping(value = "multiplication")
    @ResponseStatus(HttpStatus.OK)
    public String multiplyNumbers(@RequestBody OperandsBody numbers) {
        return multiplicationService.operate(numbers);
    }
}
