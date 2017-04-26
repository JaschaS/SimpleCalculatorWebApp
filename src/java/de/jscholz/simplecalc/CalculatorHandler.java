package de.jscholz.simplecalc;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author JScholz
 */
@ManagedBean
@ApplicationScoped
public class CalculatorHandler {

    @EJB
    private SimpleCalculator calculator;
    private String displayText;
    private String expression;
    private String operator;

    public CalculatorHandler () {
        displayText = "0";
        operator = null;
    }

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

            expression = displayText + " " + operator + " ";
            displayText = digit + "";
            operator = null;
        }
    }

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

    public void clearAll () {
        displayText = "0";
    }

    public void subtract () {

    }

    public void addition () {
        if ( expression != null && !expression.isEmpty () ) {
            result ();
        }
        operator = "+";
    }

    public void multiply () {
        if ( expression != null && !expression.isEmpty () ) {
            result ();
        }
        operator = "*";
    }

    public void result () {
        expression += displayText;
        displayText = calculator.calculate ( expression );
        operator = null;
        expression = null;
    }

    @Override
    public String toString () {
        return displayText;
    }
}
