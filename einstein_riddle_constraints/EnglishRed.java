package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.HouseColor;
import einstein_riddle.Nationality;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class EnglishRed implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue red = null;
			IValue english = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof HouseColor) {
					red = iv;
				}
				else if(iv instanceof Nationality) {
					english = iv;
				}
			}
			
			if(red.getValue() != null && red.getValue().equals("red")
					&& english.getValue() != null && !english.getValue().equals("english")) {
				return false;
			}
			else if(english.getValue() != null && english.getValue().equals("english")
					&& red.getValue() != null && !red.getValue().equals("red")) {
				return false;
			}
		}
		
		return true;
	}

}
