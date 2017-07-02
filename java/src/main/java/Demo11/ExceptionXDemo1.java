package Demo11;

/**
 * Created by Administrator on 2017/5/17.
 */
public class ExceptionXDemo1 {
    public static void divide(int a, int b) {
        int c = a / b;
    }

    /**
     * try catch
     *
     * @param arsg
     */
    public static void main(String[] arsg) {

        System.out.println("begin");
        try {
            divide(1, 0);
        } catch (ArithmeticException e) {
           System.out.println("e= " + e);
        }
        System.out.println("end");

    }
}
