package css.be.controllers;

import css.be.service.expressionImpl.ExpressionCalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "compound")
public class ExpressionCalculatorController {

    private ExpressionCalculatorServiceImpl compoundOperationService;

    @Autowired
    public ExpressionCalculatorController(ExpressionCalculatorServiceImpl compoundOperationService) {
        this.compoundOperationService = compoundOperationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String addNumbers(@NotEmpty @RequestBody String expression) {
        return compoundOperationService.calculate(expression);
    }
}
