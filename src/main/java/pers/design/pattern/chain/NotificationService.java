package pers.design.pattern.chain;

import org.springframework.stereotype.Service;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
@Service
public class NotificationService {
    private final EmailNotificationHandler emailNotificationHandler;
    private final LineNotificationHandler lineNotificationHandler;
    private final SmsNotificationHandler smsNotificationHandler;

    public NotificationService(EmailNotificationHandler emailNotificationHandler,
        LineNotificationHandler lineNotificationHandler,
        SmsNotificationHandler smsNotificationHandler) {
        this.emailNotificationHandler = emailNotificationHandler;
        this.lineNotificationHandler = lineNotificationHandler;
        this.smsNotificationHandler = smsNotificationHandler;
    }

    public void send(NotificationDTO notificationDTO){
        Handler handler = createChain();
        handler.handle(notificationDTO);
    }

    private Handler createChain() {
        emailNotificationHandler.setNextHandler(lineNotificationHandler)
            .setNextHandler(smsNotificationHandler);

        return emailNotificationHandler;
    }
}
