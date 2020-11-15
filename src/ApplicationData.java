import java.util.HashMap;
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
        if(null != this.getData().get(item)){
            this.data.put(item, this.getData().get(item) + value);
        }else {
            this.data.put(item , value);
        }
    }

    public void changeData(String item , double value){
        this.addData(item , value);
        setChanged();
        Map<String , Double> updateObj = new HashMap<>();
        updateObj.put(item , value);
        notifyObservers(updateObj);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);

    }
}
