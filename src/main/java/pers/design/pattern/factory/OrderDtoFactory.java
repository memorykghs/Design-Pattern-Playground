package pers.design.pattern.factory;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author memorykghs
 * @date 2025/4/21
 */
@Component
public class OrderDtoFactory {
    private final CouponService couponService;

    public OrderDtoFactory(CouponService couponService) {
        this.couponService = couponService;
    }

    public OrderDO create(OrderReq orderReq) {
        BigDecimal discount = couponService.getDiscount(orderReq.getCouponCode());
        return new OrderDO()
            .setProductId(orderReq.getProductId())
            .setQuantity(orderReq.getQuantity())
            .setDiscount(discount);
    }
}
