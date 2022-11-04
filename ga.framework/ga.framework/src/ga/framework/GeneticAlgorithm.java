package ga.framework;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm implements Fluent.Solve,
        Fluent.WithPopulationOfSize,
        Fluent.EvolvingSolutionsWith,
        Fluent.EvaluatingSolutionsBy,
        Fluent.PerformingSelectionWith,
Fluent.StoppingAtEvolution,Fluent.RunOptimization{
    Problem problem;
    int populationSize;
    List<EvolutionaryOperator> evolutionaryOperators = new LinkedList<>();
    FitnessEvaluator fitnessEvaluator;
    SelectionOperator selectionOperator;
    SurvivalOperator survivalOperator;
    int evolutionStep;

    GeneticAlgorithm(){

    }

    @Override
    public Fluent.WithPopulationOfSize solve(Problem problem) {
        this.problem = problem;
        return this;
    }

    @Override
    public Fluent.EvolvingSolutionsWith withPopulationOfSize(int i) {
        this.populationSize=i;
        return this;
    }

    @Override
    public Fluent.EvaluatingSolutionsBy evaluatingSolutionsWith(EvolutionaryOperator evolutionaryOperator) {
        this.evolutionaryOperators.add(evolutionaryOperator);
        return this;
    }

    @Override
    public Fluent.PerformingSelectionWith evaluatingSolutionsBy(FitnessEvaluator fitnessEvaluator) {
        this.fitnessEvaluator = fitnessEvaluator;
        return this;
    }

    @Override
    public Fluent.StoppingAtEvolution performingSelectionWith(SelectionOperator selectionOperator) {
        this.selectionOperator = selectionOperator;
        return this;
    }

    @Override
    public Fluent.RunOptimization stoppingAtEvolution(int i) {
        this.evolutionStep = i;
        return this;
    }


    public static class Builder{
        Problem problem;
        int populationSize;
        List<EvolutionaryOperator> evolutionaryOperators = new LinkedList<>();
        FitnessEvaluator fitnessEvaluator;
        SelectionOperator selectionOperator;
        SurvivalOperator survivalOperator;
        int evolutionStep;
    }

    public GeneticAlgorithm(Problem problem,
                            int populationSize,
                            List<EvolutionaryOperator> evolutionaryOperators,
                            FitnessEvaluator fitnessEvaluator,
                            SurvivalOperator survivalOperator,
                            int evolutionStep) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.evolutionaryOperators = evolutionaryOperators;
        this.fitnessEvaluator = fitnessEvaluator;
        this.survivalOperator = survivalOperator;
        this.evolutionStep = evolutionStep;
    }

    public List<Solution> runOptimazation() throws EvolutionException, SurvivalException, NoSolutionException {
        List<Solution> startPopulation = new LinkedList<>();
        //Start Population
        for(int i=0; i< populationSize; i++){
            startPopulation.add(problem.createNewSolution());
        }

        //evaluate
        fitnessEvaluator.evaluate(startPopulation);

        int j=0;
        while(j < evolutionStep){
            //chose a random evolution operator
            Random rand = new Random(); //instance of a random class
            int upperBound = evolutionaryOperators.size();  //size of evolution operators
            EvolutionaryOperator randEvolutionaryOperator = evolutionaryOperators.get(rand.nextInt(upperBound));

            //each object in startPolution will evolve with evolution operator
            for (Solution sol : startPopulation) {
                Solution solution = randEvolutionaryOperator.evolve(selectionOperator.selectParent(startPopulation));       //Aufgabe 1.3
                startPopulation.add(solution);
                populationSize++;
            }
            //evaluate new solution
            fitnessEvaluator.evaluate(startPopulation);

            //survival
            survivalOperator.selectPopulation(startPopulation,populationSize);
        }
        return startPopulation;
    }




    public static void main(String[] args) throws SurvivalException, EvolutionException, NoSolutionException {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        //Problem yourProblem = new Problem(c);
        List<Solution> result = ga.solve(null).withPopulationOfSize(10)
                .evaluatingSolutionsWith(null)
                .evaluatingSolutionsBy(null)
                .performingSelectionWith(null)
                .stoppingAtEvolution(100)
                .runOptimazation();
        //List<Solution> result2 = ga.solve(null).withPopulationOfSize().evaluatingSolutionsWith().evaluatingSolutionsBy()
    }

}
