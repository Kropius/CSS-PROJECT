package css.be.controllers;

import css.be.service.expressionImpl.ExpressionCalculatorServiceImpl;
import css.be.service.expressionImpl.XmlExpressionCalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "xmlcompound")
public class XmlExpressionCalculatorController {

    private XmlExpressionCalculatorServiceImpl xmlCompoundOperationService;

    @Autowired
    public XmlExpressionCalculatorController(XmlExpressionCalculatorServiceImpl xmlCompoundOperationService) {
        this.xmlCompoundOperationService = xmlCompoundOperationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String calculateExpression(@NotEmpty @RequestBody String expression) {
        return xmlCompoundOperationService.calculate(expression);
    }

}
