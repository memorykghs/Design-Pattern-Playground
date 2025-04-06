# 13 - 樣板模式 ( Template )

## Template Pattern 
想不到例子了...
也沒什麼好說的，總之就是模板方法中先定義一個給 Client 端呼叫的方法，該方法不能被子類別複寫，所以通常是 `public final method()`。然後將這個方法要達到的目標拆成一個一個比較小的 method，這些步驟可以由子類別實現，或是寫一個 default method 在模板中，要不要複寫由子類別決定。

總之，就是定義一個演算法的骨架，並允許子類為其中的一個或者多個步驟提供實現。

## UML
1. **抽象類別 Abstract­Class** 
將達成某個目的的執行過程拆成較細的方法，並規定調用他們的優先順序或關係，模板方法會依照這些特定的順序來調用這些步驟。當有重複不變的邏輯，也可以直接定義在模板方法內。

2. **實例類別 Concrete­Class** 
可以複寫所有模板類別中的步驟，但不能複寫樣板本身。
![](/images/template-1.png)

## 實際應用

## 實作
一起來做漢堡包^^。

* `Tempalte Method Class`
```java
public abstract class HambergerTemplate {
	
	final void makeHamberger() {
		
		roastBun();
		addMeat();
		
		if(doubleCheeseBerger()) {
			addCheese();
			addMeat();
		}
		addCheese();
		addVegetables();
		wrapHamberger();
	}
	
	public abstract void roastBun();
	
	public abstract void addMeat();
	
	public void addCheese() {
		System.out.println("Added cheese.");
	}
	
	public void addVegetables() {
		System.out.println("Added vegetables");
	}
	
	public void wrapHamberger() {
		System.out.println("Hamberger wrapped.");
	}
	
	public boolean doubleCheeseBerger() {
		return false;
	}
}
```
<br/>

然後建立兩種產品：雙層牛肉吉士堡、麥香魚
```java
/**
 * 雙層牛肉吉士堡
 * @author memorykghs
 */
public class DoubleCheeseBerger extends HambergerTemplate {

	String usedMeat = "beef";

	String usedBun = "rice";

	@Override
	public void roastBun() {
		System.out.println("Roast " + usedBun);
	}

	@Override
	public void addMeat() {
		System.out.println("Added " + usedMeat);
	}
	
	public void wrapHamberger() {
		System.out.println("DoubleCheeseBerger wrapped.");
	}

	public boolean doubleCheeseBerger() {
		return true;
	}
}
```

```java
/**
 * 麥香魚
 * 
 * @author memorykghs
 */
public class FiletOFishBerger extends HambergerTemplate {

	String usedMeat = "cod"; // 鱈魚

	String usedBun = "bun";

	@Override
	public void roastBun() {
		System.out.println("Roast " + usedBun);
	}

	@Override
	public void addMeat() {
		System.out.println("Added " + usedMeat);
	}

	public void wrapHamberger() {
		System.out.println("FiletOFishBerger wrapped.");
	}
}
```

最後測試。
```java
public class Client {

	public static void main(String[] args) {

		// 做雙層牛
		DoubleCheeseBerger doubleCheeseBerger = new DoubleCheeseBerger();
		doubleCheeseBerger.makeHamberger();
		
		System.out.println("==========");
		
		// 做麥香魚
		FiletOFishBerger fieFiletOFishBerger = new FiletOFishBerger();
		fieFiletOFishBerger.makeHamberger();

	}
}
```

![](/images/template-2.png)

## 小結

#### 優點
* 利用模板方法將相同處理邏輯的程式碼放到抽象父類中，可以提高程式碼的復用性。
* 將不同的程式碼放到不同的子類中，通過對子類的擴展增加新的行為，提高程式碼的擴展性。
* 把不變的行為寫在父類上，去除子類的重複程式碼，提供了一個很好的程式碼復用平台，符合開閉原則。

#### 缺點
* 類數目的增加，每一個抽象類都需要一個子類來實現，這樣導致類的個數增加。
* 類數量的增加，間接地增加了系統實現的複雜度。
* 繼承關係自身缺點，如果父類添加新的抽象方法，所有子類都要改一遍。

## 參考
* https://refactoring.guru/design-patterns/template-method
* https://codingnote.cc/zh-tw/p/83149/
* https://www.youtube.com/watch?v=7ocpwK9uesw&list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc&index=13