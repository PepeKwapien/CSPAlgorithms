package coloring_map;

import interfaces.IConstraint;
import interfaces.IVariable;

public class AdjacentAreasConstraint implements IConstraint {

	@Override
	public boolean validate(IVariable[] variables) {
		MapArea[] areas = (MapArea[]) variables;
		
		for(MapArea ma : areas) {
			String currentColor = ma.getValues()[0].getValue();
			if(currentColor == null) {
				continue;
			}
			
			for(MapArea ma2 : ma.getAdjacentAreas()) {
				if(ma2.getValues()[0].getValue() != null && ma2.getValues()[0].getValue().equals(currentColor)) {
					return false;
				}
			}
		}
		
		return true;
	}

}
