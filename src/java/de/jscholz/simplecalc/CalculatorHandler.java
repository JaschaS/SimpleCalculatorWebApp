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

    public CalculatorHandler () {
        displayText = "0";
    }

    public void addDigit ( final int digit ) {
        
        //the digit in the range of 0 and 9 (both included).
        //if the digit is less than 0 or greater than 9, return.
        if(digit < 0 || 9 < digit) return;
        
        //We display always a zero, even if we don't diplay a number.
        if(displayText.equals ( "0" )) {
            
            //Remove the zero, so that the display text is not e.g 01.
            displayText = "";
        }
        
        displayText += digit;
    }

    public void clear () {
    }

    public void clearAll () {
    }

    public void subtract () {

    }

    public void addition () {
    }

    public void multiply () {
    }

    public void result () {
    }

    @Override
    public String toString () {
        return displayText;
    }
}
