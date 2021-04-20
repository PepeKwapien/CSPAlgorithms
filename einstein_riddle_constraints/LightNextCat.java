package einstein_riddle_constraints;

import einstein_riddle.House;
import einstein_riddle.HouseColor;
import einstein_riddle.Pet;
import einstein_riddle.Tobacco;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class LightNextCat implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		House cat = null;
		House light = null;
		
		for(House h : houses) {
			for(IValue iv : h.getValues()) {
				if(iv instanceof Pet && iv.getValue() != null && iv.getValue().equals("cat")) {
					cat = h;
				}
				if(iv instanceof Tobacco && iv.getValue() != null && iv.getValue().equals("light")) {
					light = h;
				}
			}
		}
		
		if(cat == null || light == null) {
			return true;
		}
		else if(light.getNumber() != cat.getNumber() - 1 && light.getNumber() != cat.getNumber() + 1) {
			return false;
		}
		
		return true;
	}

}
