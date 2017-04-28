package de.jscholz.simplecalc.postfix;

import de.jscholz.simplecalc.PostFixCalculator;
import de.jscholz.simplecalc.WebCalculator;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author JScholz
 */
@ManagedBean
@ApplicationScoped
public class PostfixCalculatorHandler implements WebCalculator {

    @EJB
    private PostFixCalculator calculator;
    private String displayText;
    private String expression;
    private String operator;

    public PostfixCalculatorHandler () {
        displayText = "0";
        operator = null;
    }

    @Override
    public void addDigit ( final int digit ) {

        //the digit in the range of 0 and 9 (both included).
        //if the digit is less than 0 or greater than 9, return.
        if ( digit < 0 || 9 < digit ) {
            return;
        }

        if ( operator == null ) {
            //We display always a zero, even if we don't diplay a number.
            if ( displayText.equals ( "0" ) ) {

                //Remove the zero, so that the display text is not e.g 01.
                displayText = "";
            }

            displayText += digit;
        }
        else {

            if ( displayText.contains ( "-" ) ) {
                expression = "um" + displayText.substring ( 1, displayText.length () ) + " " + operator + " ";
            }
            else {
                expression = displayText + " " + operator + " ";
            }

            displayText = digit + "";
            operator = null;
        }
    }

    @Override
    public void clear () {

        //If there is only one digit left, replace the digit with a zero.
        if ( displayText.length () == 1 ) {
            displayText = "0";
        }
        else {
            //Remove the last added digit from the display.
            displayText = displayText.substring ( 0, displayText.length () - 1 );
        }
    }

    @Override
    public void clearAll () {
        displayText = "0";
    }

    @Override
    public void subtract () {

        //Otherwise set the operator.
        if ( expression != null && !expression.isEmpty () ) {
            result ();
        }
        operator = "-";

    }

    @Override
    public void addition () {
        if ( expression != null && !expression.isEmpty () ) {
            result ();
        }
        operator = "+";
    }

    @Override
    public void multiply () {
        if ( expression != null && !expression.isEmpty () ) {
            result ();
        }
        operator = "*";
    }

    @Override
    public void divide () {
    }

    @Override
    public void result () {
        expression += displayText;
        System.out.println ( expression );
        displayText = calculator.calculate ( expression );
        operator = null;
        expression = null;
    }

    @Override
    public String toString () {
        return displayText;
    }
}
