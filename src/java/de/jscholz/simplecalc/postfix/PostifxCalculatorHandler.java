package de.jscholz.simplecalc.postfix;

import de.jscholz.simplecalc.PostFixCalculator;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author JScholz
 */
@ManagedBean
@ApplicationScoped
public class PostifxCalculatorHandler {

    @EJB
    private PostFixCalculator calculator;
    private String displayText;
    private String result;
    
    public void calculate() {
        
        if(displayText == null || displayText.isEmpty ()) return;
        
        result = calculator.calculate ( displayText );
    }
    
    public void setInput(final String input) {
        displayText = input;
    }
    
    public String getInput() {
        return displayText;
    }
    
    public String getResult() {
        return result;
    }
}
