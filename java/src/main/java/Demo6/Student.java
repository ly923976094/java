package Demo6;

/**
 * Created by yodo1 on 17/4/17.
 */
public class Student {
    private int score;
    private  String name;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}


class Demo1{
    public static void main(String[] args){
        Student student = new Student();
        student.setName("liyang");
        student.setScore(91);
        System.out.println(student);
    }
}