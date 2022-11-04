package ga.problems.knapsack;

import ga.framework.TopKSurvival;
import ga.framework.model.NoSolutionException;
import ga.framework.model.Solution;
import ga.framework.operators.EvolutionException;
import ga.framework.operators.SurvivalException;
import ga.framework.operators.SurvivalOperator;

import java.util.LinkedList;
import java.util.List;

public class ConcreateProblem {

    public static void main(String[] args) throws NoSolutionException, EvolutionException, SurvivalException {
        List<Item> itemList = new LinkedList<>();
        itemList.add(new Item(5, 10));
        itemList.add(new Item(4,8));    //final
        itemList.add(new Item(4,6));
        itemList.add(new Item(4,4));
        itemList.add(new Item(3,7));
        itemList.add(new Item(3,4));

        KnapsackProblem knapsackProblem = new KnapsackProblem(itemList,47);
        KnapsackMutation knapsackMutation = new KnapsackMutation();
        KnapsackFitnessEvaluator knapsackFitnessEvaluator = new KnapsackFitnessEvaluator();
        SurvivalOperator TopKSurvival = new TopKSurvival(4);

        List<Solution> population = new LinkedList<>();
        for(int i=0; i<4; i++){
            population.add(knapsackProblem.createNewSolution());
        }

        List<Solution> evolvePopulation = new LinkedList<>();
        for(int i=0; i<4; i++){
            evolvePopulation.add(knapsackMutation.evolve(evolvePopulation.get(i)));
            knapsackFitnessEvaluator.evaluate(evolvePopulation);
            population = TopKSurvival.selectPopulation(evolvePopulation,4);
            evolvePopulation.clear();
        }

        for (Solution s: population) {
            KnapsackSolution ks = (KnapsackSolution) s;
            System.out.println(ks.currentItem);
        }

    }
}
