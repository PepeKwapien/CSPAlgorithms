package einstein_riddle;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class Nationality implements IValue {

	private ArrayList<String> domain;
	private String currentNationality;
	private IChooseValue icv;
	
	public Nationality(IChooseValue icv) {
		this.icv = icv;
		domain = new ArrayList<String>();
		domain.add("norwegian");
		domain.add("english");
		domain.add("danish");
		domain.add("german");
		domain.add("swedish");
		Collections.shuffle(domain);
		currentNationality = null;
	}
	
	@Override
	public ArrayList<String> getDomain() {
		return domain;
	}

	@Override
	public String getValue() {
		return this.currentNationality;
	}

	@Override
	public void set(String newValue) {
		this.currentNationality = newValue;
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
