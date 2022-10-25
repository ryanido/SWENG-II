package yup.sweng.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static yup.sweng.calculator.CalculatorApplication.welcome;

@SpringBootTest
class CalculatorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testWelcome() {
        assert welcome().equals("Welcome to not calcuator");
    }

}
