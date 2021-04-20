package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.Nationality;
import einstein_riddle.Pet;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class SwedishDog implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue dog = null;
			IValue swedish = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof Pet) {
					dog = iv;
				}
				else if(iv instanceof Nationality) {
					swedish = iv;
				}
			}
			
			if(dog.getValue() != null && dog.getValue().equals("dog")
					&& swedish.getValue() != null && !swedish.getValue().equals("swedish")) {
				return false;
			}
			else if(swedish.getValue() != null && swedish.getValue().equals("swedish")
					&& dog.getValue() != null && !dog.getValue().equals("dog")) {
				return false;
			}
		}
		
		return true;
	}

}
