package einstein_riddle;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class Tobacco implements IValue {

	private ArrayList<String> domain;
	private String currentTobacco;
	private IChooseValue icv;
	
	public Tobacco(IChooseValue icv) {
		this.icv = icv;
		domain = new ArrayList<String>();
		domain.add("light");
		domain.add("cigar");
		domain.add("pipe");
		domain.add("menthol");
		domain.add("unfiltered");
		Collections.shuffle(domain);
		currentTobacco = null;
	}
	
	@Override
	public ArrayList<String> getDomain() {
		return domain;
	}

	@Override
	public String getValue() {
		return this.currentTobacco;
	}

	@Override
	public void set(String newValue) {
		this.currentTobacco = newValue;
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
