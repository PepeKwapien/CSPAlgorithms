package interfaces;

import java.util.ArrayList;

public interface IValue {
	ArrayList<String> getDomain();
	void setDomain(ArrayList<String> domain);
	String getValue();
	void set(String newValue);
	void sort(IVariable[] context);
	void setIcv(IChooseValue icv);
}
