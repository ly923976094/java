package Demo6;

import java.util.Date;

/**
 * Created by yodo1 on 17/4/17.
 * import 导入的使你用到的类
 */
public class Test1 {

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5};
        Date d = new Date(1L);
        System.out.println(d.getClass());//输出导入的类型


        //java.lang.reflect.Type


    }
}

class Demo {


    private int a;

    public Demo() {   //构造方法的访问权限从语法上不限制

    }
}
