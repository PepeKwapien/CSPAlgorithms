package einstein_riddle_constraints;

import einstein_riddle.Beverage;
import einstein_riddle.House;
import einstein_riddle.Nationality;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class DanishTea implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue tea = null;
			IValue danish = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof Beverage) {
					tea = iv;
				}
				else if(iv instanceof Nationality) {
					danish = iv;
				}
			}
			
			if(tea.getValue() != null && tea.getValue().equals("tea")
					&& danish.getValue() != null && !danish.getValue().equals("danish")) {
				return false;
			}
			else if(danish.getValue() != null && danish.getValue().equals("danish")
					&& tea.getValue() != null && !tea.getValue().equals("tea")) {
				return false;
			}
		}
		
		return true;
	}

}
