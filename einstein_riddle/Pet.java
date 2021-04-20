package einstein_riddle;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class Pet implements IValue {

	private ArrayList<String> domain;
	private String currentPet;
	private IChooseValue icv;
	
	public Pet(IChooseValue icv) {
		this.icv = icv;
		domain = new ArrayList<String>();
		domain.add("dog");
		domain.add("cat");
		domain.add("horse");
		domain.add("bird");
		domain.add("fish");
		Collections.shuffle(domain);
		currentPet = null;
	}
	
	@Override
	public ArrayList<String> getDomain() {
		return domain;
	}

	@Override
	public String getValue() {
		return this.currentPet;
	}

	@Override
	public void set(String newValue) {
		this.currentPet = newValue;
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
