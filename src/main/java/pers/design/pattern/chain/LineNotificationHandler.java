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
public class LineNotificationHandler extends NotificationHandler {

    private final LineBindingDao lineBindingDao;

    public LineNotificationHandler(LineBindingDao lineBindingDao) {
        this.lineBindingDao = lineBindingDao;
    }

    @Override
    public boolean canHandle(NotificationDTO notificationDTO) {
        String userId = notificationDTO.getUserId();
        if (StringUtils.isNotBlank(notificationDTO.getMobile()) && StringUtils.isNotBlank(
            lineBindingDao.getLineBindingUUID(userId))) {
            log.info("user {} has bind Line", userId);
            return true;
        }
        log.info("user {} doesn't have bind Line", userId);
        return false;
    }

    @Override
    protected void send(NotificationDTO notificationDTO) {
        log.info("send line with: {}", notificationDTO.getMobile());
    }
}
