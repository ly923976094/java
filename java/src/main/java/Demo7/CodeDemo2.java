package Demo7;

/**
 * Created by yodo1 on 17/5/3.
 *
 * 静态代码块：就是在构造代码块之前，加上一个static关键字
 */
public class CodeDemo2 {

    public CodeDemo2() {
        System.out.println("构造方法");
    }

    {
        System.out.println("构造代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public static void main(String[] args){

        System.out.println("main");
        new CodeDemo2();
        new CodeDemo2();
        new CodeDemo2();
        System.out.println("hell world");

    }
}
