# 15 - 策略模式 ( Strategy )

## Strategy Pattern 
是一種行為模式，可以讓使用者定義一系列演算法，並分別封裝起來放入獨立的類別中，讓它們之間可以互相替換，從而讓演算法的變化不會影響到使用演算法的用戶。

## UML
![](/images/strategy-4.png)

* **Strategy 策略**
所有具體策略的接口，定義應用程式中用於執行策略的方法。

* **Concrete Strategies 具體策略**
實現應用程式中需要的不同的算法或模式的類別。

* **Context 上下文**
內部有使用的策略的 reference，且僅通過策略接口與該對象進行交流。當應用程式中有需要使用到策略模式的方法時，他會呼叫關聯的實體策略類別並調用其方法。外部使用者可以不需要知道內部涉及的策略類型與實作方法。

* **Client 客户端**
建立一個特定的策略類別並傳入要用的物件中。

## 實際應用

## 實作

![](/images/strategy-3.png)

首先建立 CameraModeStrategy。

```java
public interface CameraModeStrategy {
    void photograph();
}
```

接著建立三個不同的拍攝模式：白天模式、夜景模式與動態捕捉模式，分別繼承定義的 Strategy 介面。
```java
public class DailyMode implements CameraMode{

    @Override
    public void photograph() {
        System.out.println("Daily mode on. Take a photo.");
    }
}
```
```java
public class NightMode implements CameraMode{

    @Override
    public void photograph() {
        System.out.println("Night mode on. Take city night scene.");
    }
}
```
```java
public class DynamicMode implements CameraMode{

    @Override
    public void photograph() {
        System.out.println("Dynamic mode on. Take exercising photo.");		
    }
}
```

然後建立相機類別，裡面有一個紀錄現在使用模式的私有屬性，以及一個按下快門拍照的方法。
```java
public class Camera {

    private CameraMode cameraMode;

    public void photograph() {
        cameraMode.photograph();
    }

    public void setMode(CameraMode cameraMode) {
        this.cameraMode = cameraMode;
    }
}
```

最後，就是測試了!
```java
public class Client {

    public static void main(String[] args) {

        Camera camera = new Camera();
        camera.setMode(new DailyMode());
        camera.photograph();

        System.out.println("==========");

        camera.setMode(new NightMode());
        camera.photograph();

        System.out.println("==========");

        camera.setMode(new DynamicMode());
        camera.photograph();
    }
}
```

測試結果：
```
Daily mode on. Take a photo.
==========
Night mode on. Take city night scene.
==========
Dynamic mode on. Take exercising photo.
```

## 小結
在策略模式中，以替換實體類別取代繼承，原因是因為如果是用繼承，結構會長的像下面這樣：<br/>
![](/images/strategy-2.png)

如果改用策略模式，就可以將獨立封裝好的功能傳入，以達到共用的目的。

#### 優點
* 策略模式符合開閉原則。
* 改變演算法或功能的同時，Client 端不需要跟著修改。
* 避免使用多重條件轉移語句，如if…else…語句、switch語句。
* 提高演算法的保密性和安全性。

#### 缺點
* 客戶端必須知道所有的策略，並且自行決定使用哪一個策略類。
* 程式碼中會產生非常多的策略類，增加維護難度。

## 參考
* https://refactoringguru.cn/design-patterns/strategy
* https://github.com/youlookwhat/DesignPattern#4-%E7%AD%96%E7%95%A5%E6%A8%A1%E5%BC%8F