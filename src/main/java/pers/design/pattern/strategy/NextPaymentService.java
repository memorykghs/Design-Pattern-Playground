package pers.design.pattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author memorykghs
 * @date 2025/4/12
 */
@Service
@Slf4j
public class NextPaymentService implements IPayment {
    @Override
    public void payByCredit(PaymentDTO paymentDTO) {
        log.info("pay by provider: {} and channel: {}", paymentDTO.getProvider(), paymentDTO.getChannel());
    }

    @Override
    public ChannelEnum getChannel() {
        return ChannelEnum.NEXT_BANK_CREDIT_CARD;
    }
}
