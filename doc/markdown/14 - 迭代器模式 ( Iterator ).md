# 14 - 迭代器模式 ( Iterator )

## Iterator Pattern 

![](/images/iterator-1.png)
總之就是像冒險者的背包中有很多種東西，但他們又可以被區分為藥水、礦物、武器、其他等類別，再切換到該類別時要把屬於此類的物品陳列出來。

另一個例子是書架上有許多書，書架就是一個集合，我們可以屬書架上有多少書，或是從哪個位置將書拿出來 ( 迭代器 )。
![](/images/iterator-2.png)

Drawback：
1. 可以透過 Iterator Pattern 取得內部的 Concrete 物件集合，這些集合會是有限的數量 ( finite ) ( C# 好像可以做到收集無限多個物件並回傳這件事 )。
2. 可以避免將這些潛在的集合 ( underlying collection ) 暴露出來。因為外部可以拿到集合的話可能會因為傳遞中做了一些操作，如新增、移除而去改變集合的內容。
3. 可以隨時中斷且記得 Iterator 的 location。


###### 參考
[Iterator Pattern Drawback：18:00](https://www.youtube.com/watch?v=uNTNEfwYXhI&list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc&index=16)

## UML
![](/images/iterator-3.png)

1. **迭代器 Iterator**
宣告迭代器的三個方法，分別是：
    * `hasNext(): boolean` - 是否有下一個元素
    * `next()` - 取得下一個元素並回傳
    * `current()` - 獲取當前元素或位置
<br/>

2. **迭代器實例 Concrete Iterators** 
實作變歷集合的某一種特定算法，可以實作多個對同一物件不同迭代方式的迭代器實例。會將要迭代的集合實例傳入。
<br/>

3. **集合 Collection** 
宣告一些對內部集合操作的方法，且其中有一個 `getIterator()` 方法會先建立迭代器實例，並將集合本身傳入，這樣迭代器中就會有集合物件的拷貝。
<br/>

4. **具體集合 Concrete Collections** 
Concrete Collections return new instances of a particular concrete iterator class each time the client requests one. You might be wondering, where’s the rest of the collection’s code? Don’t worry, it should be in the same class. It’s just that these details aren’t crucial to the actual pattern, so we’re omitting them.

## 實際應用

## 實作
首先建立要收集的物件：
```java
public class Item {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    // ------ getter ------
    public String getName() {
        return name;
    }
}
```

接著建立具體集合實例：
```java
public class ConcreteIterable implements Iterable {

    private List<Object> items = new ArrayList<>();

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(this); // 將自己傳入迭代器
    }

    @Override
    public void addItem(Object item) {
        items.add(item);
        
    }

    @Override
    public int getLength() {
        return items.size();
    }

    @Override
    public Object getItemAt(int index) {
        return items.get(index);
    }
}
```

---
接著分別定義具體集合介面與實例物件：
* 具體集合介面 ( 可被迭代的物件介面 )
```java
public interface Iterable {
	
    public Iterator getIterator();

    public void addItem(Object item);

    public int getLength();

    public Object getItemAt(int index);

}
```

---
最後建立迭代器介面與實例：
* 迭代器介面
```java
public interface Iterator {

    public boolean hasNext(); // 或是命名成 isDone 比較好，才不會有一堆 Next 在那邊!!!

    public void next(); // 移動 pointer 到下一個物件

    public Object current(); // 返回當前 pointer 指到的物件
}
```
<br/>

* 迭代器實例
```java
public class ConcreteIterator implements Iterator {

    int index = 0; // pointer

    private Iterable iterable; // 明確指定此 Iterator 的化也可以將型別定為 ConcreteIterable

    public ConcreteIterator(Iterable items) {
        this.iterable = items;
    }

    @Override
    public boolean hasNext() {

        if (index < iterable.getLength()) {
            return true;
        }
        return false;
    }

    @Override
    public void next() {
        this.index += 1;
    }

    @Override
    public Object current() {
        return iterable.getItemAt(index++); 
    }

}
```

測試：
```java
public class Client {
	
    public static void main(String[] args) {
        
        Iterable iterable = new ConcreteIterable();
        iterable.addItem(new Item("Bird"));
        iterable.addItem(new Item("Monkey"));
        iterable.addItem(new Item("Dimo"));
        iterable.addItem(new Item("Aunt"));
        
        Iterator iterator = new ConcreteIterator(iterable);
        while (iterator.hasNext()) {
            Item item = (Item) iterator.current();
            System.out.println(item.getName());
        }
        
    }
}
```

## 小結

#### 優點
* 多態迭代：為不同的聚合結構提供一致的遍歷介面，即一個迭代介面可以訪問不同的聚集對象。
* 簡化集合對象介面：迭代器模式將集合對象本身應該提供的元素迭代介面抽取到了迭代器中，使集合對象無須關心具體迭代行為。
* 元素迭代功能多樣化：每個集合對象都可以提供一個或多個不同的迭代器，使得同種元素聚合結構可以有不同的迭代行為。
* 解耦迭代與集合：迭代器模式封裝了具體的迭代演算法，迭代演算法的變化不會影響到集合對象的架構。

#### 缺點
對於比較簡單的遍歷（像數組或者有序列表），使用迭代器方式遍歷較為繁瑣。

## 參考
* [Iterator Pattern Vedio](https://www.youtube.com/watch?v=uNTNEfwYXhI&list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc&index=16)
* http://twmht.github.io/blog/posts/design-pattern/iterator.html
* https://refactoringguru.cn/design-patterns/iterator