package pers.design.pattern.singleton;

/**
 * @author memorykghs
 * @date 2025/3/23
 */
public class Singleton_2 {
    private static final Singleton_2 singleton = new Singleton_2();

    private Singleton_2(){}

    public static Singleton_2 getInstance(){
        return singleton;
    }
}
