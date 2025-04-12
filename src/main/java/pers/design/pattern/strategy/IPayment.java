package pers.design.pattern.strategy;

/**
 * @author memorykghs
 * @date 2025/4/12
 */
public interface IPayment {
    void payByCredit(PaymentDTO paymentDTO);

    ChannelEnum getChannel();
}
