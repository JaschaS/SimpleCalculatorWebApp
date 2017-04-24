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

    public CalculatorHandler () {
    }

    public void addDigit ( final String digit ) {
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
        return "0";
    }
}
