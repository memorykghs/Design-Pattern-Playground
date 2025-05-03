package pers.design.pattern.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author memorykghs
 * @date 2025/5/1
 */
@Service
public class StockService {
    @Autowired
    private StockSubject stockSubject;

    @Autowired
    private UuidGenerator uuidGenerator;

    public void registerThenNotify(){
        // 建立兩個觀察者
        StockObserver observer1 = new StockObserver(uuidGenerator.genUuid());
        StockObserver observer2 = new StockObserver(uuidGenerator.genUuid());

        // 訂閱
        stockSubject.subscribe(observer1);
        stockSubject.subscribe(observer2);

        // 發送更新通知
        stockSubject.notifyObservers("股票價格更新：TSMC 漲停！");
    }
}
