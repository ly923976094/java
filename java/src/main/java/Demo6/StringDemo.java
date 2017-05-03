package Demo6;

/**
 * Created by yodo1 on 17/5/2.
 */
public class StringDemo {

    /*
      String和基本类型之间的转换
      String int
     */
    public static void main(String[] args) {
        int i = Integer.parseInt("007");
        System.out.println(i);

        String s = Integer.toString(123);
        s = String.valueOf(123);

       // s = (String)i;
        System.out.println(s);


        System.out.println(new Object().equals(new Object()));//false
        System.out.println(new Integer(1).equals(new Integer(1)));//true
        System.out.println(new String().equals(new String()));

        System.out.println(new Object().hashCode());

        Object o1 = new Object();
        System.out.println(o1.hashCode());
        System.out.println(o1.hashCode());

        /*
        我们打印对象，其实打印的是对象的toString()方法
         */
        System.out.println(new StringDemo());

    }


}
