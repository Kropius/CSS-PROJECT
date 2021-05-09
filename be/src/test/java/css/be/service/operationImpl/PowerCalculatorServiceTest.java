package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PowerCalculatorServiceTest {

    private final PowerCalculatorService powerCalculatorService;

    public PowerCalculatorServiceTest() {
        this.powerCalculatorService = new PowerCalculatorService();
    }

    @Test
    public void operate_whenPowerIs0_thenReturn1() {
        OperandsBody givenOperandsBody = new OperandsBody("9991", "0");

        assertThat(powerCalculatorService.operate(givenOperandsBody)).isEqualTo("1");
    }

    @Test
    public void operate_whenNumbersAreEqualTo2_thenReturn4() {
        OperandsBody givenOperandsBody = new OperandsBody("2", "2");

        assertThat(powerCalculatorService.operate(givenOperandsBody)).isEqualTo("4");
    }

    @Test
    public void operate_whenFirstNumberIsBig_thenReturnPower() {
        OperandsBody givenOperandsBody = new OperandsBody("99999999999", "2");

        assertThat(powerCalculatorService.operate(givenOperandsBody)).isEqualTo("9999999999800000000001");
    }

}
