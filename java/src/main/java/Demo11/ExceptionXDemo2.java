package Demo11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017/5/17.
 */
public class ExceptionXDemo2 {


    public static void main(String[] arsg) {
        divide1("3","0");

        try {
            new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param a
     * @param b
     */

    private static void divide1(String a, String b) {
        try {
            Integer c = new Integer(a) / new Integer(b);
        }catch (Exception e){
            System.out.println("0" + e.getStackTrace());
        }

    }
}
