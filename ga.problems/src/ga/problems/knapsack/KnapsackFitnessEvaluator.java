package ga.problems.knapsack;

import ga.framework.model.Solution;
import ga.framework.operators.FitnessEvaluator;

import java.util.List;

public class KnapsackFitnessEvaluator implements FitnessEvaluator {

    @Override
    public void evaluate(List<Solution> population) {
        for (Solution sol: population) {
            //cast sol to knapsackSolution because KnapsackSolution extends Solution
            KnapsackSolution knapsackSolution = (KnapsackSolution) sol;
            // calculate the value of current item which was stored in the list
            knapsackSolution.setFitness(knapsackSolution.currentItem.stream().mapToInt(Item::getValue).sum());

        }
    }


}
