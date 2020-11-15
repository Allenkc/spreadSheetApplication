import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * copy some of features from java.util.observable
 */
public class ApplicationData {

    private Map<String, Double> data;
    private Vector<IObserver> obs;
    private boolean changed = false;

    public ApplicationData() {
        obs = new Vector<>();
    }

    public ApplicationData(Map<String, Double> data) {
        this.data = data;
        obs = new Vector<>();
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void addData(String item, double value) {

        this.data.put(item, value);

    }

    public void changeData(String item, double value) {
        setChanged();
        Map<String, Double> updateObj = new HashMap<>();
        updateObj.put(item, value);
        notifyObservers(updateObj);
    }

    public void notifyObservers(Object arg) {

        Object[] arrLocal;

        synchronized (this) {

            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        // original java.util.observable 's order was reverse
        for (int i = 0; i <= arrLocal.length - 1; i++)
            ((IObserver) arrLocal[i]).update(this, arg);
    }

    public synchronized void registerObserver(IObserver o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

}
