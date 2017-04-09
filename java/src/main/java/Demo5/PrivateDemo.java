package Demo5;

/**
 * author:sun
 * Created by yodo1 on 17/4/4.
 */



class Person{
    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        if(age<=0 || age>130) {
            System.out.println("亲，数据错误");
            return;
           }
        else
             this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsMan() {
        return isMan;
    }

    public void setIsMan(boolean isman) {
        this.isMan = isman;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    //把字段private外界就不能访问
    private int age;
    private String name;
    private boolean isMan;
    void p(){
        System.out.println(age + "-->" + name);
    }
}
public class PrivateDemo {

    public static void main(String[] args){
        Person p = new Person();
        p.setAge(17);
        p.setName("liyang");
        int age = p.getAge();
        System.out.println(p);
    }
}
