package yup.sweng.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static yup.sweng.calculator.CalculatorApplication.*;

@SpringBootTest
class CalculatorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testWelcome() {
        assert welcome().equals("Welcome to calculator **release two");
    }

    @Test
    void testErrorChecking() {
        String notNumberOrOperator = "ERROR: Please only enter numbers and operators. ('+', '-', '*', '/', '^')\n";
        String doubleOperator = "ERROR: Do not duplicate operation character.\n";
        String firstOperator = "ERROR: First item can't be operator unless starting with minus number.\n";
        String lastOperator = "ERROR: Last item can't be operator.\n";
        String notFullProblem = "ERROR: Please enter a full problem.\n";
        String oddNumberOfBrackets = "ERROR: Please enter as many left brackets as right brackets.\n";
        String expProblem = "ERROR: Use 'exp' followed by a number or bracket please for exponent.\n";
        String lnProblem = "ERROR: Use 'ln' followed by a number or bracket please for natural log.\n";
        String invalidString = "jksndisnd*ksdn";
        //Test if correct error is returned for string that doesn't only have numbers and operators
        assert errorChecking(invalidString).equals(notNumberOrOperator);
        invalidString ="2++2";
        //Test if correct error is returned for string that has a double operator
        assert errorChecking(invalidString).equals(doubleOperator);
        invalidString ="*8+9";
        //Test if correct error is returned for string that starts with an operator
        assert errorChecking(invalidString).equals(firstOperator);
        invalidString = "8/";
        //Test if correct error is returned for string that ends with an operator
        assert errorChecking(invalidString).equals(lastOperator);
        //Test if correct error is returned for string that is not a full problem
        invalidString = "+";
        assert errorChecking(invalidString).equals(firstOperator+ lastOperator +  notFullProblem);
        invalidString = "8(9+9";
        //Test if correct error is returned for string that has an odd number of brackets
        assert errorChecking(invalidString).equals(oddNumberOfBrackets);
        invalidString = "exp+0";
        //Test if correct error is returned for string that uses exp wrong

        assert errorChecking(invalidString).equals(expProblem);
        invalidString = "ln+9";
        //Test if correct error is returned for string that ln wrong
        assert errorChecking(invalidString).equals(lnProblem);

    }

    @Test
    void testEvaluator(){
        Evaluator tmpEvaluator = new Evaluator();
        //Test if correct answer for basic evaluation(+ , -, *, /, ^)
        assert tmpEvaluator.compute("9 * (9^8 + 10/2) - 3") == 387420531;
        //Test if correct answer for advanced evaluation(exp,ln)
        assert tmpEvaluator.compute("3 + ln(exp(2))") == 5;
    }


}
