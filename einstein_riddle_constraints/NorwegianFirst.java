package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.Nationality;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class NorwegianFirst implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h : houses) {
			if(h.getNumber() == 1) {
				for(IValue iv : h.getValues()) {
					if(iv instanceof Nationality && iv.getValue() != null && !iv.getValue().equals("norwegian")) {
						return false;
					}
				}
			}
			else {
				for(IValue iv : h.getValues()) {
					if(iv instanceof Nationality && iv.getValue() != null && iv.getValue().equals("norwegian")) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

}
