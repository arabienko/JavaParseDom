package Gard;

public class Bush {
    private String name;
    private double high;

    public Bush(String name, double high) {
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
        return "Bush{" +
                "name='" + name + '\'' +
                ", high=" + high +
                '}';
    }
}
