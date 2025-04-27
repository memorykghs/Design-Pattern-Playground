package pers.design.pattern.factory;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author memorykghs
 * @date 2025/4/21
 */
@Data
@Accessors(chain = true)
public class OrderDO {
    private String productId;
    private int quantity;
    private BigDecimal discount;
}
