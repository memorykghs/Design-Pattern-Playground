# 09 - 組合模式 ( Composite )

## Composite Pattern 
校外教學的時候，老師為了方便清點人數，把整個班級 40 人分成幾個小隊，小隊又可以分成幾個小組，上車報數或是到集合地點時由小組長回報給小隊長，小隊長再回報給老師人數，速度就會快許多。<br/>
![](/images/composite-1.png)

這樣老師也不用需要管每一個小隊與小組的組成是誰，~~就算裡面有奇怪的人混進來，只要總人數對就好~~。不是，只要通過呼叫小隊長，就可以知道人數對不對；老師有什麼訊息要通知的，也是透過小隊長往下傳達。

簡而言之，Composite Pattern 是一種結構型設計模式，他可以用來將物件組成一種樹狀結構，並向使用獨立的物件一樣使用他們。就像老師只要詢問小隊長，就可以獲得全班人數，而不需要一個一個點名。

## UML
在 Composite Pattern 中可以分為幾種角色：
1. **組件 `Component`** 
Component 介面定義了簡單項目和複雜項目所有共同的方法。
<br/>

2. **葉節點 `Leaf`**
在資料結構中我們會稱沒有 child / subtree 的節點為葉子，就像真實的樹葉一樣，樹葉外不會再長其他葉子或是樹枝。<br/>
一般情況下，葉節點最終會完成大部分的實際服務，因為他們不能將工作再往下分配。
<br/>

3. **容器 `Container` 或 `Composite`**
是包含葉節點或是其他容器等子項目的單位，容器不會知道其涵蓋的子項目的類別，它只關注通用的物件方法，以及與子項目的互動。<br/>
容器接收到請求後會將工作分配給自己的子項目處理，最後將結果回傳給客戶端。

![](/images/composite-2.png)

> 補充

以上的模式由於 Leaf 跟 Composite 都實作同一個介面，代表在 Leaf 中不會用到的 `add()`、`remove()` 及 `getChild()` 方法也必須覆寫，此模式稱為**透明模式**。為了避免這種情況，可以採用另一種模式，稱之為**安全方式**。

安全方式就是 Component 介面中不宣告以上三個方法，只宣告 `doService()`，這樣 Leaf 就不需要實作。但是 Composite 內就要特別在多加方法，有時候會造成用戶端調整的不便。

## 實際應用

## 實作
以上面的 UML 圖實作，程式碼如下：
* `Component` 介面
```java
public interface Component {

    public void add (Component c);
	
    public void remove(Component c);
	
    public Component getComponent(int i);
	
    public void doService();
}
```
<br/>

* `Leaf` 子項目
```java
public class Leaf implements Component {

    @Override
    public void add(Component c) {
        // Leaf 不能 add, pring log
        System.out.println("Leaf 不可再新增子元素");
    }

    @Override
    public void remove(Component c) {
        // Leaf 不能 remove, pring log
        System.out.println("Leaf 無刪除子元素功能");
    }

    @Override
    public Component getComponent(int i) {
        // Leaf 不能 getComponent, pring log
        System.out.println("Leaf 無子元素");
        return null;
    }

    @Override
    public void doService() {
        System.out.println("Leaf：我真的有在做事!");
    }
}
```

```java
public class MapleLeaf implements Component {

    @Override
    public void add(Component c) {
        // MapleMapleLeaf 不能 add, pring log
        System.out.println("MapleLeaf 不可再新增子元素");
    }

    @Override
    public void remove(Component c) {
        // MapleLeaf 不能 remove, pring log
        System.out.println("MapleLeaf 無刪除子元素功能");
    }

    @Override
    public Component getComponent(int i) {
        // MapleLeaf 不能 getComponent, pring log
        System.out.println("MapleLeaf 無子元素");
        return null;
    }

    @Override
    public void doService() {
        System.out.println("MapleLeaf：我真的有在做事!!!");
    }
}
```

* `Composite` 組合類別
```java
public class Composite implements Component {

    /** 用來裝子項目的容器 */
    private List<Component> list = new ArrayList<>();

    @Override
    public void add(Component c) {
        list.add(c);
    }

    @Override
    public void remove(Component c) {
        list.remove(c);
    }

    @Override
    public Component getComponent(int i) {
        return list.get(i);
    }

    @Override
    public void doService() {
        // 容器物件請求轉交子項目去做
        // 每個子項目都要跑，用迴圈遍歷
        for (Object obj : list) {
            ((Component) obj).doService();
        }
    }
}
```

* `Client` 測試用的客戶端
```java
public class Client {
	
    public static void main(String[] args) {
        Composite comosite = new Composite();
        comosite.add(new Leaf());
        comosite.add(new MapleLeaf());
        
        comosite.doService();
    }
}
```

## 小結

#### 優點
* 容易加入新類型的 Component
* 對 Client 來說就不需要知道他所處理到的是 Leaf 或著是 Composite

#### 缺點
* 難以約束 Compoiste 中 Component 的種類數量

## 參考
* https://ithelp.ithome.com.tw/articles/10218208
* https://refactoring.guru/design-patterns/composite
* https://www.gushiciku.cn/pl/pPh1/zh-tw