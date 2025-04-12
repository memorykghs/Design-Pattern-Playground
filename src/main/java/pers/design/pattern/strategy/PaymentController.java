package pers.design.pattern.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author memorykghs
 * @date 2025/4/10
 */
@RestController
@RequestMapping("/strategy")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public void payment(@RequestBody PaymentDTO paymentDTO) throws Exception {
        paymentService.payment(paymentDTO);
    }
}
