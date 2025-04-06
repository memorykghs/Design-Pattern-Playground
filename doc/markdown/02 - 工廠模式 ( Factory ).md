# 02 - 工廠模式 ( Factory )
## 定義
簡單工廠模式是一種管理物件創建的模式，可以依照不同的傳入參數來決定實例化哪一個類別的物件，而且我們也不需要知道該物件要怎麼被實例化。
簡單說，就是由一個工廠對象決定創建出哪一種產品類的實例。

## 角色 & UML
![](/images/simple_factory-1.png)

[圖片來源](https://www.twblogs.net/a/5b812a152b71772165ab5bab)

* **Product ( 抽象產品 )**
是一個定義產品規格的介面 ( Interface )。

* **ConcreteProduct ( 具體產品 )**
具體產生的產品實體 ( Class )，藉由工廠角色依據介面條件而建立。

* **Factory ( 工廠 )**
負責建立對應的物件。

## 實作說明
假設我們有一個花園，這個花園目前只種了一種花 - 康乃馨，康乃馨會開花，以類別 ( Class ) 的方式可以寫成這樣：
```java
public class Carnation {
	
    /**
	 * 開花
	 */
    public void bloom() {
        System.out.println("Carnation bloomed.");
    }
}
```

但如果這不小心種出的突變種的康乃馨，這時候我們的花園就會有兩種不同的花，我們用一個新類別
來記錄突變的康乃馨：
```java
public class LilyCarnation {
	
    /**
     * 開花的同時會吃人!
     */
    public void bloom() {
        System.out.println("Lily carnation bloomed.");
        System.out.println("Lily carnation eat people!!!!!");
    }
}
```

康乃馨以及突變的康乃馨都是花的一種，兩種都會開花，所以我們可以用一個介面來定義花的行為，也就是只要是一朵花，就都會開花。
```java
public interface Flower {
    void bloom();
}
```

這時候修改 `Carnation.java` 以及 `LilyCarnation.java` 這兩個類別，讓它們都實作 `Flower` 這個介面，再各自覆寫開花之後的動作。
```java
public class Carnation implements Flower{

    /**
     * 開花
     */
    @Override
    public void bloom() {
        System.out.println("Carnation bloomed.");
    }
}
```
```java
public class LilyCarnation implements Flower{

    /**
     * 開花的同時會吃人!
     */
    @Override
    public void bloom() {
        System.out.println("Lily carnation bloomed.");
        System.out.println("Lily carnation eats people!!!!!");
    }
}
```

以上面的例子來說，`Flower` 這個介面就是 Product ( 抽象產品 ) 的角色，而康乃馨和突變康乃馨都是實作介面的類別，也就是 ConcreteProduct ( 具體產品 ) 角色。

再來，有了前面突變種的經驗，我們就可以決定到底要種哪一種花，這時候我們新增一個類別，用來在花園種植花朵。在這個類別裡，我們讓老天 ( 亂數產生 ) 來決定要我們種什麼花。
```java
public class FlowerFactory {
    public Flower plantFlower() {
        int num = (int) (Math.random() * 100) + 1;

        if (num % 2 == 1) {
            return new Carnation();
        } else {
            return new LilyCarnation();
        }
    }
}
```
```java
public class test {
	public static void main(String[] args) {
		FlowerFactory flowerFactory = new FlowerFactory();
        Flower flower = flowerFactory.plantFlower();
        flower.bloom();
	}
}
```

而這個類別就相當於 Factory ( 工廠 ) 的角色，它會負責建立康乃馨或是突變種康乃馨的實例。不過如果當花園可種植的花變多了，那麼花朵產生工廠內的邏輯也會變得相對複雜，而且會修改以前就存在的邏輯。
會修改到既有邏輯，就不符合開放封閉原則 ( 擴充容易且不會動到既有邏輯 )。
```java
public class FlowerFactory {
    public Flower plantFlower() {
        int num = (int) (Math.random() * 100) + 1;

        if (num % 3 == 1) {
            return new Carnation();
        } else if (num % 3 == 2) {
            return new LilyCarnation();
        } else { // 多了另一種康乃馨
            return new TiredLilyCarnation();
        }
    }
}
```
```java
public class TiredLilyCarnation implements Flower{

    /**
     * 沒力氣吃人...懶...
     */
    @Override
    public void bloom() {
        System.out.println("Tired Lily carnation tired to bloom.");
        System.out.println("Tired to eat people, either.");
    }
}
```

Btw，如果覺得上面每次建立新的物件都需要用 `new` 來實例化很麻煩，可以把 `plantFlower` 方法改為 `static` ，在使用工廠時就不需要將工廠實例化，可以直接使用工廠內的方法。
```java
public class FlowerFactory {
    public static Flower plantFlower() { // 改為靜態方法
        int num = (int) (Math.random() * 100) + 1;

        if (num % 3 == 1) {
            return new Carnation();
        } else if (num % 3 == 2) {
            return new LilyCarnation();
        } else {
            return new TiredLilyCarnation();
        }
    }
}
```
```java
public class test {
	public static void main(String[] args) {
		Flower flower = FlowerFactory.plantFlower();
        flower.bloom();
	}
}
```

## 結論
###### 優點
* 透過工廠建立實例，外部使用者不需要知道如何產生實例便可以使其公開方法 &rArr; 職責分離。
* 未來新增不同實體類別，外部使用者不會受到影響，只會動到工廠類別內的邏輯。
###### 缺點
* 如果產品的類別過多，工廠的邏輯將變得極為複雜以及龐大，不利於套件的擴充。
* <font color=red>因使用靜態方法，所以工廠類別無法繼承</font>

## 參考
https://raychiutw.github.io/2019/%E9%9A%A8%E6%89%8B-Design-Pattern-3-%E7%B0%A1%E5%96%AE%E5%B7%A5%E5%BB%A0%E6%A8%A1%E5%BC%8F-Simple-Factory-Pattern/