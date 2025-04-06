# 11 - 蠅量/享元模式 ( Flyweight )

## Flyweight Pattern 
Flyweight 原本是拳擊術語，指最輕量的級別，蠅量 ( 享元 ) 模式單看文字可能有點難理解，但簡單來說就是共享元件 ( 資源 )。下文都稱為享元模式。

就像是在玩俄羅斯方塊，他們的形狀不外乎就是幾種。但是每一個方塊可以有位置以及方向的不同，當畫面上重複出現某個圖形時，必須記錄該圖案的位置以及旋轉方向，那麼你可能會這樣寫：<br/>
![](/images/flyweight-1.png)

不過當玩到越來越後面時，要管理的物件也會變多，就極度有可能造成系統 crash。<br/>
![](/images/flyweight-2.png)

這時候就可以使用享元模式，有效的減少需要實體化類別的數量。操作的方式可以先將實體中的屬性如：顏色、位置、方向等等資訊區分成可以共享或是不能共享。

可以共享的代表物件的某些參數是不需要隨著狀況改變而改變的，例如顏色，通常我們會把這類不會變動的參數放在享元物件的內部，稱為**內部狀態 ( Intrinsic State )**，他們是可以共享的。不能共享的則是向每個方塊的位置或是方向，稱為**外部狀態 ( Extrinsic State )**。<br/>
![](/images/flyweight-3.png)

依照這個邏輯，我們可以將原本的 Class 拆分成這樣：<br/>
![](/images/flyweight-6.png)

## UML
享元模式可以劃分為兩種模式：**單純享元模式**或是**複合享元模式**。角色我們大致可以分為以下：<br/>

但是我沒看懂 [Guru](https://refactoringguru.cn/design-patterns/flyweight) 的圖...( ´•̥̥̥ω•̥̥̥` )
![](/images/flyweight-7.png)

1. **Flyweight**
為介面或抽象類別的接口，定義了 `ConcreteFlyweight` 的方法，非享元的外部狀態以參數的形式通過方法傳入。
<br/>

2. **ConcreteFlyweight**
實現Flyweight。
<br/>

3. **UnsharableFlyweight**
稱作複合享元物件，不可共享。但可分解成多個單純享元物件，則可共享。
<br/>

4. **FlyweightFactory**
負責建立及管理享元物件。當 `Client` 呼叫享元物件時，`FlyweightFactory` 檢查是否存在符合要求的物件，存在則提供，不存在建立新的享元物件。

#### 單純享元模式
沒有可以共享的物件，較為單純。
![](/images/flyweight-4.png)

#### 複合享元模式
多了 `UnsharableFlyweight` 物件。
![](/images/flyweight-5.png)

## 實際應用

## 實作

## 小結
享元模式可以想成是對象池的一種實現。類似於執行緒池，執行緒池可以避免不停的創建和銷毀多個對象而消耗性能，也就是提供了減少對象數量從而改善應用所需的對象結構的方式。

* 適用場景：
  * 當一個類別會在執行時期產生多個實例，且他們的控制方法相同時。
  * 也就是系統有大量相似對象、需要緩衝池的場景。
<br/>

#### 內部與外部狀態
* 內部狀態 Intrinsic State
  * 不會隨著環境變化而改變的屬性，可共享。
  * 通常會放在享元物件的內部。

* 外部狀態 Extrinsic State

#### 優點
* 減少執行時期的物件數量，節省記憶體。
* 減少記憶體之外的其他資源的佔用。

#### 缺點
* 需要關注內部狀態和外部狀態，關注執行緒安全問題。
* 使系統、程式的邏輯複雜化。
* 一般設置成享元工廠，而享元工廠大部分情況設置成單例，因為快取存放在一個地方。

## 參考
* http://corrupt003-design-pattern.blogspot.com/2017/01/flyweight-pattern.html
* O'REILLY - 深入淺出設計模式
* https://ianjustin39.github.io/ianlife/design-pattern/flyweight-pattern/

## 資源
* https://visualvm.github.io/download.html