package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.HouseColor;
import einstein_riddle.Nationality;
import einstein_riddle.Tobacco;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class GermanPipe implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue german = null;
			IValue pipe = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof Nationality) {
					german = iv;
				}
				else if(iv instanceof Tobacco) {
					pipe = iv;
				}
			}
			
			if(german.getValue() != null && german.getValue().equals("german")
					&& pipe.getValue() != null && !pipe.getValue().equals("pipe")) {
				return false;
			}
			else if(pipe.getValue() != null && pipe.getValue().equals("pipe")
					&& german.getValue() != null && !german.getValue().equals("german")) {
				return false;
			}
		}
		
		return true;
	}

}
