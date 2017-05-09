package Demo8;

/**
 * Created by yodo1 on 17/5/9.
 */


class Outer{
    private String name="outer";
    class Inner{
        private String name = "inner";
        public void test(){
            String name = "test";
            System.out.println(name);
            System.out.println(this.name);
        }
    }

    public void show(){
        //想执行内部类的test方法
        new Inner().test();
    }
}
public class InnerDemo {

    public static void main(String[] args){
        new Outer().show();
    }
}
