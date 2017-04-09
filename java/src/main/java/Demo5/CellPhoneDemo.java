package Demo5;

/**
 * 定义一个类
 * class 类名{
 * <p>
 * <p>
 * <p>
 * }
 * <p>
 * <p>
 * Created by yodo1 on 17/4/3.
 */

class CellPhone {

    //显示的写出默认的构造器
    CellPhone() {
    }

    //只要显示的写了构造方法，建议一个保证有一个无参的构造方法
    CellPhone(String a1, String a2, Double a3) {
        this.pp = a1;
        this.color = a2;
        this.price = a3;
        System.out.println("------------------");
    }

    String pp;//品牌
    String color;//颜色
    double price;//价格

    public void setPp(String pp) {
        this.pp = pp;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getPp() {
        return pp;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CellPhone{" +
                "pp='" + pp + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }


}


class Person1 {
    /*

特点：
			随着类的加载而加载
			优先于对象存在
			被所有对象所共享
			可以直接被类名调用

    没有使用static修饰的方法或字段，属于对象 ---》 实列成员


    使用static修饰的方法或字段属于类   --》 类成员

    调用：
       若是实力成员：    只能通过对象来调用
       若是类成员：   可以使用类名调用；   也可以使用对象来调用

                     建议使用类名来调用，其实在底层对象调用类成员，也会转成类名调用


                     static int num = 100; //表示人类的总数
                     在ＪＶＭ内存区中有一块存储区域:  静态区域,专门存储类成员
    * */

 //   static Person1(){};
    String name;
    int age;
    static int num = 100;
}


class StaticDemo {
    public static void main(String[] args){
    System.out.println("通过类名来调用类成员=" + Person1.num);
    }
}

public class CellPhoneDemo {

    //显示的写出默认的构造器
    CellPhoneDemo() {
    }


    public static void main(String[] args) {

        CellPhone p = new CellPhone();
        p.setColor("red");
        p.setPp("apple");
        p.setPrice(12500.0);
        System.out.println(p);
        p = new CellPhone("apple", "red", 12500.0);
        System.out.println(p);

        /*
        方法的调用：
        1.对象调用： 对象.方法(实参)
        2.类调用： 这个方法所在的类的名字.方法(实参);
                //前提：这个被调用的方法必须使用static修饰
        * */

        System.out.println("通过类名来调用类成员= " + Person1.num);  //类直接调用
        //System.out.println("通过类名来调用实例成员= " + Person1.age);
        System.out.println("通过对象来调用实例成员= " + new Person1().age);//对象调用
        System.out.println("通过对象来调用类成员= " + new Person1().num);


        //故意使用对象来调用类成员

        Person1 p1  = new Person1();

        //p1去修改人类总数
        p1.num = 95;

        System.out.println("p1.num= " + p1.num);//95
        Person1 p2 = new Person1();

        System.out.println("p2.num= " + p2.num);//


        //对方法或字段只进行一次调用时；
        //new Person1();表示匿名对象,没有名字的对象

        new Person1().age = 17;//使用一次之后就被销毁了
        System.out.println(new Person1().age);//0

        //==  判断对象时候是同一个对象,其实比较的是  堆里面的地址
        System.out.println("p1 == p1" +(p1 == p1));//true
        System.out.println("new Preson() == new Person1()" + (new Person1() == new Person1()));//false

    }
}
