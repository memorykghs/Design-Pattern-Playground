# 10 - 裝飾器模式 ( Decorator )

## Decorator Pattern 
假設有一間飲料店的點餐系統，開店初期，奶茶只能加入珍珠或是小芋圓，那麼像下面這樣設計是沒有問題的。  
![](/images/decorator-1.png)
<br/>

不過當品項增加之後可能就沒有這麼美妙了，有的客戶可能同時換成黑糖珍珠奶茶，代表我們需要依照不同的品項，就建立一個類別。  
![](/images/decorator-2.png)
<br/>

這樣的設計一來非常難維護，二來，萬一某項原料漲價了，該品項也要跟著被調整，就犯了**開放封閉原則**，已經存的品項的類別不應該被修改。那麼，你可能會用繼承的方式，來處理這些加料的部分：

## UML
![](/images/decorator-3.png)
可以將 Decorator Pattern 中的角色分為以下幾類：
1. **Component**
定義了實體物件的共用行為 ( 方法 )，以及客戶端要呼叫的接口。可以是抽象類別或是介面。以上面飲料店的例子來看的話，是所有"飲料"都要有的功能，也就是回傳價錢。
<br/>

2. **Concrete Component**
是一個 Component 的實體類別，覆寫了 Component 的方法。像是上面例子中的奶茶，是真正被製造出來的產品 ( 同時可能是其他產品的基底 )。
<br/>

3. **Decorator**
此類別回包含 Component 實體類別的物件，並繼承或實作 Component，這樣就可以在裝飾器內部使用原有類別功能的同時，加上一些新的功能或是敘述，就好比加珍珠要加價。
<br/>

4. **Concrete Decorator**
定義了可以動態添加到 Component 的額外行為，覆蓋 Decorator 的方法並在調用父類別方法之前或之後執行本身添加的功能。像是在奶茶中加珍珠。
<br/>

5. **Client**
呼叫時只要呼叫對應的裝飾器提供的方法，不需要管理面到底包了幾層的物件。

綜上所述，替換成飲料店的結構會長的像下面這個樣子：  
![](/images/decorator-4.png)

## 實際應用
Java I/O 中的類別就屬於裝飾器。

![](/images/decorator-5.png)

1. `FileInputStream` 是被裝飾的物件，`java.io` package 中提供許多元件，包括 `FileInputStream`、`StringBufferInputStream`、`ByteArrayInputStream`...等，都是可以讀取 bytes 的基本組件。

2. 而 `BufferedInputStream` 是具體裝飾器，它為 `FileInputStream` 加上緩衝行為，改善效能。
3. `ZipInputStream` 也是一個具體裝飾器，可以在讀取 zip 檔案的資料時讀取 zip 檔案項目 ( entry )。

具體架構如下：  

![](/images/decorator-6.png)

## 實作
* `Drink` 抽象類別
  ```java
  public abstract class Drink {

      String description = "Unknown drink";

      public String getDescription() {
          return description;
      }

      public abstract BigDecimal cost();
  }
  ```
<br/>

* `MilkTea` 
實際產品，繼承 `Drink` 抽象類別
  ```java
  public class MilkTea extends Drink{
      String description = "Milk tea";

      public String getDescription() {
          return description;
      }

      @Override
      public BigDecimal cost() {
          return new BigDecimal("50");
      }
  }
  ```
<br/>

* `DrinkDecorator` 
裝飾器抽象類別，包含 `Drink` 的實作類別
  ```java
  public abstract class DrinkDecorator extends Drink{
	
      Drink drink;

      public abstract String getDescription();
	
      public abstract BigDecimal cost();
  }
  ```

* 奶茶裝飾器 - `Mocha` 與 `Taro`
  ```java
  public class Mocha extends DrinkDecorator{
	
      public Mocha(Drink drink) {
          this.drink = drink;
      }

      @Override
      public String getDescription() {
          return "Mocha " + drink.getDescription();
      }

      @Override
      public BigDecimal cost() {
          return drink.cost().add(new BigDecimal("5"));
      }
  }
  ```

  ```java
  public class Taro extends DrinkDecorator{
	
      public Taro(Drink drink) {
          this.drink = drink;
      }

      @Override
      public String getDescription() {
          return "Taro " + drink.getDescription();
      }

      @Override
      public BigDecimal cost() {
          return drink.cost().add(BigDecimal.TEN);
      }
  }
  ```
<br/>

* `Client`
  ```java
  public class Client {
	
    public static void main(String[] args) {
          // 製作奶茶
          Drink milkTea = new MilkTea();
          System.out.println(milkTea.getDescription());
          System.out.println(milkTea.cost());
		
          // 製作抹茶奶茶
          Drink mochaMilkTea = new MilkTea();
          mochaMilkTea = new Mocha(mochaMilkTea);
          System.out.println(mochaMilkTea.getDescription());
          System.out.println(mochaMilkTea.cost());
		
          // 製作抹茶芋圓奶茶
          Drink taroMochaMilkTea = new MilkTea();
          taroMochaMilkTea = new Mocha        (taroMochaMilkTea);
          taroMochaMilkTea= new Taro(taroMochaMilkTea);
          System.out.println(taroMochaMilkTea.getDescription());
          System.out.println(taroMochaMilkTea.cost());
      }
  }
  ```

## 小結
* 在不改變原有對象的基礎，可以在執行時期將功能附加到對象上
* 也是將物件包裹起來，但會賦予新的行為或功能，但 Proxy Pattern 不會

#### 優點
* 比繼承靈活，不需要因為修改一個方法，全部繼承的對象都需要更改
* 透過不同裝飾類別的排列組合，可以時間不同效果
* 完全遵守開放封閉原則

#### 缺點
* 需要維護更多的物件，最後有可能寫出錯誤的程式碼，所以通常會搭配工廠模式或是建造者模式來建立裝飾器。


## 參考
* https://refactoring.guru/design-patterns/decorator
* O'REILLY - 深入淺出設計模式
* https://codingnote.cc/zh-tw/p/83149/