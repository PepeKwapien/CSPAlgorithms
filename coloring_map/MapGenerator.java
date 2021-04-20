package coloring_map;

import java.util.ArrayList;
import java.util.Random;

import choose_value.DefaultOrderValue;
import choose_variable.DefaultOrderVariable;
import csp.CSP;
import interfaces.IChooseValue;
import interfaces.IConstraint;
import interfaces.IVariable;

public class MapGenerator {

	public static CSP generate(int n, int maxX, int maxY) {
		return generate(n, maxX, maxY, null, new DefaultOrderValue());
	}
	
	public static CSP generate(int n, int maxX, int maxY, ArrayList<String> colors, IChooseValue icv) {
		MapArea[] map = new MapArea[n];
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Segment> segments = new ArrayList<Segment>();
		Random r = new Random();
		IConstraint[] c = {new AdjacentAreasConstraint()};
		
		if(maxX <= 0 || maxY <= 0 || n > maxX*maxY) {
			return new CSP(map, c, new DefaultOrderVariable());
		}
		
		for(int i = 0; i < n; i++) {
			int x = r.nextInt(maxX);
			int y = r.nextInt(maxY);
			Point p = new Point(x, y);
			if(points.contains(p)) {
				i--;
			}
			else {
				points.add(p);
				map[i] = createMap(x, y, colors, icv);
			}
		}
		
		for(int i=n - 1; i >= 0; i--) {
			for(int j=i-1; j >= 0; j--) {
				boolean flag = true;
				Segment segment = new Segment(new Point(map[i].getX(), map[i].getY()), new Point(map[j].getX(), map[j].getY()));
				for(Segment s : segments) {
					if(Point.doIntersect(segment.getStart(), segment.getEnd(), s.getStart(), s.getEnd())) {
						flag = false;
						break;
					}
				}
				if(flag) {
					segments.add(segment);
					map[i].addAdjacentArea(map[j]);
					map[j].addAdjacentArea(map[i]);
				}
			}
		}
		
		return new CSP(map, c, new DefaultOrderVariable()); //new MostConstrainedVariable() new DefaultOrderVariable()
	}
	
	public static CSP setChooseValue(CSP csp, IChooseValue icv) {
		IVariable[] variables = csp.getVariables();
		
		for(int i = 0; i < variables.length; i++) {
			if(variables[i].getValues()[0] instanceof Color) {
				variables[i].getValues()[0].setIcv(icv);
			}
		}
		
		return csp;
	}
	
	private static MapArea createMap(int x, int y, ArrayList<String> colors, IChooseValue icv) {
		if(colors == null) {
			return new MapArea(x, y);
		}
		else {
			return new MapArea(x, y, colors, icv);
		}
	}
}
