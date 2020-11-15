import java.util.Observer;
import java.util.Vector;

/**
 * modified from java.util.observable
 */

public class Subject {
    private boolean changed = false;
    private Vector<IObserver> obs;


    public Subject() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(IObserver o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
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
        for (int i = 0 ; i <= arrLocal.length-1; i++)
            ((IObserver)arrLocal[i]).update(this, arg);
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }
}
