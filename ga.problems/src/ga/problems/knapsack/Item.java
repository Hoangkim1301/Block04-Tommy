package ga.problems.knapsack;

public class Item {
    int weight;
    int value;
    //Getter
    public int getWeight() {
        return weight;
    }
    public int getValue() {
        return value;
    }


    //Konstruktor
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "gewicht=" + weight +
                ", value=" + value +
                '}';
    }

    public static void main(String[] args) {
        Item test = new Item(10,15);
        System.out.println(test.toString());
    }


}
