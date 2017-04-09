package Demo5;

/**
 * 作者：sun
 * Created by yodo1 on 17/4/4.
 */

class Student{
    private String name;
    //默认没有交学费
    private boolean isFees = false;

    Student(String name){
        this.name = name;
    }
    //交学费
    void Fees(){
        isFees = true;//修改学生没有交学费的状态
    }

    void show(){
        System.out.println(name + "-->" + isFees);
    }
}
public class StudentDemo {

    public static void main(String[] args){
        Student s1 = new Student("will");
        Student s2 = new Student("lucy");

        s1.show();
        s2.show();

        //创建一个数组，用着装没有交学费的学生
        Student[] unFees = new Student[]{s1,s2};

        for (Student s : unFees){
            s.Fees();
        }
        s1.show();
        s2.show();
    }

}
