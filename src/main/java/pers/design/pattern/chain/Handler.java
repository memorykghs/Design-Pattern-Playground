package pers.design.pattern.chain;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
public interface Handler {
    Handler setNextHandler(Handler handler);

    void handle(NotificationDTO notificationDTO);

    boolean canHandle(NotificationDTO notificationDTO);
}
