package einstein_riddle;

import choose_variable.DefaultOrderVariable;
import coloring_map.AdjacentAreasConstraint;
import csp.CSP;
import einstein_riddle_constraints.DanishTea;
import einstein_riddle_constraints.EinsteinUnique;
import einstein_riddle_constraints.EnglishRed;
import einstein_riddle_constraints.GermanPipe;
import einstein_riddle_constraints.GreenCoffee;
import einstein_riddle_constraints.GreenLeftWhite;
import einstein_riddle_constraints.HorseNextYellow;
import einstein_riddle_constraints.LightNextCat;
import einstein_riddle_constraints.LightNextWater;
import einstein_riddle_constraints.MentholBeer;
import einstein_riddle_constraints.MiddleMilk;
import einstein_riddle_constraints.NorwegianFirst;
import einstein_riddle_constraints.NorwegianNextBlue;
import einstein_riddle_constraints.SwedishDog;
import einstein_riddle_constraints.UnfilteredBird;
import einstein_riddle_constraints.YellowCigar;
import interfaces.IChooseValue;
import interfaces.IConstraint;

public class EinsteinRiddleGenerator {
	
	public static CSP generate() {
		House[] houses = new House[5];
		IConstraint[] c = {new DanishTea(), new EinsteinUnique(), new EnglishRed(), new GermanPipe(), new GreenCoffee(),
				new GreenLeftWhite(), new HorseNextYellow(), new LightNextCat(), new LightNextWater(), new MentholBeer(),
				new MiddleMilk(), new NorwegianFirst(), new NorwegianNextBlue(), new SwedishDog(), new UnfilteredBird(),
				new YellowCigar()};
		
		for(int i = 1; i < 6; i++) {
			houses[i-1] = new House(i);
		}
		
		return new CSP(houses, c, new DefaultOrderVariable());
	}
	
	public static CSP generate(IChooseValue icv) {
		House[] houses = new House[5];
		IConstraint[] c = {new DanishTea(), new EinsteinUnique(), new EnglishRed(), new GermanPipe(), new GreenCoffee(),
				new GreenLeftWhite(), new HorseNextYellow(), new LightNextCat(), new LightNextWater(), new MentholBeer(),
				new MiddleMilk(), new NorwegianFirst(), new NorwegianNextBlue(), new SwedishDog(), new UnfilteredBird(),
				new YellowCigar()};
		
		for(int i = 1; i < 6; i++) {
			houses[i-1] = new House(i, icv);
		}
		
		return new CSP(houses, c, new DefaultOrderVariable());
	}
	
	public static CSP wikiCorrect() {
		House[] houses = new House[5];
		IConstraint[] c = {new DanishTea(), new EinsteinUnique(), new EnglishRed(), new GermanPipe(), new GreenCoffee(),
				new GreenLeftWhite(), new HorseNextYellow(), new LightNextCat(), new LightNextWater(), new MentholBeer(),
				new MiddleMilk(), new NorwegianFirst(), new NorwegianNextBlue(), new SwedishDog(), new UnfilteredBird(),
				new YellowCigar()};
		
		for(int i = 1; i < 6; i++) {
			houses[i-1] = new House(i);
		}
		
		houses[0].getValues()[0].set("yellow");
		houses[0].getValues()[1].set("norwegian");
		houses[0].getValues()[2].set("cat");
		houses[0].getValues()[3].set("water");
		houses[0].getValues()[4].set("cigar");
		
		houses[1].getValues()[0].set("blue");
		houses[1].getValues()[1].set("danish");
		houses[1].getValues()[2].set("horse");
		houses[1].getValues()[3].set("tea");
		houses[1].getValues()[4].set("light");
		
		houses[2].getValues()[0].set("red");
		houses[2].getValues()[1].set("english");
		houses[2].getValues()[2].set("bird");
		houses[2].getValues()[3].set("milk");
		houses[2].getValues()[4].set("unfiltered");
		
		houses[3].getValues()[0].set("green");
		houses[3].getValues()[1].set("german");
		houses[3].getValues()[2].set("fish");
		houses[3].getValues()[3].set("coffee");
		houses[3].getValues()[4].set("pipe");
		
		houses[4].getValues()[0].set("white");
		houses[4].getValues()[1].set("swedish");
		houses[4].getValues()[2].set("dog");
		houses[4].getValues()[3].set("beer");
		houses[4].getValues()[4].set("menthol");
		
		return new CSP(houses, c, new DefaultOrderVariable());
	}
}
