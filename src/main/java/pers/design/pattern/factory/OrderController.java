package pers.design.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author memorykghs
 * @date 2025/4/21
 */
@RestController
@Slf4j
public class OrderController {

    private final OrderDtoFactory orderDtoFactory;

    public OrderController(OrderDtoFactory orderDtoFactory) {
        this.orderDtoFactory = orderDtoFactory;
    }

    public void createOrder(@RequestBody OrderReq orderReq) {
        OrderDO orderDO = orderDtoFactory.create(orderReq);
        log.info("=====> orderDO = {}", orderDO);
    }
}
