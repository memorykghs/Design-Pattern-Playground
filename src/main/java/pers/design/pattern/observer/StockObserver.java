package pers.design.pattern.observer;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author memorykghs
 * @date 2025/5/1
 */
@Slf4j
public class StockObserver implements IObserver {

    private final String uuid;

    public StockObserver(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public void send(String message) {
        log.info("{} new message: {}", LocalDateTime.now(), message);
    }

    @Override
    public String getId() {
        return uuid;
    }
}
