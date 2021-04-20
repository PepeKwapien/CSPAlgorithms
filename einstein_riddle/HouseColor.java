package einstein_riddle;

import java.util.ArrayList;
import java.util.Collections;

import coloring_map.Color;
import interfaces.IChooseValue;

public class HouseColor extends Color {
	
	private static ArrayList<String> domain = new ArrayList<String>();
	
	static  {
		domain.add("red");
		domain.add("white");
		domain.add("yellow");
		domain.add("green");
		domain.add("blue");
		Collections.shuffle(domain);
	}

	public HouseColor(IChooseValue icv) {
		super(domain, icv);
	}
}
