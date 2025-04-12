package pers.design.pattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author memorykghs
 * @date 2025/4/10
 */
@Service
@Slf4j
public class PaymentService {

    private final PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void payment(PaymentDTO paymentDTO) throws Exception {
//        String channel = paymentDTO.getChannel();
//        String provider = paymentDTO.getProvider();
//        ChannelEnum channelEnum = ChannelEnum.getByChannelAndProvider(channel, provider);
//
//        switch (channelEnum) {
//            case CATHAY_CREDIT_CARD:
//                cathayPaymentService.payByCredit(paymentDTO);
//                break;
//            case LINE_BANK_CREDIT_CARD:
//                lineBankPaymentService.payByCredit(paymentDTO);
//                break;
//        }
        paymentStrategy.payment(paymentDTO);
    }
}
