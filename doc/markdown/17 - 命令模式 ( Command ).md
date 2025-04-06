# 17 - 命令模式 ( Command )

## Command Pattern 
命令模式將請求封裝成物件，讓使用者將請求、佇列 ( Queue ) 或紀錄等物件參數畫，並支援可復原的操作 ( unexecute )。

1. Command 物件藉著將準備送給 Receiver 的一組行動綁在一起，來封裝請求。<br/>

![](/images/command-3.png)

2. Command 物件將動作和 Receiver 都包在裡面，且只公開一個方法供外部使用 ( execute )。外部不會知道哪個 Receiver 被呼叫執行，只知道呼叫 `execute()` 方法後請求會被處理。

![](/images/command-4.png)

## UML
![](/images/command-1.png)

## 實際應用

## 實作
![](/images/command-2.png)

* `Light` ( Receiver )
```java
public class Light {
	
    private String switchStatus = "on";

    public String getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(String switchStatus) {
        this.switchStatus = switchStatus;
    } 
}
```

* Command and Concrete Command
  * `Command` ( Interface )
  ```java
  public interface Command {

    public void execute();

    public void unexecute();
  }
  ```

  * `LightOnCommand` ( Concrete Command )
  ```java
  public class LightOnCommand implements Command{
	
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.setSwitchStatus("on");
        System.out.println("Light is turned on.");
    }

    @Override
    public void unexecute() {
        // TODO Auto-generated method stub
    }
  }
  ```

  * `LightOffCommand` ( Concrete Command )
  ```java
  public class LightOffCommand implements Command {
	private Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.setSwitchStatus("off");
		System.out.println("Light is turned off.");
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
	}
  }
  ```
<br/>
 
* `RemoteControl` ( Invoker )
```java
public class RemoteControl {

private Command on;

    private Command off;

    public RemoteControl(Command on, Command off) {
        this.on = on;
        this.off = off;
    }

    public void lightOn() {
        on.execute();
    }

    public void lightOff() {
        off.execute();
    }
}
```
<br/>

* `Client`
```java
public class Client {

    public static void main(String[] args) {
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);
        
        RemoteControl remoteControl = new RemoteControl(lightOn, lightOff);
        remoteControl.lightOn();
        remoteControl.lightOff();
    }
}
```

結果：
```
Light is turned on.
Light is turned off.
```

* [進階版：實現遙控器上的多個功能]()
  在 `com.team.command.multiCommand` package 下

## 小結

#### 優點


#### 缺點


## 參考