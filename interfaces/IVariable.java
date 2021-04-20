package interfaces;

public interface IVariable {
	IValue[] getValues();
	void setValue(int index, String value);
	boolean isSet();
	IVariable[] clone(IVariable[] array);
}
