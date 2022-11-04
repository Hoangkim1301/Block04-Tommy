package ga.framework;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.*;

import java.util.List;

public interface Fluent {

    interface Solve{
        WithPopulationOfSize solve(Problem problem);
    }

    interface WithPopulationOfSize{
        EvolvingSolutionsWith withPopulationOfSize(int i);
    }

    interface EvolvingSolutionsWith{
        //EvolvingSolutionsWith evaluatingSolutionsWith(EvolutionaryOperator evolutionaryOperator)
        EvaluatingSolutionsBy evaluatingSolutionsWith(EvolutionaryOperator evolutionaryOperator);
    }

    interface EvaluatingSolutionsBy{
        PerformingSelectionWith evaluatingSolutionsBy(FitnessEvaluator fitnessEvaluator);
    }
    interface PerformingSelectionWith{
        StoppingAtEvolution performingSelectionWith(SelectionOperator selectionOperator);
    }

    interface StoppingAtEvolution {
        RunOptimization stoppingAtEvolution(int i);
    }

    interface RunOptimization{
        List<Solution> runOptimazation() throws EvolutionException, SurvivalException, NoSolutionException;
    }
}
