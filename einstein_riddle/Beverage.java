package einstein_riddle;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class Beverage implements IValue {

	private ArrayList<String> domain;
	private String currentBeverage;
	private IChooseValue icv;
	
	public Beverage(IChooseValue icv) {
		this.icv = icv;
		domain = new ArrayList<String>();
		domain.add("tea");
		domain.add("coffee");
		domain.add("beer");
		domain.add("water");
		domain.add("milk");
		Collections.shuffle(domain);
		currentBeverage = null;
	}
	
	@Override
	public ArrayList<String> getDomain() {
		return domain;
	}

	@Override
	public String getValue() {
		return this.currentBeverage;
	}

	@Override
	public void set(String newValue) {
		this.currentBeverage = newValue;
	}
	
	@Override
	public void setDomain(ArrayList<String> domain) {
		// TODO Auto-generated method stub
		this.domain = domain;
	}
	
	@Override
	public void sort(IVariable[] context) {
		// TODO Auto-generated method stub
		this.icv.sort(domain, context);
	}
	
	@Override
	public void setIcv(IChooseValue icv) {
		// TODO Auto-generated method stub
		this.icv = icv;
	}

}
