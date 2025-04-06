# 16 - 觀察者模式 ( Observer )

## Observer Pattern 
又稱發布-訂閱 ( Publish/Subscribe ) 模式。定義一種一對多的依賴關係，一個主題對象可被多個觀察者對象同時監聽，使得每當主題對象狀態變化時，所有依賴於它的對象都會得到通知並被自動更新。

![](/images/observer-1.png)

觀察者模式的核心：將觀察者與被觀察者解耦。

###### 適用場景
* 當一個抽象模型包含兩個方面內容，其中一個方面依賴於另一個方面。
* 其他一個或多個對象的變化依賴於另一個對象的變化。
* 實現類似廣播機制的功能，無需知道具體收聽者，只需分發廣播，系統中感興趣的對象會自動接收該廣播。
* 多層級嵌套使用，形成一種鏈式觸發機制，使得事件具備跨域 ( 跨越兩種觀察者類型 ) 通知。

## UML
![](/images/observer-2.png)

## 實際應用

## 實作

![](/images/observer-3.png)

下面用在手機與電腦上新增地震通知作為範例，Observable 就是當地震發生時，由國家統一發送的簡訊通知；而 Observer 則是個人的手機或是電腦。

###### Step 1 建立 Observable 介面與實例類別
* `Observable`
```java
public interface Observable {
	
    public void add(Observer o);

    public void remove(Observer o);

    public void notifys();
}
```

* `EarthquakeObservable`
```java
public class EarthquakeObservable implements Observable {

    private String epicenter;

    private static List<Observer> observerList;

    public EarthquakeObservable() {
        observerList = new LinkedList<>();
    }

    /**
     * 在裝置上新增地震通知
     */
    @Override
    public void add(Observer o) {
        observerList.add(o);
        System.out.println("已新增地震通知");
    }

    /**
     * 在裝置上移除地震通知
     */
    @Override
    public void remove(Observer o) {
        observerList.remove(o);
        System.out.println("已移除地震通知");
    }

    /**
     * Observable 的核心啊!
     */
    @Override
    public void notifys() {
        observerList.stream().forEach(Observer::update);
    }

    /**
     * 發生新地震
     * @param epicenter
     */
    public void earthquakeHappen(String epicenter) {
        this.epicenter = epicenter;
        notifys();
    }

    /**
     * 取得地震震央
     * @return
     */
    public String getEpicenter() {
        return this.epicenter;
    }
}
```

###### Step 2 建立 Observer 介面與實例物件

* `Observer`
```java
public interface Observer {

    public void update();
}
```

* `Phone`
```java
public class Phone implements Observer {

    private EarthquakeObservable earthquakeObservable;

    public Phone(EarthquakeObservable earthquakeObservable) {
        this.earthquakeObservable = earthquakeObservable;
    }

    @Override
    public void update() {
        System.out.println("Phone: 震央位於" + this.earthquakeObservable.getEpicenter());
    }
}
```

* `EarthquakeObservable`
```java
private EarthquakeObservable earthquakeObservable;
	
    public NoteBook(EarthquakeObservable earthquakeObservable) {
        this.earthquakeObservable = earthquakeObservable;
    }

    @Override
    public void update() {
        System.out.println("NoteBook: 震央位於" + this.earthquakeObservable.getEpicenter());
    }
}
```

###### Step 3 測試
```java
public class Client {

    public static void main(String[] args) {

        EarthquakeObservable earthquakeObservable = new EarthquakeObservable();

        Phone phone = new Phone(earthquakeObservable);
        NoteBook notebook = new NoteBook(earthquakeObservable);

        earthquakeObservable.add(phone);
        earthquakeObservable.add(notebook);
        
        earthquakeObservable.earthquakeHappen("宜蘭外海");
        earthquakeObservable.earthquakeHappen("台東");
        
        earthquakeObservable.remove(phone);
    }
}
```

印出的結果：
```
已新增地震通知
已新增地震通知
Phone: 震央位於宜蘭外海
NoteBook: 震央位於宜蘭外海
Phone: 震央位於台東
NoteBook: 震央位於台東
已移除地震通知
```

## 小結

#### 優點
* 觀察者和被觀察者是松耦合 ( 抽象耦合 ) 的，符合依賴倒置原則。
* 分離了表示層 ( 觀察者 ) 和數據邏輯層 ( 被觀察者 ) ，並且建立了一套觸發機制，使得數據的變化可以響應到多個表示層上。
* 實現了一對多的通訊機制，支援事件註冊機制，支援興趣分發機制，當被觀察者觸發事件時，只有感興趣的觀察者可以接收到通知。

#### 缺點
* 如果觀察者數量過多，則事件通知會耗時較長。
* 事件通知呈線性關係，如果其中一個觀察者處理事件卡殼，會影響後續的觀察者接收該事件。
* 如果觀察者和被觀察者之間存在循環依賴，則可能造成兩者之間的循環調用，導致系統崩潰。

## 參考
* https://codingnote.cc/zh-tw/p/83149/
* [Observer Pattern](https://www.youtube.com/watch?v=_BpmfnqjgzQ&list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc&index=2)
* https://medium.com/enjoy-life-enjoy-coding/design-pattern-%E5%8F%AA%E8%A6%81%E4%BD%A0%E6%83%B3%E7%9F%A5%E9%81%93-%E6%88%91%E5%B0%B1%E5%91%8A%E8%A8%B4%E4%BD%A0-%E8%A7%80%E5%AF%9F%E8%80%85%E6%A8%A1%E5%BC%8F-observer-pattern-feat-typescript-8c15dcb21622