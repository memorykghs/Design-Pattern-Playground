# 06 - 代理模式 ( Proxy )

## Proxy Pattern
假設有一天，你的伴侶因為在外面闖禍導致你們離婚，但是你又對法律相關的問題不熟，所以就請了一個律師，委託他幫你處理這些事情…

所以當今天你的伴侶來找你吵著要 $$ 時，你把這一切委託給了律師。<br/>

![](/images/proxy-1.png)

之後如果出現各種親戚，一律通通交給律師。<br/>

![](/images/proxy-2.png)

不管是要財產分配、撫慰金、撫養費或是碰瓷，他們都會把這些需求讓你知道，但是因為不會處理，所以其實是委託律師代辦，實際上就是律師在做事。不過親戚們的窗口還是你本人就是了。


依照上面的例子，可作如下對應：
![](/images/proxy-3.png)

## UML
在還沒有使用 Proxy 之前，UML 如下：<br>
![](/images/proxy-6.png)

Client 端會直接宣告 `ServiceInterface service = new Servie()`，直接拿到實際執行邏輯的 `Service` 物件。

而使用 Proxy 之後的 UML 會多出一個 Proxy 類別，該類別同樣實作 `ServiceInterface` 介面，只是內部會有私有屬性存取 `Service` 物件。

![](/images/proxy-4.png)

1. Service Interface 決定了服務的接口，Proxy 代理必須依照該接口方法，才能偽裝成服務對象。
    > The Service Interface declares the interface of the Service. The proxy must follow this interface to be able to disguise itself as a service object.

2. Service 是實際提供業務邏輯處理的類別。
    > The Service is a class that provides some useful business logic.

3. Proxy 類別有指向那些實際執行業務邏輯的 Service 物件的屬性。當 Proxy 完成其任務 ( 如延遲加載、cache 等等 ) 會將 Request 傳遞給 Service 對象。通常，Proxy 對象也會監控 Service 對象的生命週期。
    > The Proxy class has a reference field that points to a service object. After the proxy finishes its processing (e.g., lazy initialization, logging, access control, caching, etc.), it passes the request to the service object.<br>
    Usually, proxies manage the full lifecycle of their service objects.

4. Client 可以通過同一個 Service Interface 的 Proxy 互動，不需要管實際上執行的物件是哪一個，所以可以在一切需要該服務的地方使用 Proxy。
    >  The Client should work with both services and proxies via the same interface. This way you can pass a proxy into any code that expects a service object.


## 實際應用
實際應用的例子，可以看看 Spring Security。由於 Spring Security 是基於 Fileter 設計的，在 MVC 架構中會使用 Filter Chain 一一過濾進來 Request 中的資訊。

想要在 MVC 架構中用 Spring 框架的 Bean 就需要用到 Proxy，因為 MVC 的 ApplicationContext 跟 Spring 的 Context 是不同的，當 Request 進來要使用 Spring Security 的 Filter 時，需要一個介於兩者之間的勾同橋梁 - **DelegatingFilterProxy**。

`DelegatingFilterProxy` 會負責去攔截打進來的 Request，再交由 Spring Security 建立的 Bean 去驗證 Request 的資訊。<br/>

![](/images/proxy-5.png)

