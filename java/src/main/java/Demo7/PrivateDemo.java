package Demo7;

import java.lang.reflect.Constructor;

/**
 * Created by yodo1 on 17/5/3.
 */
class Cat {
    /*
    反射可以创建private修饰的构造方法的对象

    项目开发经常有这样的一个需求

    某一个对象，只需要存在一个就ok了，有且只有一个对象

     */
    private Cat() {
      System.out.println("cat");
    }
}

public class PrivateDemo {
    public static void main(String[] args) {


        try {
            //反射
            Class<Cat> clz = Cat.class;
            Constructor<Cat> c =  clz.getDeclaredConstructor();
            c.setAccessible(true);
            Cat cat = c.newInstance();
            System.out.println(cat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
