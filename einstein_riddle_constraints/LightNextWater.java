package einstein_riddle_constraints;

import einstein_riddle.Beverage;
import einstein_riddle.House;
import einstein_riddle.Pet;
import einstein_riddle.Tobacco;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class LightNextWater implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		House water = null;
		House light = null;
		
		for(House h : houses) {
			for(IValue iv : h.getValues()) {
				if(iv instanceof Beverage && iv.getValue() != null && iv.getValue().equals("water")) {
					water = h;
				}
				else if(iv instanceof Tobacco && iv.getValue() != null && iv.getValue().equals("light")) {
					light = h;
				}
			}
		}
		
		if(water == null || light == null) {
			return true;
		}
		else if(light.getNumber() != water.getNumber() - 1 && light.getNumber() != water.getNumber() + 1) {
			return false;
		}
		
		return true;
	}

}
