package Demo8;

/**
 * Created by yodo1 on 17/5/4.
 */
abstract class Tuxing {

    //求面积，交给子类完成
    abstract double getArea();

    abstract double getZC();

    public void p() {
        System.out.println("周长： " + getZC());
        System.out.println("面积：" + getArea());
    }
}

class Juxing extends Tuxing {
    private int a;
    private int b;

    public Juxing(int a, int b) {
        this.a = a;
        this.b = b;
    }


    double getArea() {
        return 2 * (a + b);
    }

    double getZC() {
        return a * b;
    }


}

class Circle extends Tuxing {

    private int r;

    public Circle(int r) {
        this.r = r;
    }

    double getArea() {
        return r * r * 3.14;
    }

    double getZC() {
        return 3.14 * 2 * r;
    }

}

public class TemplateDemo {
    public static void main(String[] args) {

        Tuxing t = new Juxing(1, 2);
        t.p();

        Tuxing t1 = new Circle(2);
        t1.p();

    }
}
