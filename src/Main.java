import aima.core.search.csp.*;

import java.util.ArrayList;
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


		String[] colors = {"Red", "Green", "Ivory", "Yellow", "Blue"};
		String[] nations = {"Englishman", "Spaniard", "Norwegian", "Ukrainian", "Japanese"};
		String[] cigarettes = {"Old Gold", "Kools", "Chesterfields", "Lucky Strike", "Parliaments"};
		String[] drink = {"Water", "Orange juice", "Tea", "Coffee", "Milk"};
		String[] pet = {"Zebra", "Dog", "Fox", "Snails", "Horse"};

		Integer[] houseNumbers = {1, 2, 3, 4, 5};

		// TODO add all your variables to this list, e.g.,
		List<Variable> colorList = new ArrayList<Variable>();
		List<Variable> nationList = new ArrayList<Variable>();
		List<Variable> cigarettList = new ArrayList<Variable>();
		List<Variable> drinkList = new ArrayList<Variable>();
		List<Variable> petList = new ArrayList<Variable>();

		for (int i = 0; i < colors.length; i++)
		{
			colorList.add(new Variable(colors[i]));
			nationList.add(new Variable(nations[i]));
			cigarettList.add(new Variable(cigarettes[i]));
			drinkList.add(new Variable(drink[i]));
			petList.add(new Variable(pet[i]));
		}

		List<Variable> variables = new ArrayList<Variable>();

		for (int i = 0; i < colors.length; i++)
		{
			for (int j = 0; j < colors.length; j++)
			{
				if (i == 0)
					variables.add(colorList.get(j));
				else if (i == 1)
					variables.add(nationList.get(j));
				else if (i == 2)
					variables.add(cigarettList.get(j));
				else if (i == 3)
					variables.add(drinkList.get(j));
				else
					variables.add(petList.get(j));
			}
		}
		
		csp = new CSP(variables);

		// TODO set domains of variables, e.g.,
		Domain d = new Domain(houseNumbers);

		for(Variable v: variables)
		{
			if(v.getName().equals("Norwegian"))
			{
				csp.setDomain(v, new Domain(new Integer[] {1} ));
			}
			else if(v.getName().equals("Milk"))
			{
				csp.setDomain(v, new Domain(new Integer[] {3} ));
			}
			else
			{
				csp.setDomain(v, d);
			}
		}

//		# --The Englishman lives in the red house.
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
		// TODO add constraints, e.g.,

		/*
		String[] colors = {"Red", "Green", "Ivory", "Yellow", "Blue"};
		String[] nations = {"Englishman", "Spaniard", "Norwegian", "Ukrainian", "Japanese"};
		String[] cigarettes = {"Old Gold", "Kools", "Chesterfields", "Lucky Strike", "Parliaments"};
		String[] drink = {"Water", "Orange juice", "Tea", "Coffee", "Milk"};
		String[] pet = {"Zebra", "Dog", "Fox", "Snails", "Horse"};
		 */

		csp.addConstraint(new EqualConstraint(nationList.get(0), colorList.get(0))); // meaning var1 == var2
		csp.addConstraint(new EqualConstraint(nationList.get(0), petList.get(1))); // meaning var1 == var2
		csp.addConstraint(new EqualConstraint(drinkList.get(3), colorList.get(1))); // meaning var1 == var2
		csp.addConstraint(new EqualConstraint(nationList.get(3), drinkList.get(2))); // meaning var1 == var2
		csp.addConstraint(new EqualConstraint(drinkList.get(3), colorList.get(1))); // meaning var1 == var2
		csp.addConstraint(new SuccessorConstraint(colorList.get(1), colorList.get(2))); // meaning var1 == var2 + 1
		csp.addConstraint(new EqualConstraint(cigarettList.get(0), petList.get(3))); // meaning var1 == var2
		csp.addConstraint(new EqualConstraint(cigarettList.get(1), colorList.get(3))); // meaning var1 == var2

		csp.addConstraint(new DifferByOneConstraint(cigarettList.get(2), petList.get(2))); // meaning var1 == var2 + 1 or var1 == var2 - 1
		csp.addConstraint(new DifferByOneConstraint(cigarettList.get(1), petList.get(4))); // meaning var1 == var2 + 1 or var1 == var2 - 1
		csp.addConstraint(new EqualConstraint(cigarettList.get(3), drinkList.get(1))); // meaning var1 == var2
		csp.addConstraint(new EqualConstraint(nationList.get(4), cigarettList.get(4))); // meaning var1 == var2
		csp.addConstraint(new DifferByOneConstraint(nationList.get(2), colorList.get(4))); // meaning var1 == var2 + 1 or var1 == var2 - 1


		for (int i = 0; i < colorList.size(); i++)
		{
			for (int j = 0; j < colorList.size(); j++)
			{
				if (i == j)
				{
					// Do nothing
				}
				else
				{
					csp.addConstraint(new NotEqualConstraint(colorList.get(i), colorList.get(j)));
					csp.addConstraint(new NotEqualConstraint(nationList.get(i), nationList.get(j)));
					csp.addConstraint(new NotEqualConstraint(cigarettList.get(i), cigarettList.get(j)));
					csp.addConstraint(new NotEqualConstraint(drinkList.get(i), drinkList.get(j)));
					csp.addConstraint(new NotEqualConstraint(petList.get(i), petList.get(j)));
				}
			}
		}

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
