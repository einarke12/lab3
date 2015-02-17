import aima.core.search.csp.*;

import java.util.LinkedList;
import java.util.List;

public class Main {

	private static CSP setupCSP() {
		CSP csp = null;
//		In five houses, each with a different color, live five persons of different nationality,
//		each of whom prefers a different brand of cigarettes, a different drink, and a different pet.
//		The five houses are arranged in a row (no house has more than 2 neighbors).   
//		# The Englishman lives in the red house.
//		# The Spaniard owns the dog.
//		# Coffee is drunk in the green house.
//		# The Ukrainian drinks tea.
//		# The green house is immediately to the right of the ivory house.
//		# The Old Gold smoker owns snails.
//		# Kools are smoked in the yellow house.
//		# Milk is drunk in the middle house.
//		# The Norwegian lives in the first house.
//		# The man who smokes Chesterfields lives in the house next to the man with the fox.
//		# Kools are smoked in the house next to the house where the horse is kept.
//		# The Lucky Strike smoker drinks orange juice.
//		# The Japanese smokes Parliaments.
//		# The Norwegian lives next to the blue house.
//
//		Now, who drinks water? Who owns the zebra?


		/*String[] colors = {"Red", "Green", "Ivory", "Yellow", "Blue"};
		String[] nations = {"Englishman", "Spaniard", "Norwegian", "Ukrainian", "Japanese"};
		String[] cigarettes = {"Old Gold", "Kools", "Chesterfields", "Lucky Strike", "Parliaments"};
		String[] drink = {"Water", "Orange juice", "Tea", "Coffee", "Milk"};
		String[] pet = {"Zebra", "Dog", "Fox", "Snails", "Horse"};*/

		String[] houseNumbers = {"1", "2", "3", "4", "5"};

		List<Variable> variables = new LinkedList<Variable>();

		// TODO create variables, e.g.,
		Variable Red = new Variable("Red");
		Variable Green = new Variable("Green");
		Variable Ivory = new Variable("Ivory");
		Variable Yellow = new Variable("Yellow");
		Variable Blue = new Variable("Blue");

		Variable Englishman = new Variable("Englishman");
		Variable Spaniard = new Variable("Spaniard");
		Variable Norwegian = new Variable("Norwegian");
		Variable Ukrainian = new Variable("Ukrainian");
		Variable Japanese = new Variable("Japanese");

		Variable OldGold = new Variable("OldGold");
		Variable Kools = new Variable("Kools");
		Variable Chesterfields = new Variable("Chesterfields");
		Variable LuckyStrike = new Variable("LuckyStrike");
		Variable Parliaments = new Variable("Parliaments");

		Variable Water = new Variable("Water");
		Variable OrangeJuice = new Variable("OrangeJuice");
		Variable Tea = new Variable("Tea");
		Variable Coffee = new Variable("Coffee");
		Variable Milk = new Variable("Milk");

		Variable Zebra = new Variable("Zebra");
		Variable Dog = new Variable("Dog");
		Variable Fox = new Variable("Fox");
		Variable Snails = new Variable("Snails");
		Variable Horse = new Variable("Horse");

		// TODO add all your variables to this list, e.g.,
		variables.add(Red);
		variables.add(Green);
		variables.add(Ivory);
		variables.add(Yellow);
		variables.add(Blue);

		variables.add(Englishman);
		variables.add(Spaniard);
		variables.add(Norwegian);
		variables.add(Ukrainian);
		variables.add(Japanese);

		variables.add(OldGold);
		variables.add(Kools);
		variables.add(Chesterfields);
		variables.add(LuckyStrike);
		variables.add(Parliaments);

		variables.add(Water);
		variables.add(OrangeJuice);
		variables.add(Tea);
		variables.add(Coffee);
		variables.add(Milk);

		variables.add(Zebra);
		variables.add(Dog);
		variables.add(Fox);
		variables.add(Snails);
		variables.add(Horse);

		
		csp = new CSP(variables);

		// TODO set domains of variables, e.g.,
		Domain d = new Domain(houseNumbers);
//		# The Englishman lives in the red house.
//		# The Spaniard owns the dog.
//		# Coffee is drunk in the green house.
//		# The Ukrainian drinks tea.
//		# The green house is immediately to the right of the ivory house.
//		# The Old Gold smoker owns snails.
//		# Kools are smoked in the yellow house.
//		# Milk is drunk in the middle house.
//		# The Norwegian lives in the first house.
//		# The man who smokes Chesterfields lives in the house next to the man with the fox.
//		# Kools are smoked in the house next to the house where the horse is kept.
//		# The Lucky Strike smoker drinks orange juice.
//		# The Japanese smokes Parliaments.
//		# The Norwegian lives next to the blue house.

		for(Variable v: variables)
		{
			if(v.getName().equals("Norwegian"))
			{
				csp.setDomain(v, new Domain(new String[] {"1"} ));
			}
			else if(v.getName().equals("Milk"))
			{
				csp.setDomain(v, new Domain(new String[] {"3"} ));
			}
			else
			{
				csp.setDomain(v, d);
			}
		}

		
		// TODO add constraints, e.g.,
		csp.addConstraint(new EqualConstraint(Englishman, Red));
		//csp.addConstraint(new NotEqualConstraint(var1, var2)); // meaning var1 != var2
		//csp.addConstraint(new EqualConstraint(var1, var2)); // meaning var1 == var2
		//csp.addConstraint(new SuccessorConstraint(var1, var2)); // meaning var1 == var2 + 1
		//csp.addConstraint(new DifferByOneConstraint(var1, var2)); // meaning var1 == var2 + 1 or var1 == var2 - 1
		
		return csp;
	}

	private static void printSolution(Assignment solution) {
		// TODO print out useful answer
		// You can use the following to get the value assigned to a variable:
		// Object value = solution.getAssignment(var); 
		System.out.println("solution:" + solution);
	}
	
	/**
	 * runs the CSP backtracking solver with the given parameters and print out some statistics
	 * @param description
	 * @param enableMRV
	 * @param enableDeg
	 * @param enableAC3
	 * @param enableLCV
	 */
	private static void findSolution(String description, boolean enableMRV, boolean enableDeg, boolean enableAC3, boolean enableLCV) {
		CSP csp = setupCSP();

		System.out.println("======================");
		System.out.println("running " + description);
		
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(enableMRV, enableDeg, enableAC3, enableLCV);
		final int nbAssignments[] = {0};
		solver.addCSPStateListener(new CSPStateListener() {
			@Override
			public void stateChanged(Assignment arg0, CSP arg1) {
				nbAssignments[0]++;
			}
			@Override
			public void stateChanged(CSP arg0) {}
		});
		Assignment solution = solver.solve(csp);
		endTime = System.currentTimeMillis();
		System.out.println("runtime " + (endTime-startTime)/1000.0 + "s" + ", number of assignments:" + nbAssignments[0]);
		printSolution(solution);
	}

	/**
	 * main procedure
	 */
	public static void main(String[] args) throws Exception {
		// run solver with different parameters
		findSolution("backtracking + AC3 + variable heuristics + value heuristics", true, true, true, true);
		findSolution("backtracking + AC3 + variable heuristics", true, true, true, false);
		findSolution("backtracking + AC3", false, false, true, false);
		findSolution("backtracking + forward checking + variable heuristics + value heuristics", true, true, false, true);
		findSolution("backtracking + forward checking + variable heuristics", true, true, false, false);
		findSolution("backtracking + forward checking", false, false, false, false);
	}

}
