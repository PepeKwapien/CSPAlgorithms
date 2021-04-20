package einstein_riddle_constraints;

import einstein_riddle.Beverage;
import einstein_riddle.House;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class MiddleMilk implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h : houses) {
			if(h.getNumber() == 3) {
				for(IValue iv : h.getValues()) {
					if(iv instanceof Beverage && iv.getValue() != null) {
						if(iv.getValue().equals("milk")) {
							return true;
						}
						else {
							return false;
						}
					}
				}
			}
			else {
				for(IValue iv : h.getValues()) {
					if(iv instanceof Beverage && iv.getValue() != null && iv.getValue().equals("milk")) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
