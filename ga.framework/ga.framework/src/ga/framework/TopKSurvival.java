package ga.framework;

import ga.framework.model.Solution;
import ga.framework.operators.SurvivalException;
import ga.framework.operators.SurvivalOperator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopKSurvival implements SurvivalOperator {
    int size_K;

    public TopKSurvival(int size_K) {
        this.size_K = size_K;
    }

    public List<Solution> selectPopulation(List<Solution> candidates, int populationSize) throws SurvivalException{
        //Case 1: if k> candidates.size
        if(size_K>populationSize)
            throw new SurvivalException("size k is greater than candidates size");
        //Case 2: if k <= candidates.size

        int different = populationSize - size_K;    //find the different between 2 size
        candidates.stream()
                .sorted(Comparator.comparing(Solution::getFitness).reversed())
                .collect(Collectors.toList());  //Sort the candidates using stream sorted method, compare each Fitness
        List<Solution> solutionNew = new ArrayList<>();
        if(different==0) {
            return candidates;
        }else{
            solutionNew.addAll(candidates);
            for (int i=size_K; i<populationSize; i++){
                solutionNew.add(candidates.get(i));
            }
            return solutionNew;
        }
    }


}
