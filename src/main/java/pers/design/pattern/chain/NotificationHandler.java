package pers.design.pattern.chain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
@Slf4j
@Component
public abstract class NotificationHandler implements Handler {
    private Handler nextHandler;

    @Override
    public Handler setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    @Override
    public void handle(NotificationDTO notificationDTO) {
        if (canHandle(notificationDTO)) {
            send(notificationDTO);
        } //else {
            next(notificationDTO);
        //}
    }

    protected void next(NotificationDTO notificationDTO) {
        if (nextHandler == null) {
            log.warn("End of chain reached, notification not handled: {}", notificationDTO);
            return;
        }
        nextHandler.handle(notificationDTO);
    }

    protected abstract void send(NotificationDTO notificationDTO);
}
