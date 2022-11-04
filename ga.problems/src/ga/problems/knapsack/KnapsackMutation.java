package ga.problems.knapsack;

import ga.framework.model.Solution;
import ga.framework.operators.EvolutionException;
import ga.framework.operators.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class KnapsackMutation implements EvolutionaryOperator{

    @Override
    public Solution evolve(Solution solution) throws EvolutionException {
        //Random random = new Random();
        Solution sol = solution;
        KnapsackSolution ks = new KnapsackSolution((KnapsackSolution) sol);
        List<Item> list = ks.filterList(ks.notSelectedItem, ks.maxWeight);        //filter the not selected item, so that we can later add a new item from notSelectedItem to the Bag

        Random random = new Random();
        if(ks.currentItem.size()>0 && !list.isEmpty()){
            if(random.nextBoolean()) {
                sol = new MutationRemove(ks).remove();
            }else
            sol = new MutationAdd(ks).add();
        }else if(ks.currentItem.size()>0 && list.isEmpty()){
            throw new EvolutionException("can not add Item");
        }else if(ks.currentItem.size()<1 && !list.isEmpty()){
            sol = new MutationAdd(ks).add();
        }else {
            throw new EvolutionException("Population not mutate");
        }
        return sol;
    }

    static class MutationRemove {
        KnapsackSolution ks;
        MutationRemove(KnapsackSolution ks){
            this.ks = new KnapsackSolution(ks); //Copy
        }

        public KnapsackSolution remove(){
            Random rd = new Random();
            int currentIndex = rd.nextInt(ks.currentItem.size());
            ks.maxWeight += ks.currentItem.get(currentIndex).weight;
            ks.notSelectedItem.add(ks.currentItem.remove(currentIndex));
            return ks;
        }
    }

    static class MutationAdd{
        KnapsackSolution ks;
        MutationAdd(KnapsackSolution ks){
            this.ks = new KnapsackSolution(ks); //copy
        }

        public KnapsackSolution add() {
            Random rd = new Random();
            int currentIndex = rd.nextInt(ks.notSelectedItem.size());
            ks.maxWeight -= ks.notSelectedItem.get(currentIndex).weight;
            ks.currentItem.add(ks.notSelectedItem.remove(currentIndex));
            return ks;
        }

    }

}
