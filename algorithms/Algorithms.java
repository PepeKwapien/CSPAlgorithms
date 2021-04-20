package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import csp.CSP;
import interfaces.IValue;
import interfaces.IVariable;

public class Algorithms {
	
	private static int incorrect;
	private static int states;
	private static Integer statesBeforeFirst;
	private static long startTime;
	private static long endTime;
	private static long firstTime;
	
	public static ArrayList<CSP> measureAlgorithm(CSP csp, int mode, String path) {
		incorrect = 0;
		states = 0;
		statesBeforeFirst = null;
		startTime = System.currentTimeMillis();
		ArrayList<CSP> solutions = null;
		
		switch(mode) {
		case 1:
			solutions = recurBacktrack(csp, 0);
			System.out.println("BACKTRACK:");
			break;
		case 2:
			solutions = recurForward(csp, 0);
			System.out.println("FORWARD CHECKING:");
			break;
		case 3:
			//solutions = recurAC3(csp, 0);
			System.out.println("AC3:");
			break;
		}
		
		endTime = System.currentTimeMillis();
		
		String message = String.format("States = %d\nIncorrect states = %d\nTotal time = %fs\nFound solutions = %d",
				states, incorrect, (endTime - startTime)/1000F, solutions.size());
		
		if(statesBeforeFirst != null) {
			message += String.format("\nStates before first solution = %d\nTime before first solution = %fs",
					statesBeforeFirst, (firstTime - startTime)/1000F);
		}
		
		System.out.println(message+"\n");
		
		if(path != null) {
			try (PrintWriter out = new PrintWriter(path+".txt")) {
			    out.print(states+";"+incorrect+";"+(endTime - startTime)/1000F+";"+solutions.size());
			    if(!solutions.isEmpty()) {
			    	out.print(";"+statesBeforeFirst+";"+(firstTime - startTime)/1000F);
			    }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return solutions;
	}
	
	public static ArrayList<CSP> backtrackAlgorithm(CSP csp) {
		return measureAlgorithm(csp, 1, null);
	}
	
	private static ArrayList<CSP> recurBacktrack(CSP csp, int depth) {
		ArrayList<CSP> correctSolution = new ArrayList<CSP>();
		IVariable[] previousOrder = new IVariable[csp.getVariables().length];
		for(int i = 0; i < csp.getVariables().length; i++) {
			previousOrder[i] = csp.getVariables()[i];
		}
		csp.sort();
		states++;
		
		if(!csp.isLegit()) {
			incorrect++;
		}
		else if(csp.isFinished()) {
			if(statesBeforeFirst == null) {
				statesBeforeFirst = states;
				firstTime = System.currentTimeMillis();
			}
			correctSolution.add(csp.clone());
		}
		else {
			IVariable firstNotSet = null;
			IValue firstEmpty = null;
			
			for(int i = 0; i < csp.getVariables().length; i++) {
				if(!csp.getVariables()[i].isSet()) {
					firstNotSet = csp.getVariables()[i];
					break;
				}
			}
			
			for(int j = 0; j < firstNotSet.getValues().length; j++) {
				if(firstNotSet.getValues()[j].getValue() == null) {
					firstEmpty = firstNotSet.getValues()[j];
					break;
				}
			}
			
			ArrayList<String> originalOrder = new ArrayList<String>();
			for(String s : firstEmpty.getDomain()) {
				originalOrder.add(s);
			}
			
			firstEmpty.sort(csp.getVariables());
			
			for(int k = 0; k < firstEmpty.getDomain().size(); k++) {
				firstEmpty.set(firstEmpty.getDomain().get(k));
				correctSolution.addAll(recurBacktrack(csp, depth + 1));
			}
			firstEmpty.set(null);
			firstEmpty.setDomain(originalOrder);
		}
		
		for(int i = 0; i < csp.getVariables().length; i++) {
			csp.getVariables()[i] = previousOrder[i];
		}
		
		return correctSolution;
	}
	
	public static ArrayList<CSP> forwardcheckingAlgorithm(CSP csp){
		return measureAlgorithm(csp, 2, null);
	}
	
	private static ArrayList<CSP> recurForward(CSP csp, int depth){
		ArrayList<CSP> correctSolution = new ArrayList<CSP>();
		IVariable[] previousOrder = new IVariable[csp.getVariables().length];
		for(int i = 0; i < csp.getVariables().length; i++) {
			previousOrder[i] = csp.getVariables()[i];
		}
		csp.sort();
		states++;
		
		if(csp.isFinished()) {
			if(statesBeforeFirst == null) {
				statesBeforeFirst = states;
				firstTime = System.currentTimeMillis();
			}
			correctSolution.add(csp.clone());
		}
		else {
			IVariable firstNotSet = null;
			IValue firstEmpty = null;
			ArrayList<IValue> changedvalues = new ArrayList<IValue>();
			ArrayList<ArrayList<String>> oldDomains = new ArrayList<ArrayList<String>>();
			
			for(int i = 0; i < csp.getVariables().length; i++) {
				if(!csp.getVariables()[i].isSet()) {
					if(firstNotSet == null) {
						firstNotSet = csp.getVariables()[i];
					}
					for(int j = 0; j < csp.getVariables()[i].getValues().length; j++) {
						if(csp.getVariables()[i].getValues()[j].getValue() == null) {
							if(firstEmpty == null) {
								firstEmpty = csp.getVariables()[i].getValues()[j];
							}
							
							boolean altered = false;
							ArrayList<String> newDomain = new ArrayList<String>();
							
							for(String value : csp.getVariables()[i].getValues()[j].getDomain()) {
								csp.getVariables()[i].getValues()[j].set(value);
								if(csp.isLegit()) {
									newDomain.add(value);
								}
								else if(!altered) {
									altered = true;
									changedvalues.add(csp.getVariables()[i].getValues()[j]);
									oldDomains.add(csp.getVariables()[i].getValues()[j].getDomain());
								}
							}
							
							csp.getVariables()[i].getValues()[j].set(null);
							
							if(newDomain.isEmpty()) {
								for(int k = 0; k < changedvalues.size(); k++) {
									changedvalues.get(k).setDomain(oldDomains.get(k));
								}
								incorrect++;
								for(int g = 0; g < csp.getVariables().length; g++) {
									csp.getVariables()[g] = previousOrder[g];
								}
								return correctSolution;
							}
							else if(altered) {
								csp.getVariables()[i].getValues()[j].setDomain(newDomain);
							}
						}
					}
				}
			}//for(int i = 0; i < csp.getVariables().length; i++)
			
			ArrayList<String> originalOrder = new ArrayList<String>();
			for(String s : firstEmpty.getDomain()) {
				originalOrder.add(s);
			}
			
			firstEmpty.sort(csp.getVariables());
			
			for(int k = 0; k < firstEmpty.getDomain().size(); k++) {
				firstEmpty.set(firstEmpty.getDomain().get(k));
				correctSolution.addAll(recurForward(csp, depth + 1));
			}
			
			for(int k = 0; k < changedvalues.size(); k++) {
				changedvalues.get(k).setDomain(oldDomains.get(k));
			}
			
			if(!changedvalues.contains(firstEmpty)) {
				firstEmpty.setDomain(originalOrder);
			}
			
			firstEmpty.set(null);
		}
		
		for(int i = 0; i < csp.getVariables().length; i++) {
			csp.getVariables()[i] = previousOrder[i];
		}
		
		return correctSolution;
	}
	
	public static ArrayList<CSP> ac3Algorithm(CSP csp) {
		return measureAlgorithm(csp, 3, null);
	}
	
	private static ArrayList<CSP> recurAC3(CSP csp, int depth){
		return null;
	}
}
