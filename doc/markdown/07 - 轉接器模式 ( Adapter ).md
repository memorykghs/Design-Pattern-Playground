# 07 - 轉接器模式 ( Adapter )

## Adapter Pattern 
大家都聽過披著羊皮的狼，狼想要混入羊群，就需要找一張羊皮披著偽裝成洋。在上述這個例子中，狼與羊是兩種完全不同、且無法放在同一個地區和平生活的動物。

![](/images/adapter-4.png)

藉由披著羊皮的狼的故事，可以知道應用轉接器模式的狀況的特點：
* 客戶端介面與當前應用程式的介面不相容 ( 狼與羊是互斥共生關係 )
* 無法修改任一方的程式碼 ( 畢竟不能把狼改造成羊 )
* 確認多一個到多個客戶端將受益於使用服務類別的方法

那麼羊皮 ( 轉接器 ) 做了什麼呢，首先它靠外表成功的偽裝成羊 ( 具有與目標對象相同的接口 )。羊群知道混入了一隻披著羊皮的狼嗎?可能不知道! ( 呼叫轉接器方法的對象很有可能不知道轉接器的存在 ) ~~因為羊群沒有想過有狼會把同伴的皮剝下來當煙霧彈~~。

講點正經的，另外一個例子是如果想要做一個監控市場股價的 APP，但股票資訊平台的 API 只提供 XML 格式的檔案，而分析用的第三方資源庫則是必須接收 JSON 格式資料。這時候就需要在應用程式中做一個轉接器，將 XML 資料轉換為 JSON 格式。

![](/images/adapter-1.png)

轉接器模式痛過封裝某個對象，將複雜的轉換過程隱藏起來。被封裝的對象甚至察覺不到轉接器的存在。除了轉換不同格式的數據外，轉接器模式還有助於不同接口的介面之間的合作，其運作方式如下：

1. 轉接器實現一個與其相容的接口，並建立實例。
    > The adapter gets an interface, compatible with one of the existing objects.

2. 透過相容的接口，目標對象就可以使用該接口調用轉接器的方法。
    > Using this interface, the existing object can safely call the adapter's methods.

3. 轉接器方法被調用後，將已與另一個對象相容的格式將請求傳遞給該對象。
    > Upon receiving a call, the adapter passes the request to the second object, but in a format and order that the second object expects.

![](/images/adapter-2.png)
<br/>

## UML
在還沒有使用 Adapter Pattern 之前，且可以改變任一方的源碼的情況下，我們需要在 Client 端或是 Service 端自行做轉換，UML可能會長的像下面這樣。<br/>

![](/images/adapter-6.png)

而當使用 Adapter Pattern 之後，UML 如下：<br/>

![](/images/adapter-3.png)

1. 客戶端是一個爆含當前應用程式業務邏輯的類別。
    > The Client is a class that contains the existing business logic of the program.

2. 客戶端接口規定了其他與之互動的對象必須遵循的規範。
    > The Client Interface describes a protocol that other classes must follow to be able to collaborate with the client code.

3. 應用程式中有一些功能類別 ( 通常來自第三方或是較舊的系統 ) 與客戶端的接口不相容，因此無法直接調用其功能。
    > The Service is some useful class (usually 3rd-party or legacy). The client can't use this class directly because it has an incompatible interface.

4. 轉接器是一個可以同時與客戶端和服務端作用的類別，它在實現客戶端的接口的同時，封裝了服務對象。轉接器接受客戶端發起的調用，並將其轉換為適用於被封裝對象的接口，再呼叫其方法。
    > The Adapter is a class that's able to work with both the client and the service: it implements the client interface, while wrapping the service object. The adapter receives calls from the client via the adapter interface and translates them into calls to the wrapped service object in a format it can understand.

5. 客戶端成是只需要通過接口與轉接器互動即可，不需要與具體的轉接器類別耦合。因此，可以在程式中新增新的轉接器而不需要修改原有的代碼。這在提供服務的類別接口被更改或替換時很有用，因為不需要修改到客戶端的程式碼就可以創建新的轉接器。
    > The client code doesn't get coupled to the concrete adapter class as long as it works with the adapter via the client interface. Thanks to this, you can introduce new types of adapters into the program without breaking the existing client code. This can be useful when the interface of the service class gets changed or replaced: you can just create a new adapter class without changing the client code.

