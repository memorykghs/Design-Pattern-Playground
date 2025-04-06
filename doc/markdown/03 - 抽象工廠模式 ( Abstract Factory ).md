# 04 - 抽象工廠模式 ( Abstract Factory )
## 定義
為了解決簡單工廠違反開放封閉原則的問題，針對工廠的部分也設定介面，當要產出產品時，才實例化該產品的工廠，透過工廠產生產品類別。

## 角色 & UML
* **Product ( 抽象產品 )**
是一個定義產品規格的介面 ( Interface )。

* **ConcreteProduct ( 實體產品 )**
具體產生的產品實體 ( Class )，藉由工廠角色依據介面條件而建立。

* **Factory ( 工廠 )**
定義工廠的介面。

* **ContreteFactory ( 實體工廠 )**
根據不同條件產生的工廠實例，負責根據參數建立產品類別。

![ ](/images/abstract_factory-1.png)

## 實作說明
在工廠模式裡，我們只有一個花園，種的花是3種不同的康乃馨，有一天花園擴大了變成2個花園，而且第二個花園預計種3種不一樣的玫瑰，以工廠方法模式來時做的話，就需要建立6個不一樣的工廠來種花。

| 花園A | 花園B
|---   |---
| 康乃馨 | 玫瑰
| 莉莉康乃馨 | 驕傲的玫瑰
| 疲勞的莉莉康乃馨 | 無聊的玫瑰

這時候原本的工廠方法模式就變得非常難以維護。動物有分界們剛目科屬種，花的話我們就以通稱來區分好了(欸)。康乃馨和玫瑰都有很多種，粗略可以將這兩個名稱區分為兩個不同的工廠，一個工廠負責種植不同康乃馨，另一個則負責種植不同的玫瑰。

![ ](/images/abstract_factory-2.png)

先來建立產品，也就是不同種類的花，他們都會實作 `Flower` 介面。
```java
public interface Flower {
    void bloom();
}
```

康乃馨的部分：
```java
public class Carnation implements Flower{
    @Override
    public void bloom() {
        System.out.println("Carnation bloomed.");
    }
}
```
```java
public class LilyCarnation implements Flower{
    @Override
    public void bloom() {
        System.out.println("Lily carnation bloomed.");
        System.out.println("Lily carnation eats people!!!!!");
    }
}
```
```java
public class TiredLilyCarnation implements Flower{
    @Override
    public void bloom() {
        System.out.println("Tired Lily carnation tired to bloom.");
        System.out.println("Tired to eat people, either.");
    }
}
```

玫瑰花的部分：
```java
public class Rose implements Flower{
    @Override
    public void bloom() {
        System.out.println("Rose bloomed.");
    }
}
```
```java
public class ProudRose implements Flower {
    @Override
    public void bloom() {
        System.out.println("ProudRose bloomed.");
        System.out.println("Rose says, kneel down!");
    }
}
```
```java
public class BoredRose implements Flower{
    @Override
    public void bloom() {
        System.out.println("BoredRose bloomed.");
        System.out.println("BoredRose feels very bored.");
    }
}
```

然後建立不同的花的種植工廠，以康乃馨工廠來舉例，它要可以種植所有不同的康乃馨。所有的實體工廠都會繼承抽象工廠，抽象工廠會定義實體工廠應具備的功能 ( 屬性 or 方法 )。
```java
public interface FlowerFactory {
    Flower plantFlower(String flowerType);
}
```
```java
public class CarnationFactory implements FlowerFactory {
    /**
     * 種康乃馨
     * @return
     */
    @Override
    public Flower plantFlower(String carnationType) {
        Flower flower = null;

        switch (carnationType) {
            case "Carnation":
                flower = new Carnation();
                break;
            case "LilyCarnation":
                flower = new LilyCarnation();
                break; 
            case "TiredLilyCarnation":
                flower = new TiredLilyCarnation();
                break;
        }

        return flower;
    }
}
```
```java
public class RoseFactory implements FlowerFactory {
    /**
     * 種玫瑰花
     * @return
     */
    @Override
    public Flower plantFlower(String roseType) {
        Flower flower = null;

        switch (roseType) {
            case "Rose":
                flower = new Rose();
                break;
            case "ProudRose":
                flower = new ProudRose();
                break;
            case "BoredRose":
                flower = new BoredRose();
                break;
        }

        return flower;
    }
}
```

這樣在新增康乃馨或是玫瑰花種類的部分，就只需要新增一個產品類別，以及新增實體工廠內的判斷邏輯，相較於原本的工廠方法模式，不需要額外再建立
新的實體工廠來產生產品。

簡而言之，就是將工廠原本只生產一個產品的功能，擴充為生產產品族。像是 BMW 工廠應該要可以生產所有系列的車，而 Audi 工廠也應該可以製造出該品牌旗下所有系列的汽車，而不是一個系列一個工廠。

## 結論
與工廠方法模式比較起來，減少了實體工廠的數量，不過同時也限定了實體工廠裡面可以產生的產品類別。新增新的產品同樣也需要去修改實體工廠的判斷邏輯。

###### 優點
* 與工廠方法模式相同，外部使用者不需要知道如何產生實例便可以使其公開方法建立想要的物件 &rArr; 職責分離。
* 符合開放封閉原則，新增的產品建立過程不會動到既有邏輯。
###### 缺點
* 擴充產品時，就需要對實體工廠新增判斷邏輯。

## 參考
https://blog.amowu.com/factory-pattern/
https://blog.techbridge.cc/2017/05/22/factory-method-and-abstract-factory/