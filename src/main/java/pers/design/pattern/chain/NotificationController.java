package pers.design.pattern.chain;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public void sendNotification() {
        NotificationDTO notificationDTO = NotificationDTO.builder()
            .userId("86594")
            .build();

        notificationService.send(notificationDTO);
    }
}
