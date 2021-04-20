package coloring_map;

import java.util.ArrayList;

import choose_value.DefaultOrderValue;
import interfaces.IChooseValue;
import interfaces.IValue;
import interfaces.IVariable;

public class MapArea implements IVariable {
	
	private int x;
	private int y;
	private ArrayList<MapArea> adjacentAreas;
	private Color[] color;

	public MapArea(int x, int y) {
		this.x = x;
		this.y = y;
		this.adjacentAreas = new ArrayList<MapArea>();
		color = new Color[1];
		color[0] = new Color(new DefaultOrderValue());
	}
	
	public MapArea(int x, int y, ArrayList<String> colors) {
		this.x = x;
		this.y = y;
		this.adjacentAreas = new ArrayList<MapArea>();
		color = new Color[1];
		color[0] = new Color(colors, new DefaultOrderValue());
	}
	
	public MapArea(int x, int y, IChooseValue icv) {
		this.x = x;
		this.y = y;
		this.adjacentAreas = new ArrayList<MapArea>();
		color = new Color[1];
		color[0] = new Color(icv);
	}
	
	public MapArea(int x, int y, ArrayList<String> colors, IChooseValue icv) {
		this.x = x;
		this.y = y;
		this.adjacentAreas = new ArrayList<MapArea>();
		color = new Color[1];
		color[0] = new Color(colors, icv);
	}
	
	public void addAdjacentArea(MapArea ma) {
		this.adjacentAreas.add(ma);
	}
	
	public ArrayList<MapArea> getAdjacentAreas(){
		return this.adjacentAreas;
	}
	
	public void setAdjacentAreas(ArrayList<MapArea> adjacentAreas) {
		this.adjacentAreas = adjacentAreas;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public IValue[] getValues() {
		return color;
	}

	@Override
	public boolean isSet() {
		return color[0].getValue() != null;
	}

	@Override
	public IVariable[] clone(IVariable[] array) {
		MapArea[] oldMap = (MapArea[]) array;
		MapArea[] newMap = new MapArea[array.length];
		for(int i = 0; i < array.length; i++) {
			newMap[i] = oldMap[i].copyArea();
		}
		
		for(int i = 0; i < array.length; i++) {
			ArrayList<MapArea> newAdjacent = new ArrayList<MapArea>();
			for(int j = 0; j < newMap[i].getAdjacentAreas().size(); j++) {
				for(int k = 0; k < array.length; k++) {
					if(newMap[i].getAdjacentAreas().get(j).equals(newMap[k])) {
						newAdjacent.add(newMap[k]);
					}
				}
			}
			newMap[i].setAdjacentAreas(newAdjacent);
		}
		
		return newMap;
	}
	
	private MapArea copyArea() {
		ArrayList<String> copiedColors = new ArrayList<>();
		for(String color : this.color[0].getDomain()) {
			copiedColors.add(color);
		}
		MapArea mockArea = new MapArea(this.getX(), this.getY(), copiedColors);
		for(MapArea ma : this.adjacentAreas) {
			mockArea.addAdjacentArea(ma);
		}
		mockArea.setValue(0, this.color[0].getValue());
		return mockArea;
	}

	@Override
	public void setValue(int index, String value) {
		this.color[0].set(value);
	}
	
	@Override
	public String toString() {
		String toString = "";
		toString += x + ":" + y + " = " + color[0].getValue() + " -> ";
		for(MapArea ma : this.adjacentAreas) {
			toString += ma.getX() + ":" + ma.getY() + " | ";
		}
		
		return toString;
	}
	
	@Override
	public boolean equals(Object object) {
		MapArea other = (MapArea) object;
		return other.getX() == x && other.getY() == y && other.getValues()[0].getValue()!= null
				&& color[0].getValue() != null && other.getValues()[0].getValue().equals(color[0].getValue());
	}

}
