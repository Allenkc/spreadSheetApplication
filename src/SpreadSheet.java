import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class SpreadSheet implements Observer , Depictor {

    private Map<String , Double> data;

    @Override
    public void draw() {

    }

    @Override
    public void change(String item, double value) {

    }

    @Override
    public void update(Observable o, Object data) {

        this.setData((Map) data);

    }

    public void setData(Map<String, Double> data) {
        this.data = data;
    }
}
