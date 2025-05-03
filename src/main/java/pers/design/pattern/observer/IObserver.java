package pers.design.pattern.observer;

/**
 * @author memorykghs
 * @date 2025/5/1
 */
public interface IObserver {
    void send(String message);

    String getId();
}
