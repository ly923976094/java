package Demo6;

/**
 * Created by yodo1 on 17/5/3.
 */
public class ObjectDemo {
    public static void main(String args[]) {
        ObjectDemo od = new ObjectDemo();
        System.out.println(od);
        System.out.println(od.toString());

        System.out.println(new Person("liyang","man",12));
    }
}


class Person {
    private String name;
    private String gender;
    private Integer age;

    public Person(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age
                ;
    }
}