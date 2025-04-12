package pers.design.pattern.chain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
@Data
@Builder
public class NotificationDTO {
    private String userId;
    private String mobile;
    private String address;
    private String email;
}
