package css.be.service.operationImpl;

import css.be.controllers.model.OperandsBody;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareRootCalculatorServiceTest {

    private final SquareRootCalculatorService squareRootCalculatorService;

    public SquareRootCalculatorServiceTest() {
        this.squareRootCalculatorService = new SquareRootCalculatorService();
    }

    @Test
    public void operate_whenNumberIsEqualsTo1_thenReturn1() {
        assertThat(squareRootCalculatorService.operate("1")).isEqualTo("1");
    }

    @Test
    public void operate_whenNumberIsEqualsTo4_thenReturn2() {
        assertThat(squareRootCalculatorService.operate("4")).isEqualTo("2");
    }

    @Test
    public void operate_whenNumberIsABigPerfectSquare_thenReturnSquareRoot() {
        assertThat(squareRootCalculatorService.operate("100000000")).isEqualTo("10000");
    }

    @Test
    public void operate_whenNumberIsABigButNotPerfectSquare_thenReturnApproximateSquareRoot() {
        assertThat(squareRootCalculatorService.operate("10985457")).isEqualTo("3314");
    }


}
