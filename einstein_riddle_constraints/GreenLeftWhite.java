package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.HouseColor;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class GreenLeftWhite implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		House green = null;
		House white = null;
		
		for(House h : houses) {
			for(IValue iv : h.getValues()) {
				if(iv instanceof HouseColor && iv.getValue() != null) {
					if(iv.getValue().equals("green")) {
						green = h;
					}
					else if(iv.getValue().equals("white")) {
						white = h;
					}
				}
			}
		}
		
		if(white != null && white.getNumber() == 1) {
			return false;
		}
		else if(green != null && green.getNumber() == 5) {
			return false;
		}
		else if(white == null || green == null) {
			return true;
		}
		else if(green.getNumber() != white.getNumber() - 1) {
			return false;
		}
		
		return true;
	}

}
