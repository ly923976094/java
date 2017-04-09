package Demo5;

/**
 * author：Sun
 * Created by yodo1 on 17/4/4.
 */

class Cat{
    private String name;
    private int age;
    private String color;

    public Cat(String name) {
        this.name = name;
    }

    public Cat(int age) {
        this.age = age;
    }

    public Cat(String name, int age) {
        //this.name = name;
        this(name);//ConstructerDemo.java:26: 错误: 对this构造器的调用必须是构造器中的第一个语句
        this.age = age;
    }

    public Cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }


}
public class ConstructerDemo {

    public static void main(String[] args){
        //show();
        //this.show();
        new ConstructerDemo().show();

    }
    public void show(){

        this.hi();
    }

    public static  void hi(){
        //
    }
}
