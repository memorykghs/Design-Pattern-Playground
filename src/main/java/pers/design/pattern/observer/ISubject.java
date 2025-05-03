package pers.design.pattern.observer;

/**
 * @author memorykghs
 * @date 2025/5/1
 */
public interface ISubject {

    boolean subscribe(IObserver uuid);

    boolean unsubscribe(IObserver uuid);

    void notifyObservers(String message);
}
