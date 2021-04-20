package einstein_riddle_constraints;

import java.util.ArrayList;

import einstein_riddle.House;
import interfaces.IConstraint;
import interfaces.IValue;
import interfaces.IVariable;

public class EinsteinUnique implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		House[] houses = (House[]) variables;
		
		ArrayList<String> values = new ArrayList<String>();
		
		for(House h : houses) {
			for(IValue iv : h.getValues()) {
				if(iv.getValue() != null) {
					if(values.contains(iv.getValue())) {
						return false;
					}
					else {
						values.add(iv.getValue());
					}
				}
			}
		}
		
		return true;
	}

}
