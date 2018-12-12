package Components;

public class Component {

    private String name;
    private double mi;
    private double oi;
    private double pi;
    private int count;
    private double estimation;
    private double deviation;

    public Component(String name, double mi, double oi, double pi, int count, double estimation, double deviation) {
        this.name = name;
        this.mi = mi;
        this.oi = oi;
        this.pi = pi;
        this.count = count;
        this.estimation = estimation;
        this.deviation = deviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getMi() {
        return mi;
    }

    public void setMi(double mi) {
        this.mi = mi;
    }

    public double getOi() {
        return oi;
    }

    public void setOi(double oi) {
        this.oi = oi;
    }

    public double getPi() {
        return pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    public double getEstimation() {
        return estimation;
    }

    public void setEstimation(double estimation) {
        this.estimation = estimation;
    }

    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }
}
