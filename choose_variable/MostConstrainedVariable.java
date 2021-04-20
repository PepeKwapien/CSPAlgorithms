package choose_variable;

import interfaces.IChooseVariable;
import interfaces.IValue;
import interfaces.IVariable;

public class MostConstrainedVariable implements IChooseVariable {

	@Override
	public void sort(IVariable[] variables) {
		int[] possibleValues = new int[variables.length];
		
		for(int i = 0; i < variables.length; i++) {
			for(IValue value : variables[i].getValues()) {
				possibleValues[i] += value.getDomain().size();
			}
		}
		
		for(int i = 0; i < variables.length - 1; i++) {
			for(int j = 0; j < variables.length - i - 1; j++) {
				if (possibleValues[j] > possibleValues[j+1])
                {
                    int temp1 = possibleValues[j];
                    possibleValues[j] = possibleValues[j+1];
                    possibleValues[j+1] = temp1;
                    
                    IVariable temp2 = variables[j];
                    variables[j] = variables[j+1];
                    variables[j+1] = temp2;
                }
			}
		}
	}

}
