package Method;

import Components.Component;

import java.util.List;

public class Pert {
    public static double calculateEstimation(double mi, double oi, double pi) {
        return (pi + 4 * mi + oi) / 6;
    }

    public static double calculateDeviation(double oi, double pi) {
        return (pi - oi) / 6;
    }

    public static double calculateTotalEstimation(List<Component> components){
        double estimation=0;
        double deviation=0;
        for (Component component:components){
            estimation+=component.getEstimation()*component.getCount();
            deviation+=component.getDeviation()*component.getDeviation()*component.getCount();
        }
        return estimation + 2*Math.sqrt(deviation);
    }
}
