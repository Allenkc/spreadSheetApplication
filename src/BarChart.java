import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class BarChart implements IObserver, Depictor {

    private Map<String, Double> data;

    public BarChart(Map<String, Double> data) {
        this.data = data;
    }

    public Map<String, Double> getData() {
        return data;
    }

    @Override
    public void draw() {

        for (Map.Entry<String, Double> entry : this.data.entrySet()) {
            for (int i = 1; i <= entry.getValue(); i++) {
                System.out.print("=");
            }
            System.out.println(" " + entry.getKey());
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
}
