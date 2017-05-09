package Demo7;

/**
 * Created by yodo1 on 17/5/3.
 * 单例模式：保证整个项目运作期间某一个对象有且只有一个
 * 单例模式：
 * 1.饿汉式
 * 2.懒汉式
 */
//饿汉式，线程安全
class Singleton1 {
    private static final Singleton1 instance = new Singleton1();// final加不加无所谓

    public static Singleton1 getInstance(){
        return instance;
    }
    //吧构造方法私有化，不允许外界在创建对象
    private Singleton1() {
    }
}

//懒汉式,线程不安全
class Singleton2{
    private static Singleton2 instance = null;
    public static Singleton2 getInstance(){
        if (instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
    //吧构造方法私有化，不允许外界在创建对象
    private Singleton2() {
    }
}


public class SingleDemo {
    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();
        System.out.println(s1 == s2);

        Singleton2 s3 = Singleton2.getInstance();
        Singleton2 s4 = Singleton2.getInstance();
        System.out.println(s3 == s4);
    }
}
