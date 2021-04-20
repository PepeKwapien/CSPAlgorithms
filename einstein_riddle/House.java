package einstein_riddle;

import java.util.ArrayList;

import choose_value.DefaultOrderValue;
import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class House implements IVariable {
	
	private int number;
	private IValue[] values;
	private IChooseValue icv;
	
	public House(int number) {
		this.icv = new DefaultOrderValue();
		this.number = number;
		this.values = new IValue[5];
		this.values[0] = new HouseColor(icv);
		this.values[1] = new Nationality(icv);
		this.values[2] = new Pet(icv);
		this.values[3] = new Beverage(icv);
		this.values[4] = new Tobacco(icv);
	}
	
	public House(int number, IChooseValue icv) {
		this.icv = icv;
		this.number = number;
		this.values = new IValue[5];
		this.values[0] = new HouseColor(icv);
		this.values[1] = new Nationality(icv);
		this.values[2] = new Pet(icv);
		this.values[3] = new Beverage(icv);
		this.values[4] = new Tobacco(icv);
	}
	
	public int getNumber() {
		return number;
	}

	@Override
	public IValue[] getValues() {
		return values;
	}

	@Override
	public void setValue(int index, String value) {
		this.values[index].set(value);
	}

	@Override
	public boolean isSet() {
		boolean result = true;
		
		for(IValue iv : values) {
			result = result && iv.getValue() != null;
		}
		
		return result;
	}

	@Override
	public IVariable[] clone(IVariable[] array) {
		House[] newHouses = new House[array.length];
		House[] oldHouses = (House[]) array;
		
		for(int i = 0; i < array.length; i++) {
			newHouses[i] = oldHouses[i].copyHouse();
		}
		
		return newHouses;
	}
	
	private House copyHouse() {
		House newHouse = new House(number, this.icv);
		for(int i = 0; i < 5; i++) {
			ArrayList<String> newDomain = new ArrayList<>();
			for(String value : values[i].getDomain()) {
				newDomain.add(value);
			}
			newHouse.values[i].set(values[i].getValue());
			newHouse.values[i].setDomain(newDomain);
		}
		return newHouse;
	}
	
	@Override
	public String toString() {
		String result = "";
		result += "Number: " + number;
		for(IValue iv : this.values) {
			result += "\n" + iv.getValue();
		}
		
		return result;
	}

}
