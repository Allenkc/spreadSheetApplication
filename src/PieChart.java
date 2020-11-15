import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class PieChart implements IObserver, Depictor {

    private Map<String, Double> data;

    public PieChart(Map<String, Double> data) {
        this.data = data;
    }

    public Map<String, Double> getData() {
        return data;
    }

    @Override
    public void draw() {

        double sum = 0;

        for (Map.Entry<String, Double> entry : this.data.entrySet()) {
            sum = sum + entry.getValue();
        }

        for (Map.Entry<String, Double> entry : this.data.entrySet()) {
            System.out.print(entry.getKey());
            double percentage = 100 * entry.getValue() / sum;
            System.out.println(" " + round(percentage, 1) + "%");
        }

    }

    @Override
    public void update(Subject o, Object updateArg) {

        Map<String, Double> updateObj = (HashMap<String, Double>) updateArg;

        for (Map.Entry<String, Double> entry : updateObj.entrySet()) {
            this.data.put(entry.getKey(), entry.getValue());
        }

        draw();

    }

    private static String round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        double result = (double) Math.round(value * scale) / scale;
        return String.valueOf(result);
    }
}
