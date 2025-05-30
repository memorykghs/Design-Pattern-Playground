package pers.design.pattern.chain;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
@Slf4j
@Component
public class SmsNotificationHandler extends NotificationHandler {

    @Override
    public boolean canHandle(NotificationDTO notificationDTO) {
        String userId = notificationDTO.getUserId();
        if (StringUtils.isNotBlank(notificationDTO.getMobile())) {
            log.info("user {} has mobile number", userId);
            return true;
        }
        log.info("user {} doesn't have mobile number", userId);
        return false;
    }

    @Override
    protected void send(NotificationDTO notificationDTO) {
        log.info("send sms with: {}", notificationDTO.getMobile());
    }
}
