package css.be.controllers;

import css.be.controllers.model.OperandsBody;
import css.be.service.expressionImpl.ExpressionCalculatorServiceImpl;
import css.be.service.operationImpl.AdditionCalculatorService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ExpressionCalculatorControllerIT {

    private static final String URL = "http://localhost:8080";

    private static RestTemplate restTemplate;

    private ExpressionCalculatorServiceImpl expressionCalculatorService;

    @BeforeEach
    public void init() {
        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(URL));
        expressionCalculatorService = Mockito.mock(ExpressionCalculatorServiceImpl.class);
    }

    @Test
    public void postExpressionShouldReturnResult() {

        Mockito.when(expressionCalculatorService.calculate("5+2")).thenReturn("7");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        ResponseEntity<String> responseEntity = restTemplate.exchange("/compound", HttpMethod.POST, new HttpEntity<>("5+2", headers), String.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Assert.assertEquals(responseEntity.getBody(), "7");
    }
}
