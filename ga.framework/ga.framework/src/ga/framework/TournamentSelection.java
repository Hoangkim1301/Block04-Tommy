package ga.framework;

import ga.framework.model.Solution;
import ga.framework.operators.SelectionOperator;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements SelectionOperator {
    public int populationSize;

    public TournamentSelection(int populationSize) {
        this.populationSize = populationSize;
    }

    @Override
    public Solution selectParent(List<Solution> candidates) {
        Random random = new Random();
        int upperBound = candidates.size();
        Solution randomCandidate_1 = candidates.get(random.nextInt(upperBound));
        Solution randomCandidate_2 = candidates.get(random.nextInt(upperBound));
        if(randomCandidate_1.getFitness() >= randomCandidate_2.getFitness()){
            return randomCandidate_1;
        }
        return randomCandidate_2;
    }
}
