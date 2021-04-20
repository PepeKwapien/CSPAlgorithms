package einstein_riddle_constraints;

import einstein_riddle.Beverage;
import einstein_riddle.House;
import einstein_riddle.HouseColor;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class GreenCoffee implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		for(House h: houses) {
			IValue green = null;
			IValue coffee = null;
			for(IValue iv : h.getValues()) {
				if(iv instanceof Beverage) {
					coffee = iv;
				}
				else if(iv instanceof HouseColor) {
					green = iv;
				}
			}
			
			if(coffee.getValue() != null && coffee.getValue().equals("coffee")
					&& green.getValue() != null && !green.getValue().equals("green")) {
				return false;
			}
			else if(green.getValue() != null && green.getValue().equals("green")
					&& coffee.getValue() != null && !coffee.getValue().equals("coffee")) {
				return false;
			}
		}
		
		return true;
	}

}
