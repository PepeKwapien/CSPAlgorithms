package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.HouseColor;
import einstein_riddle.Nationality;
import einstein_riddle.Pet;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class HorseNextYellow implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		House yellow = null;
		House horse = null;
		
		for(House h : houses) {
			for(IValue iv : h.getValues()) {
				if(iv instanceof HouseColor && iv.getValue() != null && iv.getValue().equals("yellow")) {
					yellow = h;
				}
				else if(iv instanceof Pet && iv.getValue() != null && iv.getValue().equals("horse")) {
					horse = h;
				}
			}
		}
		
		if(horse != null && yellow != null &&
				(yellow.getNumber() != horse.getNumber() - 1 && yellow.getNumber() != horse.getNumber() + 1)) {
			return false;
		}
		
		return true;
	}

}
