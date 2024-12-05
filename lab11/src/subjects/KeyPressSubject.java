package subjects;

import observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class KeyPressSubject implements Subject {

    private final List<Observer> observerList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String pressedKey) {
        for (Observer observer : observerList) {
            observer.update(pressedKey);
        }
    }
}
