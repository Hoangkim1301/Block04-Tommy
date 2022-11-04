package ga.problems.knapsack;
import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;

import java.util.List;

public class KnapsackProblem implements Problem {
    int target; //Bag max weight
    List<Item> itemList;    //List of item to chose

    KnapsackProblem(List<Item> itemList, int target){
        this.target = target;
        this.itemList = itemList;
    }
    public Solution createNewSolution() throws NoSolutionException{
        return new KnapsackSolution(this, target);
    }

}
