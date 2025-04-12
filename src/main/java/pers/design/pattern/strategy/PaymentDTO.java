package pers.design.pattern.strategy;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author memorykghs
 * @date 2025/4/10
 */
@Data
@Accessors(chain = true)
public class PaymentDTO {

    private String userId;

    private BigDecimal amount;

    private String channel;

    private String provider;
}
