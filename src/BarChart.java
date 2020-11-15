import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class BarChart implements Observer, Depictor{

    private Map<String , Double> data;

    @Override
    public void draw() {

    }

    @Override
    public void change(String item, double value) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
