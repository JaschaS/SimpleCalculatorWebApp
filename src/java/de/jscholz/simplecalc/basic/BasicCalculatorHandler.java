package de.jscholz.simplecalc.basic;

import de.jscholz.simplecalc.BasicCalculator;
import de.jscholz.simplecalc.WebCalculator;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author JScholz
 */
@ManagedBean ( name = "basicCalc" )
@ApplicationScoped
public class BasicCalculatorHandler implements WebCalculator {

    @EJB
    private BasicCalculator calculator;
    private String displayText;
    private final List<Integer> numberCache;
    private Operators operator;
    private boolean clearDisplayInsert;

    public BasicCalculatorHandler () {
        displayText = "0";
        numberCache = new ArrayList<> ();
        operator = Operators.NONE;
        clearDisplayInsert = true;
    }

    @Override
    public void addDigit ( final int digit ) {

        //the digit in the range of 0 and 9 (both included).
        //if the digit is less than 0 or greater than 9, return.
        if ( digit < 0 || 9 < digit ) {
            return;
        }

        if ( clearDisplayInsert ) {
            displayText = "";
            clearDisplayInsert = false;
        }

        displayText += digit;

    }

    @Override
    public void addition () {
        operation ( Operators.ADD );
    }

    @Override
    public void subtract () {
    }

    @Override
    public void multiply () {
        operation ( Operators.MUL );
    }

    @Override
    public void divide () {
    }

    @Override
    public void clear () {
        displayText = "0";
        clearDisplayInsert = true;
        operator = Operators.NONE;
    }

    @Override
    public void clearAll () {
        displayText = "0";
        numberCache.clear ();
        clearDisplayInsert = true;
        operator = Operators.NONE;
    }

    @Override
    public void result () {
        if ( operator == Operators.NONE ) {
            return;
        }

        //Add the display to the cache.
        final int a = Integer.parseInt ( displayText );
        numberCache.add ( a );
        calculateResult ();

        //There is no operation currently available.
        operator = Operators.NONE;

    }

    @Override
    public String toString () {
        return displayText;
    }

    private void operation ( final Operators newOperator ) {
        final int a = Integer.parseInt ( displayText );

        //save number, if it is a new number.
        if ( numberCache.isEmpty () || a != numberCache.get ( 0 ) ) {
            numberCache.add ( a );
        }

        operator = newOperator;
        clearDisplayInsert = true;

        if ( enoughNumbersInCache () ) {
            calculateResult ();
        }
    }

    private boolean enoughNumbersInCache () {
        return numberCache.size () >= 2;
    }

    private void calculateResult () {
        if ( !enoughNumbersInCache () ) {
            return;
        }

        final int a = numberCache.get ( 0 );
        final int b = numberCache.get ( 1 );
        int result = 0;

        switch ( operator ) {
            case ADD:

                result = calculator.addition ( a, b );

                break;
            case SUB:

                result = calculator.subtract ( a, b );

                break;
            case MUL:

                result = calculator.multiply ( a, b );

                break;
            case DIV:
                throw new IllegalStateException ( "Division not supported!" );
            default:
                throw new IllegalStateException ( "Not a valid Operation!" );
        }

        displayText = result + "";

        //Clear the cache, because we calculated already the result.
        numberCache.clear ();

        //Add the result, so we can reuse it.
        numberCache.add ( result );

        //We display the result, therefore clear the display when the next digit is inserted.
        clearDisplayInsert = true;
    }

    private enum Operators {

        NONE,
        ADD,
        SUB,
        MUL,
        DIV
    }

}
