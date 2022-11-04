package ga.problems.knapsack;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;

import java.util.*;

public class KnapsackSolution extends Solution {

    Problem problem;
    int maxWeight;
    List<Item> itemSortList = new LinkedList<>();

    List<Item> currentItem = new LinkedList<>(); // the item in the bag or the chosen item
    List<Item> notSelectedItem = new LinkedList<>();
    public KnapsackSolution(KnapsackProblem problem, int target) throws NoSolutionException {
        super(problem);
        this.problem = problem;
        this.maxWeight = target;

        //problem.itemList = filterList(problem.itemList, target);
        //problem.itemList.sort(Comparator.comparing(Item::getWeight).reversed()); // list of item after sorted (Absteigend)
        //this.itemSortList = problem.itemList;

        //add all the sorted item to the itemList
        List<Item> listItem = new LinkedList<>();
        listItem.addAll(problem.itemList.stream().filter(x-> x.getWeight() <= maxWeight).toList());
        notSelectedItem.addAll(listItem);

        //Exception
        if(listItem.isEmpty()){
            throw new NoSolutionException("No item in the list is lighter than target weight");
        }

        Random random = new Random();
        //test each item from big to small(weight
        while(!listItem.isEmpty()){
            int currentIndex = random.nextInt(listItem.size());
            this.currentItem.add(listItem.get(currentIndex));
            maxWeight = maxWeight - listItem.get(currentIndex).weight;

            //remove the item, which was put in the Bag
            listItem.remove(currentIndex);
            notSelectedItem.remove(currentIndex);

            //make a temp list to store all the item after filter with a new maxWeight
            List<Item> templist =new LinkedList<>();
            templist.addAll(filterList(listItem,this.maxWeight));     //to filter all the overweight item

            //clear all the listItem and add the item that lighter than the new problemSize
            listItem.clear();
            listItem.addAll(templist);

            //clear the tempList
            templist.clear();
        }

    }

    //Copy Constructor
    public KnapsackSolution(KnapsackSolution ks){
        super(ks);
        this.problem = ks.problem;
        this.maxWeight = ks.maxWeight;
        currentItem = new LinkedList<>();
        this.currentItem.addAll(ks.currentItem);
        notSelectedItem = new LinkedList<>();
        this.notSelectedItem.addAll(ks.currentItem);
        this.maxWeight = ks.maxWeight;
    }

    public List<Item> filterList(List<Item> list, int maxWeight) {
        List<Item> Itm = list.stream()
                .filter (x -> x.getWeight() <= maxWeight)
                .toList();
        return Itm;
    }


}
