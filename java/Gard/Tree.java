package Gard;

public class Tree {
    private String name;
    private double high;

    public Tree(String name, double high) {
        this.name = name;
        this.high = high;
    }

    public String getName() {
        return name;
    }

    public double getHigh() {
        return high;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                ", high=" + high +
                '}';
    }
}
