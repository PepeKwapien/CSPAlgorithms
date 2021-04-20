package test;

import java.util.ArrayList;

import algorithms.Algorithms;
import choose_value.DefaultOrderValue;
import choose_value.MostRepeatedValue;
import choose_variable.DefaultOrderVariable;
import choose_variable.MostConstrainedVariable;
import coloring_map.AdjacentAreasConstraint;
import coloring_map.MapArea;
import coloring_map.MapGenerator;
import csp.CSP;
import einstein_riddle.EinsteinRiddleGenerator;
import interfaces.IConstraint;

public class Test {

	public static void main(String[] args) {
		ArrayList<String> colors = new ArrayList<String>();
		
		colors.add("blue");
		colors.add("red");
		colors.add("green");
		colors.add("yellow");
		
		IConstraint[] c = {new AdjacentAreasConstraint()};
		
		CSP csp = null;
		
		
		csp = MapGenerator.generate(5, 10, 10);
		Algorithms.backtrackAlgorithm(csp);
		Algorithms.forwardcheckingAlgorithm(csp);
		
		csp.setIcv(new MostConstrainedVariable());
		Algorithms.backtrackAlgorithm(csp);
		Algorithms.forwardcheckingAlgorithm(csp);
		
		System.out.println("-------------------------------------");
		
		csp = MapGenerator.generate(5, 10, 10);
		Algorithms.backtrackAlgorithm(csp);
		Algorithms.forwardcheckingAlgorithm(csp);
		csp = MapGenerator.setChooseValue(csp, new MostRepeatedValue());
		Algorithms.backtrackAlgorithm(csp);
		Algorithms.forwardcheckingAlgorithm(csp);
		
		System.out.println("---------------EINSTEIN----------------------");
		
		csp = EinsteinRiddleGenerator.generate();
		Algorithms.backtrackAlgorithm(csp);
		Algorithms.forwardcheckingAlgorithm(csp);
		
		csp = EinsteinRiddleGenerator.generate(new MostRepeatedValue());
		Algorithms.backtrackAlgorithm(csp);
		Algorithms.forwardcheckingAlgorithm(csp);
		
		
		/*
		csp = MapGenerator.generate(5, 6, 6, colors, new DefaultOrderValue());
		
		Algorithms.measureAlgorithm(csp, 1, null);
		Algorithms.measureAlgorithm(csp, 2, null);
		
		csp.setIcv(new MostConstrainedVariable());
		Algorithms.measureAlgorithm(csp, 1, null);
		Algorithms.measureAlgorithm(csp, 2, null);
		
		csp.setIcv(new DefaultOrderVariable());
		MapGenerator.setChooseValue(csp, new MostRepeatedValue());
		Algorithms.measureAlgorithm(csp, 1, null);
		Algorithms.measureAlgorithm(csp, 2, null);
		
		csp.setIcv(new MostConstrainedVariable());
		Algorithms.measureAlgorithm(csp, 1, null);
		Algorithms.measureAlgorithm(csp, 2, null);
		*/
		
		//measureEinstein();
		//measureMap();
	}
	
	public static void measureEinstein() {
		CSP csp = EinsteinRiddleGenerator.generate();
		csp.setIcv(new MostConstrainedVariable());
		Algorithms.measureAlgorithm(csp, 1, "b");
		Algorithms.measureAlgorithm(csp, 2, "f");
	}
	
	public static void measureMap() {
		ArrayList<String> colors = new ArrayList<String>();
		
		colors.add("blue");
		colors.add("green");
		colors.add("red");
		colors.add("yellow");
		
		CSP csp = null;
		
		for(int i = 2; i < 13; i++) {
			csp = MapGenerator.generate(i, 6, 6, colors, new DefaultOrderValue());
			
			Algorithms.measureAlgorithm(csp, 1, "b"+i);
			Algorithms.measureAlgorithm(csp, 2, "f"+i);
			
			csp.setIcv(new MostConstrainedVariable());
			Algorithms.measureAlgorithm(csp, 1, "bmcv"+i);
			Algorithms.measureAlgorithm(csp, 2, "fmcv"+i);
			
			csp.setIcv(new DefaultOrderVariable());
			MapGenerator.setChooseValue(csp, new MostRepeatedValue());
			Algorithms.measureAlgorithm(csp, 1, "bmrv"+i);
			Algorithms.measureAlgorithm(csp, 2, "fmrv"+i);
			
			csp.setIcv(new MostConstrainedVariable());
			Algorithms.measureAlgorithm(csp, 1, "bmcvmrv"+i);
			Algorithms.measureAlgorithm(csp, 2, "fmcvmrv"+i);
		}
	}
}
