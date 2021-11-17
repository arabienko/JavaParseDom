package Gard;

public class Facilities {
    private String bench;
    private String lantern;

    public Facilities(String bench, String lantern) {
        this.bench = bench;
        this.lantern = lantern;
    }

    public String getBench() {
        return bench;
    }

    public String getLantern() {
        return lantern;
    }

    @Override
    public String toString() {
        return "Facilities{" +
                "bench='" + bench + '\'' +
                ", lantern='" + lantern + '\'' +
                '}';
    }
}
