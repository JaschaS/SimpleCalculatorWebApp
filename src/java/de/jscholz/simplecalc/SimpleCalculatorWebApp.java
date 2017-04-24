package de.jscholz.simplecalc;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author JScholz
 */
@ManagedBean
@SessionScoped
@Deprecated
public class SimpleCalculatorWebApp implements Serializable {

    @EJB
    private SimpleCalculator calculator;
    private String input = "123";
    private String result = "0";

    public SimpleCalculator getCalculator () {
        return calculator;
    }

    public void setCalculator ( SimpleCalculator calculator ) {
        this.calculator = calculator;
    }

    public void setInput(final String input) {
        this.input = input;
    }
    
    public String getInput() {
        return this.input;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public void calculate() {  
        this.result = calculator.calculate ( input );
    }
 
}
