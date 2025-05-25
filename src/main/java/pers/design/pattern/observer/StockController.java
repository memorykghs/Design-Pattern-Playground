package pers.design.pattern.observer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author memorykghs
 * @date 2025/5/1
 */
@RestController
@RequestMapping("/observer/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/notify")
    public String demoObserver() {
        stockService.registerThenNotify();
        return "message update completed";
    }
}
