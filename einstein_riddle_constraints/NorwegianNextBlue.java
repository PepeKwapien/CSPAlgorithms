package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.HouseColor;
import einstein_riddle.Nationality;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class NorwegianNextBlue implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		House blue = null;
		House norwegian = null;
		
		for(House h : houses) {
			for(IValue iv : h.getValues()) {
				if(iv instanceof HouseColor && iv.getValue() != null && iv.getValue().equals("blue")) {
					blue = h;
				}
				else if(iv instanceof Nationality && iv.getValue() != null && iv.getValue().equals("norwegian")) {
					norwegian = h;
				}
			}
		}
		
		if(blue == null || norwegian == null) {
			return true;
		}
		else if(norwegian.getNumber() != blue.getNumber() - 1 && norwegian.getNumber() != blue.getNumber() + 1) {
			return false;
		}
		
		return true;
	}

}
