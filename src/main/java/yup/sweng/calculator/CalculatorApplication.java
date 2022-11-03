package yup.sweng.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CalculatorApplication {

    @GetMapping("/welcome")
    public static String welcome() {
        return "Welcome to calculator";
    }
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);

        // shown below is how to call the errorchecking and evaluator.compute()
        String testSum = "1+2*exp(4)";
        String checkAnswer = errorChecking(testSum);
        if (checkAnswer == "Valid" )
        {
            Evaluator evaluator = new Evaluator();
            double number = evaluator.compute(testSum);
            // this will need to be in the UI
            System.out.println("Result is: " + (Math.round(number * 1000))/1000.0 + "\n"); }

    }


    // function to check the string to see if it is valid - returns "Valid" if ok, or the error message if not
    public static String errorChecking(String input) {

        boolean checked = false;
        String currentInput = input.replaceAll(" ", "");;
        boolean stringIsValid = true;
        boolean notNumberOrOperator = false;
        boolean doubleOperator = false;
        boolean notFullProblem = false;
        boolean lastItemInStringIsOperator = false;
        boolean firstItemInStringIsOperator = false;
        boolean oddNumberOfBrackets = false;
        boolean expProblem = false;
        boolean lnProblem = false;
        int countOfLB = 0;
        int countOfRB = 0;
        String returnMessage = "";

        // Putting input string into char array
        char[] ch = new char[currentInput.length()];
        for (int i = 0; i < currentInput.length(); i++) {
            ch[i] = currentInput.charAt(i);
        }

        while(checked == false) {
            for(int j = 0; j < currentInput.length() && stringIsValid == true; j++)
            {
                if (ch.length > 1 && ch[j] == '.')
                {
                    if(ch[j+1] == '0' || ch[j+1] == '1' || ch[j+1] == '2' || ch[+1] == '3' || ch[+1] == '4'
                            || ch[j+1] == '5' || ch[j+1] == '6' || ch[j+1] == '7' || ch[j+1] == '8' || ch[j+1] == '9')
                    {
                        stringIsValid = true;
                    }
                }
                else if( ch[j] == 'e')
                {
                    if((ch.length-j)>3 && ch[j+1] == 'x')
                    {
                        if(ch[j+2] == 'p') stringIsValid = true;
                        else expProblem = true;
                    }
                    else expProblem = true;
                }
                else if(ch[j] == 'x')
                {
                    if((ch.length-j)>2 && j>0 && ch[j-1] == 'e')
                    {
                        if(ch[j+1] == 'p')stringIsValid = true;
                        else expProblem = true;
                    }
                    else expProblem = true;
                }
                else if( ch[j] == 'p')
                {
                    if((ch.length-j)>1 && j>1 && ch[j-2] == 'e')
                    {
                        if(ch[j-1] == 'x')
                        {
                            if(ch[j+1] == '0' || ch[j+1] == '1' || ch[j+1] == '2' || ch[j+1] == '3' || ch[j+1] == '4'
                                    || ch[j+1] == '5' || ch[j+1] == '6' || ch[j+1] == '7' || ch[j+1] == '8' || ch[j+1] == '9' || ch[j+1] == '(')
                            {
                                stringIsValid = true;
                            }
                            else expProblem = true;
                        }
                        else expProblem = true;
                    }
                    else expProblem = true;
                }
                else if(ch[j] == 'l')
                {
                    if((ch.length-j)>2 && ch[j+1] == 'n') stringIsValid = true;
                    else lnProblem = true;
                }
                else if(ch[j] == 'n' )
                {
                    if((ch.length-j)>1 && j>0 && ch[j-1] == 'l')
                    {
                        if(ch[j+1] == '0' || ch[j+1] == '1' || ch[j+1] == '2' || ch[j+1] == '3' || ch[j+1] == '4'
                                || ch[j+1] == '5' || ch[j+1] == '6' || ch[j+1] == '7' || ch[j+1] == '8' || ch[j+1] == '9' || ch[j+1] == '(')
                        {
                            stringIsValid = true;
                        }
                        else lnProblem = true;
                    }
                    else lnProblem = true;
                }
                // Check if char is a number from 0-9
                else if(ch[j] == '0' || ch[j] == '1' || ch[j] == '2' || ch[j] == '3' || ch[j] == '4'
                        || ch[j] == '5' || ch[j] == '6' || ch[j] == '7' || ch[j] == '8' || ch[j] == '9')
                {
                    stringIsValid = true;
                }
                // Check if char is '+', '*' or '/'.
                //Then checking if the char before it in array is either '+', '-', '*', or '/'.
                //if it is string isn't valid because duplicate operator
                else if(ch[j] == '+' || ch[j] == '*' || ch[j] == '/' || ch[j] == '^')
                {
                    if(ch.length == 1)
                    {
                        stringIsValid = false;
                        notFullProblem = true;
                    }
                    else
                    {
                        if(j>0)
                        {
                            if(ch[j-1] == '+' || ch[j-1] == '-' || ch[j-1] == '*' || ch[j-1] == '/' )
                            {
                                stringIsValid = false;
                                doubleOperator = true;
                            }
                            else stringIsValid = true;
                        }
                    }

                }
                else if(ch[j] == '(' || ch[j] == ')')
                {
                    stringIsValid = true;
                    if(ch[j] == '(') countOfLB++;
                    if(ch[j] == ')') countOfRB++;
                }
                // Check if char is '-'
                else if(ch[j] == '-')
                {
                    if(ch.length == 1) // check if '-' is only char in string
                    {
                        stringIsValid = false;
                        notFullProblem = true;
                        firstItemInStringIsOperator = true;
                    }
                    else
                    {
                        if(j>0)
                        {
                            if(ch[j-1] == '0' || ch[j-1] == '1' || ch[j-1] == '2' || ch[j-1] == '3' || ch[j-1] == '4'
                                    || ch[j-1] == '5' || ch[j-1] == '6' || ch[j-1] == '7' || ch[j-1] == '8' || ch[j-1] == '9'|| ch[j-1] == '('|| ch[j-1] == ')')// check for number behind '-'
                            {
                                stringIsValid = true;
                            }
                            else if(ch[j-1] == '-' && (j-1) == 0) // check for double minus at start
                            {
                                stringIsValid = false;
                                doubleOperator = true;
                            }
                            else if(ch[j-1] == '+' || ch[j-1] == '-' || ch[j-1] == '*' || ch[j-1] == '/') // check for "ok" operator behind it
                            {
                                stringIsValid = true;
                            }
                            else
                            {
                                stringIsValid = false;
                                notNumberOrOperator = true;
                            }
                        }
                        else stringIsValid = true;
                    }
                }
                else
                {
                    stringIsValid = false;
                    notNumberOrOperator = true;
                }
            }
            if(ch[ch.length-1] == '+' || ch[ch.length-1] == '-' || ch[ch.length-1] == '*' || ch[ch.length-1] == '/' || ch[ch.length-1] == '^') // check if last char in string isnt operator
            {
                stringIsValid = false;
                lastItemInStringIsOperator = true;
            }
            if(ch[0] == '+' || ch[0] == '*' || ch[0] == '/' || ch[0] == '^') // check if first char in string is operator that isnt minus
            {
                stringIsValid = false;
                firstItemInStringIsOperator = true;
            }
            if(countOfLB != countOfRB)
            {
                stringIsValid = false;
                oddNumberOfBrackets = true;
            }
            if(lnProblem == true || expProblem == true)stringIsValid = false;

            if(stringIsValid == false)
            {
                if(notNumberOrOperator == true) returnMessage = returnMessage + "ERROR: Please only enter numbers and operators. ('+', '-', '*', '/', '^')\n";
                if(doubleOperator == true) returnMessage = returnMessage + "ERROR: Do not duplicate operation character.\n";
                if(firstItemInStringIsOperator == true) returnMessage = returnMessage + "ERROR: First item can't be operator unless starting with minus number.\n";
                if(lastItemInStringIsOperator == true) returnMessage = returnMessage + "ERROR: Last item can't be operator.\n";
                if(notFullProblem == true) returnMessage = returnMessage + "ERROR: Please enter a full problem.\n";
                if (oddNumberOfBrackets == true) returnMessage = returnMessage + "ERROR: Please enter as many left brackets as right brackets.\n";
                if (expProblem == true) returnMessage = returnMessage + "ERROR: Use 'exp' followed by a number or bracket please for exponent.\n";
                if (lnProblem == true) returnMessage = returnMessage + "ERROR: Use 'ln' followed by a number or bracket please for natural log.\n";
                return returnMessage;
            }
            else System.out.println("Problem is valid");
            checked = true;
        }

        return "Valid"; // if here, then calc is valid
    }

    //Evaluator is a class which will calculate the value in double of the string passed into evaluator.compute(dstr)
    public static class Evaluator
    {
        String str = "";
        int pos = -1, ch;

        public double compute (String toCalculate)
        {
            str = toCalculate.replaceAll(" ", "");;
            return parse ();
        }

        void nextChar ()
        {
            // gets the next char in the String
            pos++;			// move the pointer to the next place
            if (pos < str.length ()) ch = str.charAt (pos);
            else ch = -1;
        }

        boolean eat (int charToEat)
        {
            while (ch == ' ') nextChar (); // consume and ignore spaces
            if (ch == charToEat)  // if the char expected is found, it is used and the ch moves onto the next character
            {
                nextChar ();
                return true;
            }
            else return false;  // the else is not needed as if true would return anyway
        }

        double parse ()		// starts here and tries to parse the string to be calculated
        {
            nextChar ();   // starts by loading up the first character (which could be a space)
            double x = getNextExpression ();  // at the outside, everything is an expression to tries to find one
            if (pos < str.length ())  // if the expression is finished and we are not at the end of the string, then it is an error
                throw new RuntimeException ("Unexpected: " + (char) ch);
            str = "";
            pos = -1;
            return x;
        }


        // an expression can contain an addsum or
        // a term is a parsefactor, or is then * or / times a factor
        // a factor, is exp, ln, power, or stuff in brackets

        double getNextExpression ()
        {
            double x = getNextMulDiv (); // starts by trying to find a value to start
            while(true)
            {
                if (eat ('+'))	        x += getNextMulDiv ();	// + next value
                else if (eat ('-'))     x -= getNextMulDiv ();	// - next value
                else return x;
            }
        }

        double getNextMulDiv ()
        {
            double x = getNextVal ();

            while(true)
            {
                if (eat ('*')) 	        x *= getNextVal ();	// x next value
                else if (eat ('/')) 	x /= getNextVal ();	// / next value
                else return x;
            }
        }

        double getNextVal () // calc a number, with Brackets, Power, Exp or Ln
        {
            if (eat ('+')) return +getNextVal ();	//  + next value
            if (eat ('-')) return -getNextVal ();	//  - next value

            double x;
            int startPos = this.pos;
            if (eat ('('))  // if find a bracket, then need to calculate what is in that bracket, and then expect closing bracket
            {
                x = getNextExpression ();
                if (!eat (')')) throw new RuntimeException ("Missing ')'");
            }
            else if ((ch >= '0' && ch <= '9') || ch == '.') // if there are numbers or a dot
            {
                while ((ch >= '0' && ch <= '9') || ch == '.')  nextChar (); // since the first one was a number, go to the end of the numbers
                x = Double.parseDouble (str.substring (startPos, this.pos));  // at this point, we are at the end of the numbers
                // would cause error if was not a number e.g. 10.3.4 would not work
            }
            else if (ch >= 'a' && ch <= 'z')
            {
                while (ch >= 'a' && ch <= 'z')  nextChar ();  // find the end of the letters  should be exp or ln - all lower cases
                String text = str.substring (startPos, this.pos);

                if (eat ('('))
                {
                    x = getNextExpression ();  // since brackets now after text, need to calculate what is in the brackets
                    if (!eat (')'))  throw new RuntimeException ("Missing ')' after argument to " +text); // may not need these checks since checking before goes in
                }
                else x = getNextVal (); // at this stage, inside exp or ln, so need to calculate what will that calculate on

                if (text.equals ("exp")) x = Math.exp (x);
                else if (text.equals ("ln")) x = Math.log (x);  // natural log
                else throw new RuntimeException ("Unknown function: " + text);
            }
            else throw new RuntimeException ("Unexpected: " + (char) ch);

            // if get here and then find a ^, then we want to raise whatever we have calculated to the power of what we find next
            if (eat ('^'))  x = Math.pow (x, getNextVal ());	// exponentiation

            return x;  // will return whatever was calculated
        }
    }

}
