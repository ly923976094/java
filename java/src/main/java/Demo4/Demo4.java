package Demo4;

/**
 * 作者：sun
 * 功能：类的使用
 * Created by yodo1 on 17/3/19.
 */
public class Demo4 {
    public static void main(String args[]) {

        Person p = new Person();
        p.age = 10;
        p.name = "liyang";

        Person b;
        b = p;
        System.out.print(b.name);

        Person c;
        c = b;
        System.out.println(c.name);
        c.age=9;
        System.out.println(c.age);

    }
}


class Person {
    int age;
    String name;
}
