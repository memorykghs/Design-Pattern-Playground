# 05 - 建造者模式 ( Builder )
Builder Pattern（建造者模式）屬於設計模式中Creational Pattern（創建模式）。當物件(object)的建構過程比較複雜或建構的物件有多種樣貌時，可利用Builder Pattern來設計。

Builder Pattern的定義如下，「把一個複雜物件的建構與樣貌分離，如此相同的建構過程可以產生不同樣貌的物件」。

Separate the construction of a complex object from its representation so that the same construction process can create different representations.

「物件的樣貌(object's representation)」



建造者模式用一種 `Configuration` 的方式，拆解每個元件建造的過程，避免建造某個物件的時候需要傳入過多的參數。

* 典型的Builder Pattern包含下列角色。
   * Product：最終要被建構物件的類別。
   * Builder：用來定義建構物件過程中各必要步驟 ( 方法 ) 的介面。
   * ConcreteBuilder：實作 Builder 介面，實際用來建構物件的類別
   * Director：負責指揮 ConcreteBuilder 該如何建構物件。

Builder的各建造方法習慣上會返回自己，這樣的設計方式又稱為Fluent interface（流暢介面）。


> 參考
https://matthung0807.blogspot.com/2019/09/design-pattern-builder-pattern.html 
https://www.tutorialspoint.com/design_pattern/builder_pattern.htm 
