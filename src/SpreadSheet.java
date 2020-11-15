import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class SpreadSheet implements IObserver, Depictor {

    private Map<String, Double> data;

    public SpreadSheet(Map<String, Double> data) {
        this.data = data;
    }

    public Map<String, Double> getData() {
        return data;
    }

    @Override
    public void draw() {

        for (Map.Entry<String, Double> entry : this.data.entrySet()) {
            System.out.print(entry.getKey());
            System.out.println(" " + ((int) (double) entry.getValue()));
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