## 實作
* [sample code](https://github.com/memorykghs/Design-Pattern/tree/proxy)

```
src
  |--com.team.proxy
    |--ExPartner.java
    |--DivorceService.java
    |--LawyerProxy.java
    |--Lawyer.java
```

###### Step 1
首先建立前伴侶類別 ( Client 端 ) 。

* `ExPartner.java`
```java
public class ExPartner {
    public static void main(String[] args) {
	
    }
}
```
<br/>

###### Step 2
建立離婚服務介面，介面內有三個方法。
```java
public interface DivorceService {

    /** 財產分配 */
    public BigDecimal propertyDistribute();

    /** 要慰撫金 */
    public BigDecimal getComfortMoney();

    /** 要贍養費 */
    public BigDecimal getAlimony();

}
```
<br/>

###### Step 3
建立實際處理離婚事務的律師類別，它會實作 `Service` 介面，也就是實作 `DivorceService` 這個離婚服務介面。
```java
public class Lawyer implements DivorceService {

    @Override
    public BigDecimal propertyDistribute() {
        System.out.println("帳戶只剩100塊");
        return new BigDecimal("50");
    }

    @Override
    public BigDecimal getComfortMoney() {
        System.out.println("不是事主的錯，所以不用賠");
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getAlimony() {
        System.out.println("沒有小孩，哪來的贍養費");
        return BigDecimal.ZERO;
    }
}
```
<br/>

###### Step 4
然後建立代理類別，代理也會實作 `DivorceService` 介面，只是會有私有屬性 `Lawyer`，在每個 `@Override` 方法中就都是直接呼叫 `Lawyer` 去做事。
```java
public class LawyerProxy implements DivorceService {

    private Lawyer lawyer = new Lawyer();

    @Override
    public BigDecimal propertyDistribute() {
        System.out.println("財產分配 - 律師的答覆：");
        return lawyer.propertyDistribute();
    }

    @Override
    public BigDecimal getComfortMoney() {
        System.out.println("撫慰金 - 律師的答覆：");
        return lawyer.getComfortMoney();
    }

    @Override
    public BigDecimal getAlimony() {
        System.out.println("贍養費 - 律師的答覆：");
        return lawyer.getAlimony();
    }
}
```
<br/>

###### Step 5
最後於 `ExPartner` 用戶端建立服務實例，而實際呼叫的對象其實是代理類。
```java
public class ExPartner {
    public static void main(String[] args) {
        DivorceService service = new LawyerProxy();
        service.propertyDistribute();
        service.getComfortMoney();
        service.getAlimony();
    }
}
```

![](/images/proxy-7.png)

## 小結
> Q：Proxy Pattern 跟 Decorator Pattern 很像，都是將某個物件包再另一個裡面，他們之間的差異???
   * Decorator Pattern - 為了幫其他類別添加行為 ( 包含另一個物件，並為它提供額外的型為 )
   * Proxy Pattern - 為了控制內部執行類別與外界的接觸 ( 包裝另一個物件來控制針對它的接觸 )
<br/>

> Q：Adapter Pattern 跟 Proxy Pattern 看起來也很像，都是在其他物件的前面將請求轉傳給他們???
  * Adapter Pattern - 會改變被轉接的物件的介面 ( 包裝另一個物件，並為它提供不同的介面 )
  * Proxy Pattern - 只會實作相同的物件介面

#### 優點
1. 可以擴充目標物件的功能
2. 將 Client 端跟目標物件分離，降低系統的耦合，增加程式的可擴充性。

#### 缺點
1. 增加系統中類別的數量。
2. Client 端和目標物件中間多了一個帶禮物建，造成請求處理速度變慢。
3. 系統的複雜度提高。

![](/images/icon-question.png) Proxy Pattern 一定都要從 Client 端建立實際的服務對象傳入??? [範例](https://matthung0807.blogspot.com/2020/11/java-proxy-pattern.html)

## 其他代理
| 代理類型 | 說明 | 實際應用 |
| --- | --- | --- |
| 動態代理 | 實際執行的類別在執行時奇才依照傳入的一組介面動態建立的。 |
| 防火牆代理 ( `Firewall Proxy` ) | 控制外界對一組網路資源的接觸，防止對象被不良的用戶端接觸。 | 公司的防火牆系統。
| 智慧參考代理 ( `Smart Reference Proxy` ) | 當對象被參考時，提供額外的動作，例如計算指向某個物件的參考數量。 | 
| 快取代理 ( `Caching Proxy` ) | 為昂貴的計算結果提供臨時性的儲存空間。它也可以讓多個用戶端共享結果，以減少計算量或網路延遲。 | 通常用在網路伺服器代理與內容管理或是發佈系統中。
| 同步代理 ( `Synchronization Proxy` ) | 可讓多個執行緒安全的接觸同一個對象。 | Collections 的多執行緒環境中，控制外界與底層物件集合的同步接觸。
| 複雜性隱藏代理 ( `Complexity Hiding Proxy` ) | 可隱藏一組類別的複雜性，並控制外界與它們接觸。  
| 寫入時複製代理 ( `Copy-On-Write Proxy` ) | 控制外界對於一個物件的複製，它會推遲外界對物件的複製，直到用戶端真的需要時才執行，是虛擬代理的變化型。 | Java 的 CopyOnWriteArrayList 就是用此模式建構的。

## 參考
* https://refactoring.guru/design-patterns/proxy
* https://github.com/ChiungjuCheng/SpringSecurity/blob/main/0_%E5%8E%9F%E7%90%86/0_1_Filter%E6%9E%B6%E6%A7%8B.md
* https://ithelp.ithome.com.tw/articles/10245445
* O'REILLY - 深入淺出設計模式
