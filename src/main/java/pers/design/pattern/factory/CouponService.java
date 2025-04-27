package pers.design.pattern.factory;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author memorykghs
 * @date 2025/4/21
 */
@Service
public class CouponService {

    private static final Map<String, BigDecimal> COUPON_DATABASE = Map.of(
        "beauty001", new BigDecimal("0.75"),
        "health001", new BigDecimal("0.8")
    );

    public BigDecimal getDiscount(String couponId){
        return COUPON_DATABASE.get(couponId);
    }
}
