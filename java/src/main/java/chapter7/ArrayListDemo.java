package chapter7;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/7/11.
 */
public class ArrayListDemo {
    public static void main(String args[]) {
        ArrayList a1 = new ArrayList();
        a1.add(new Person("liyang", 27));
        a1.add(new Person("liyang1", 27));
        a1.add(new Person("liyang2", 23));
        Iterator it = a1.iterator();
        while (it.hasNext()) {
//            System.out.println(((Person)it.next()).getName() +"  " + ((Person)it.next()).getAge());
            Person p = (Person) it.next();
            System.out.println(p.getName() + "  " + p.getAge());
        }

        Person p1 = new Person("liyang1", 27);
        Person p2 = new Person("liyang1", 27);

        Integer a = 1;
        Integer b = 1;

        System.out.println(a == b);
        System.out.println(a.equals(b));

    }
}
