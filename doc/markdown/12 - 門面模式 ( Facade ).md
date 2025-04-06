# 12 - 門面模式 ( Facade )

## Facade Pattern 
門面模式又稱外觀模式，以下統稱外觀。先來說說外觀模式主要是要用來解決什麼問題的。現在你正在某個遊戲當中準備捏一個你喜歡的角色，並幫他搭配上衣、褲子及鞋子等裝配，這三個裝扮是不同的功能。

不過如果你懶得裝扮，也可以按下隨機外觀這個按鈕，讓系統自動幫你配置。</br>
![](/images/facade-1.png)

這個隨機按鈕就是門面模式的一種，可以把許多不同的功能整合到一個物件中，使用者只要按下按鈕，就可以一次觸發換衣服、褲子和鞋子的功能。
![](/images/facade-2.png)

## UML
這張圖中包含幾個角色：<br/>
![](/images/facade-3.png)

1. **複雜子系統 `Complex Subsystem`**
由數十個不同的對象構成，通常會出現在比較舊的系統。如果要使用這些對象的功能，必須深入了解子系統的實例與功能，例如按照正確的順序初始化對象和提供正確的資料格式。<br/>
子系統不會意識到外觀對象的存在，子系統內的對象也可以互相交互。
<br/>

2. **外觀 `Facade`**
向 `Client` 提供訪問特定子系統功能的便捷的方式，讓客戶端不需要了解子自統的功能便能夠操作子系統中的物件達到目的。
<br/>

3. **附加外觀 `Additional Facade`**
用來避免多種不相關的功能汙染單一的外觀對象，不同功能可以建立不同的附加外觀，客戶端和其他外觀都可以使用附加外觀。
<br/>

4. **客戶端 `Client`**
透過外觀對象調用子系統功能。

## 實際應用
很多，不想寫 (๑•́ ₃ •̀๑)。
[例子](https://www.codenong.com/cs106632962/)

## 實作
* `Subsystem` 子系統
```java
public class Clothes {

    private static final Map<Integer, String> CLOTHMAP = new HashMap<>();
    static {
        CLOTHMAP.put(1, "red shirt");
        CLOTHMAP.put(2, "blue shirt");
        CLOTHMAP.put(3, "green shirt");
    }

    public void setClothes(int num) {
        System.out.println(CLOTHMAP.get(num));
    }
}
```
```java
public class Pants {
    private static final Map<Integer, String> PANTSMAP = new HashMap<>();
    static {
        PANTSMAP.put(1, "red pants");
        PANTSMAP.put(2, "blue pants");
        PANTSMAP.put(3, "green pants");
    }

    public void setPants(int num) {
        System.out.println(PANTSMAP.get(num));
    }
}
```
```java
public class Shoes {
    private static final Map<Integer, String> SHOESMAP = new HashMap<>();
    static {
        SHOESMAP.put(1, "red shoes");
        SHOESMAP.put(2, "blue shoes");
        SHOESMAP.put(3, "green shoes");
    }

    public void setShoes(int num) {
        System.out.println(SHOESMAP.get(num));
    }
}
```
<br/>

* `Facade` 外觀類別
```java
public class Facade {
    public void random() {
        int random = (int) (Math.random() * 10 % 3);
        System.out.println(random);
        
        new Clothes().setClothes(random);
        new Pants().setPants(random);
        new Shoes().setShoes(random);
    }
}
```

* `Client`
```java
public class Client {
    public static void main(String[] args) {

        // 原本
        new Clothes().setClothes(1);
        new Pants().setPants(2);
        new Shoes().setShoes(3);

        // 外觀模式
        Facade facade = new Facade();
        facade.random();
    }
}
```

## 小結
外觀模式其實跟轉接器模式 ( Adapter Pattern ) 很像，兩者都是在實際的服務前多加一層。外觀模式的可以幫一個介面複雜的類別提供簡化的介面；轉接器模式則是可以將一個或多個類別的介面轉換成用戶端期望使用的介面。

兩種模式的差異在於「目的」，外觀模式的目的是幫子系統提供一個簡化的介面，轉接器模式則是修改一個介面，讓他符合客戶端期望使用的介面。

#### 優點
* 簡化了調用過程，無需深入了解子系統，以防給子系統帶來風險。
* 減少系統依賴、鬆散耦合。
* 遵循迪米特法則，即最少知道原則。

#### 缺點
* 當增加子系統和擴展子系統行為時，可能容易帶來未知風險。
* 不符合開放封閉原則。
* 某些情況下可能違背單一職責原則。

## 參考
* https://refactoring.guru/design-patterns/facade
* https://codingnote.cc/zh-tw/p/83149/
* https://ianjustin39.github.io/ianlife/design-pattern/flyweight-pattern/
* 大話設計模式