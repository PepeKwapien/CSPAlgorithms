package einstein_riddle_constraints;

import einstein_riddle.Beverage;
import einstein_riddle.House;
import einstein_riddle.Tobacco;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class MentholBeer implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue menthol = null;
			IValue beer = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof Beverage) {
					beer = iv;
				}
				else if(iv instanceof Tobacco) {
					menthol = iv;
				}
			}
			
			if(beer.getValue() != null && beer.getValue().equals("beer")
					&& menthol.getValue() != null && !menthol.getValue().equals("menthol")) {
				return false;
			}
			else if(menthol.getValue() != null && menthol.getValue().equals("menthol")
					&& beer.getValue() != null && !beer.getValue().equals("beer")) {
				return false;
			}
		}
		
		return true;
	}

}
