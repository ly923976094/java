package Demo6;

/**
 * Created by yodo1 on 17/4/20.
 */

//鸟类
class Bird {
    public void fly() {
        System.out.println("我在飞");
    }
}

//乌鸦
class WUBird extends Bird {
/*

 */
public void show(Object o){

 }

}

//鸵鸟
class TOBird extends Bird {
    /*
    当父类的某一
    个行为不适合某一个子类的时候，子类应当把这个方法重载定义声明
     */

    @Override
    public void fly() {
       System.out.println("劳资太胖，飞不起来");
    }

}

public class OverrideDemo {
    public static void main(String[] args) {
        WUBird wu = new WUBird();
        wu.fly();
    }
}
