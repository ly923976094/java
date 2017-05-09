package Demo7;

/**
 * Created by yodo1 on 17/5/4.
 * 抽象方法：
 * 使用abstract修饰的方法，但没有方法体
 * 只有方法申明，抽象方法强制子类复写
 * 一但一个类有了抽象方法，那么该类也必须声明抽象类
 * 但是抽象类可以没有抽象方法
 *
 * 抽象类必须得有子类，自己功能才能得以运行
 * 抽象类的方法只是定义子类应该具备的行为，但是没有给出怎么去完成行为的功能
 * 交给子类去更具自身特殊情况去完成该父类声明的功能
 *
 * 抽象方法也成为钩子方法，回调方法
 * 抽象类可以有普通方法
 *
 * 抽象类是类的一种特殊情况，具有类的一切属性，但不能实例化，一般带有抽象方法
 */

abstract class TuXing {
    abstract public double getZC();
}

class SanJiaoXing extends TuXing {

    private double a;
    private double b;
    private double c;

    public SanJiaoXing(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getZC() {
        return a + b + c;
    }
}

class JuXing extends TuXing{

    public double getZC() {
        return 0;
    }
}

public class AbstractDemo1 {
    public static void main(String[] args) {

        double ret = new SanJiaoXing(3, 4, 5).getZC();
        System.out.println(ret);
    }
}
