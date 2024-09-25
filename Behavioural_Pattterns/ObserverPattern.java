import java.util.ArrayList;
import java.util.List;

class SubjectEntity{
    private int NumberState;
    private List<ObserverEntity> lm = new ArrayList<ObserverEntity>();

    public int getNumberState() {
        return NumberState;
    }

    public void setNumberState(int n) {
        NumberState = n;
        notifyAllObservers();
    }

    public void subscribeObserver(ObserverEntity e){
        lm.add(e);
    }

    public void unSubscribeObserver(ObserverEntity e){
        lm.remove(e);
    }

    public void notifyAllObservers(){
        for(ObserverEntity l : lm){
            l.update();
        }
    }
    
}

abstract class ObserverEntity{
    SubjectEntity se;
    abstract public void update();
}

class BinaryObserverEntity extends ObserverEntity{

    public BinaryObserverEntity(SubjectEntity e) {
        se = e;
    }

    @Override
    public void update() {
        int n = se.getNumberState();
        System.out.println("BinaryObserver represents number : " + n + " to Binary : " + Integer.toBinaryString(n));
    }

}

class HexObserverEntity extends ObserverEntity{

    public HexObserverEntity(SubjectEntity e) {
        se = e;
    }

    @Override
    public void update() {
        int n = se.getNumberState();
        System.out.println("BinaryObserver represents number : " + n + " to Hex : " + Integer.toHexString(n));
    }

}

public class ObserverPattern {
    public static void main(String[] args) {
        SubjectEntity se = new SubjectEntity();
        ObserverEntity o1 = new BinaryObserverEntity(se);
        ObserverEntity o2 = new HexObserverEntity(se);

        se.subscribeObserver(o1);
        se.setNumberState(8);

        se.subscribeObserver(o2);
        se.setNumberState(100);
    }
}