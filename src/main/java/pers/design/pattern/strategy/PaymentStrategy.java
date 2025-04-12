package pers.design.pattern.strategy;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author memorykghs
 * @date 2025/4/12
 */
@Service
@Slf4j
public class PaymentStrategy {

    Map<ChannelEnum, IPayment> serviceMap;

    public PaymentStrategy(List<IPayment> paymentServiceList) {
        serviceMap = paymentServiceList.stream().collect(Collectors.toMap(IPayment::getChannel, Function.identity()));
    }

    public void payment(PaymentDTO paymentDTO) throws Exception {
        // 取得 payment service
        IPayment service =
            serviceMap.get(ChannelEnum.getByChannelAndProvider(paymentDTO.getChannel(), paymentDTO.getProvider()));
        log.info("paymentDTO: {}", paymentDTO);
        if(service == null){
            throw new Exception();
        }
        service.payByCredit(paymentDTO);
    }
}