## 實際應用
開源項目中有不少使用 Adapter Pattern 的例子，不過大部分我們可能不會直接的用 `XXXAdapter` 作為類別的命名。常見的有 Spring MVC 中的 `NamedParameterJdbcTemplate`，以及 Spring Security 的 `WebSecurityConfigurerAdapter`。

Spring MVC 的 `NamedParameterJdbcTemplate` 之所以是 Adapter Pattern，是因為當要再 SQL 中使用 `in` 或是其他必須傳入某個範圍述職的語法時，在原本的 `JdbcTemplate` 中是使用佔位符 `?` 的先後順序，將參數傳入，範例如下。

```sql
select STUDENT_ID from HISTORY_EXAM where SCORE in (? ,? ,? ,? ,?);
```

而 `NamedParameterJdbcTemplate` 則可已給定參數名稱如 `:score` 作為佔位符來帶入參數，這樣一來相對直觀，也不需要調整傳入參數的順序。
```sql
select STUDENT_ID from HISTORY_EXAM where SCORE in :score;
```
<br/>

那為什麼是 Adapter Pattern 呢?是因為 `NamedParameterJdbcTemplate` 其實是對 `JdbcTemplate` 做包裝，實際上操作資料庫仍然是由 `JdbcTemplate` 進行的，不過他改變了使用者傳入參數的方式。<br/>

![](/images/adapter-5.png)

## 實作
![](/images/adapter-7.png)

首先建立一個用呼叫羊的介面 `BeSheep`，裡面有一個方法 `eatGrass()`。
```java
public interface BeSheep {
	public void eatGrass();
}
```

再來建立一個其他生物轉換介面，規定一定要有某一些方法，也就是 `Adaptee` 介面的角色。
```java
public interface AnimalTransfer {

	public String transferToSheep();
	
	public void origin();
}
```

假設現在有灰狼跟白狼都想偷偷地批羊皮混進羊群裡，所以針對這兩種不同生物，我們要各自建立一個類別。這兩個類別都要去實作 `AnimalTransfer` 介面的方法。
```java
public class WhiteWolfTransfer implements AnimalTransfer{

	@Override
	public String transferToSheep() {
		return "Eat grass";
	}

	@Override
	public void origin() {
		System.out.println("原本是白狼");
	}

}
```
```java
public class GrayWolfTransfer implements AnimalTransfer{

	@Override
	public String transferToSheep() {
		return "Eat grass";
	}

	@Override
	public void origin() {
		System.out.println("原本是灰狼");
	}

}
```

接著，套入最重要的角色 `SheepAdapter`，`SheepAdapter` 需要實作客戶端規定的介面方法。
```java
public class SheepAdapter implements BeSheep{
	
	private AnimalTransfer animalTransfer;
	
	public SheepAdapter(AnimalTransfer animalTransfer) {
		this.animalTransfer = animalTransfer;
	}

	@Override
	public void eatGrass() {
		animalTransfer.origin();
		System.out.println(animalTransfer.transferToSheep());
	}
}
```
Adapter 建立時需要給定要轉換的動物類別 ( Adaptee )，當 Client 端呼叫 Adapter 的方法時，Adapter 就會呼叫傳入的 Adaptee 的對應方法。

最後測試一下：
```java
public class Test {

	public static void main(String[] args) {
		BeSheep sheep1 = new SheepAdapter(new WhiteWolfTransfer());
		sheep1.eatGrass();
		
		BeSheep sheep2 = new SheepAdapter(new GrayWolfTransfer());
		sheep2.eatGrass();
	}
}
```

## 小結
* 使用時機：
  * 需要使用既有的類別或套件的功能，但該類別或套件的介面與目前使用的類別介面不符
  * 可能都是使用第三方套件，沒有原始碼且無法改動第三方函式庫時 

#### 優點
* 單一職責原則：將接口或數據轉換程式從應用程式的主要業務邏輯中分離。
* 開放封閉原則：只要客戶端呼叫轉接器對象的方法，就能在不修改線有客戶端程式碼的情況下，在程式中添加新的轉接器。

#### 缺點
* 程式碼整體的複雜度增加，因為需要新增轉接器的接口介面和實作類別。

## 參考
* https://refactoringguru.cn/design-patterns/adapter
* https://codingnote.cc/zh-tw/p/114284/
* https://matthung0807.blogspot.com/2020/03/spring-jdbctemplate-and.html