package choose_value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class MostRepeatedValue implements IChooseValue {
	
	
	@Override
	public void sort(ArrayList<String> possibleValues, IVariable[] variables) {
		ArrayList<String> setValues = new ArrayList<String>();
		
		for(IVariable variable : variables) {
			for(IValue value : variable.getValues()) {
				if(value.getValue() != null)
					setValues.add(value.getValue());
			}
		}
		
		Collections.sort(possibleValues, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return Collections.frequency(setValues, o2) - Collections.frequency(setValues, o1);
			}
		});
	}

}
