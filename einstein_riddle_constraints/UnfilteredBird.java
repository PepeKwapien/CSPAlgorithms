package einstein_riddle_constraints;

import einstein_riddle.Beverage;
import einstein_riddle.House;
import einstein_riddle.Nationality;
import einstein_riddle.Pet;
import einstein_riddle.Tobacco;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class UnfilteredBird implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue bird = null;
			IValue unfiltered = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof Pet) {
					bird = iv;
				}
				else if(iv instanceof Tobacco) {
					unfiltered = iv;
				}
			}
			
			if(bird.getValue() != null && bird.getValue().equals("bird")
					&& unfiltered.getValue() != null && !unfiltered.getValue().equals("unfiltered")) {
				return false;
			}
			else if(unfiltered.getValue() != null && unfiltered.getValue().equals("unfiltered")
					&& bird.getValue() != null && !bird.getValue().equals("bird")) {
				return false;
			}
		}
		
		return true;
	}

}
