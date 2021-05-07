package css.be.controllers;

import css.be.controllers.model.OperandsBody;
import css.be.service.operationImpl.*;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootTest
public class OperationCalculatorControllerIT {

    private RestTemplate restTemplate;

    public OperationCalculatorControllerIT() {

        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080/operation"));
    }

    @Test
    public void test() {
        OperandsBody operandsBody = new OperandsBody("1", "2");
        ResponseEntity<String> responseEntity = restTemplate.exchange("/addition", HttpMethod.POST, new HttpEntity<>(operandsBody), String.class);
        System.out.println(responseEntity.getBody());//the body

    }

    private Object getDefaultAnswer(InvocationOnMock invocationOnMock) {
        throw new RuntimeException(String.format("Mock exception on %s, method: %s", invocationOnMock.getMock().getClass().getName(), invocationOnMock.getMethod().getName()));
    }
}
