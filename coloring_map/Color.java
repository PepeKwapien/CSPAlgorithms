package coloring_map;

import java.util.ArrayList;
import java.util.Collections;

import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class Color implements IValue {
	
	private ArrayList<String> domain;
	private String currentColor;
	private IChooseValue icv;
	
	private static String[] defaultColors = {"blue", "green", "red", "yellow"};
	
	public Color(IChooseValue icv) {
		this.icv = icv;
		this.domain = new ArrayList<String>();
		for(String s: defaultColors) {
			domain.add(s);
		}
		Collections.shuffle(domain);
		this.currentColor = null;
	}
	
	public Color(ArrayList<String> colors, IChooseValue icv) {
		this.icv = icv;
		this.domain = colors;
		Collections.shuffle(domain);
		this.currentColor = null;
	}

	@Override
	public ArrayList<String> getDomain() {
		return domain;
	}

	@Override
	public String getValue() {
		return this.currentColor;
	}

	@Override
	public void set(String newValue) {
		this.currentColor = newValue;
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
