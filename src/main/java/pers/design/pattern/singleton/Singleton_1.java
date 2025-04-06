package pers.design.pattern.singleton;

/**
 * @author memorykghs
 * @date 2025/3/23
 */
public class Singleton_1 {
    /**
     * 沒有惰性初始化
     */
    private static final Singleton_1 SINGLETON = new Singleton_1();

    private Singleton_1(){}

    public static Singleton_1 getInstance(){
        return SINGLETON;
    }
}
