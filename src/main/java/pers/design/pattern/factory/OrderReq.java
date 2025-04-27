package pers.design.pattern.factory;

import lombok.Data;

/**
 * @author memorykghs
 * @date 2025/4/21
 */
@Data
public class OrderReq {
    private String productId;
    private int quantity;
    private String couponCode;
}
