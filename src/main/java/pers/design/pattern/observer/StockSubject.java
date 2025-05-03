package pers.design.pattern.observer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author memorykghs
 * @date 2025/5/1
 */
@Component
public class StockSubject implements ISubject {
    private final int INIT_CAPACITY = 16;
    private List<IObserver> observers;

    public StockSubject() {
        observers = new ArrayList<>(INIT_CAPACITY);
    }

    @Override
    public boolean subscribe(IObserver observer) {
        return observers.add(observer);
    }

    @Override
    public boolean unsubscribe(IObserver observer) {
        Optional<IObserver> target = observers.stream()
            .filter(e -> e.getId().equals(observer.getId()))
            .findFirst();

        if (target.isEmpty()) {
            return false;
        }

        observers.remove(target.get());
        return true;
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.send(message));
    }
}
