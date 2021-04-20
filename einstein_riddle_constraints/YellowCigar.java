package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.HouseColor;
import einstein_riddle.Tobacco;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class YellowCigar implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue yellow = null;
			IValue cigar = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof HouseColor) {
					yellow = iv;
				}
				else if(iv instanceof Tobacco) {
					cigar = iv;
				}
			}
			
			if(yellow.getValue() != null && yellow.getValue().equals("yellow")
					&& cigar.getValue() != null && !cigar.getValue().equals("cigar")) {
				return false;
			}
			else if(cigar.getValue() != null && cigar.getValue().equals("cigar")
					&& yellow.getValue() != null && !yellow.getValue().equals("yellow")) {
				return false;
			}
		}
		
		return true;
	}

}
