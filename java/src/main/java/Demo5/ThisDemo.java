package Demo5;

/**
 * author:sun
 * Created by yodo1 on 17/4/4.
 */

class Dog {
    private String type;
    private String color;
    private int age;

    @Override
    public String toString() {
        return "Dog{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    void show() {
        Dog d = new Dog();
        d.type = "萨摩耶";
        say(d);

    }

    void say(Dog d) {
        //System.out.println("--->"+d.getType());//打印:萨摩耶
        System.out.println("--->"+ this.getType());//打印:萨摩耶
    }
    void p()
    {
        //这里的type到底表示谁的品种?
        //谁调用p方法,此时该方法里的this就是谁? 一般情况下,this可以省略
        //方法里有一个和字段同名的局部变量时,不能省略this

        String type = "sss";
        System.out.println(this.type);//表示访问对象(调用方法的对象)的type
    }


}

public class ThisDemo {
    //static 代码块里不能使用this
    private static String name;
    public static void main(String[] args) {
        //System.out.println(this.name);//ERROR
        /**
         this:表示当前对象;
         谁调用方法谁就是当前对象

         */System.out.println(name);

    }
}
