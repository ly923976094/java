package chapter5;

/**
 * Created by Administrator on 2017/6/12.
 */
//俄汉
class Single1{
    private static Single1 s = new Single1();
    public Single1(){}
    public static  Single1 getInstance(){
        return  s;
    }
}
//懒汉
class Single2{
    private static Single2 s = null;
    public Single2(){}
    public static  Single2 getInstance(){
        if (s==null) {
            synchronized (Single2.class) {
                if (s == null) {
                    s = new Single2();
                }
            }
        }
        return  s;
    }

}
public class SingleDemo {
    public static void main(String[] args) {

    }
}
