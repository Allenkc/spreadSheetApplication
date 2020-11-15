import java.util.Map;
import java.util.Observable;

public class ApplicationData extends Observable {

    private Map<String , Double> data;

    public ApplicationData() {
    }

    public ApplicationData(Map<String, Double> data) {
        this.data = data;
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void addData(String item , double value){
        this.data.put(item, this.getData().get(item) + value);
        setChanged();
        notifyObservers();
    }
}
