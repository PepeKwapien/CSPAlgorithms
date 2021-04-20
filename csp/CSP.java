package csp;

import interfaces.IChooseVariable;
import interfaces.IConstraint;
import interfaces.IVariable;

public class CSP {
	
	private IVariable[] variablesWithDomains;
	private IConstraint[] constraints;
	private IChooseVariable icv;
	
	public CSP(IVariable[] variablesWithDomains, IConstraint[] constraints, IChooseVariable icv) {
		this.variablesWithDomains = variablesWithDomains;
		this.constraints = constraints;
		this.icv = icv;
	}
	
	public void setIcv(IChooseVariable icv) {
		this.icv = icv;
	}

	public boolean isFinished() {
		boolean noEmpty = true;
		
		for(IVariable iv : this.variablesWithDomains) {
			if(!iv.isSet()) {
				noEmpty = false;
				break;
			}
		}
		
		return noEmpty;
	}
	
	public boolean isLegit() {
		boolean isLegit = variablesWithDomains.length != 0 ? true : false;
		
		for(IConstraint ic : this.constraints) {
			if(!ic.validate(this.variablesWithDomains)) {
				isLegit = false;
				break;
			}
		}
		
		return isLegit;
	}
	
	public IVariable[] getVariables() {
		return this.variablesWithDomains;
	}
	
	public CSP clone() {
		IVariable[] newVariables = variablesWithDomains.length != 0 ? variablesWithDomains[0].clone(variablesWithDomains) : new IVariable[0];
		
		return new CSP(newVariables, this.constraints, this.icv);
	}
	
	public void print() {
		for(IVariable iv : this.variablesWithDomains) {
			System.out.println(iv);
		}
	}
	
	public void sort() {
		this.icv.sort(variablesWithDomains);
	}
}
