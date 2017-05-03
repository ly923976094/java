/**
 * Created by yodo1 on 17/4/20.
 */


class Father{

    protected Father(){}
    public Father(String name){

        System.out.println("有参数");
    }
}
class Sub extends Father{

    public Sub(){

    }
    public Sub(String name){
        super(null);//显示的写出这里隐士的调用父类的构造方法
        System.out.println("Sub");
        /*

         */
    }
}

public class NewInstanceSub {
    public static void main(String[] aegs) {
        Sub s = new Sub("liyang");
    }
}
