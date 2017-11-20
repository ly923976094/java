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

//        System.out.println("begin");
//        try {
//            divide(1, 0);
//        } catch (ArithmeticException e) {
//           System.out.println("e= " + e);
//        }
//        System.out.println("end");
        String s = addToPalindrome("abc","abc".toString().length());
        System.out.println(s);

    }

    public static String addToPalindrome(String A, int n) {
        // write code here
        String returnStr=A.charAt(0)+"";
        String temp;
        for(int i=1;i<A.length();i++){
            temp=A.substring(i,A.length());

            if(checkIsHW(temp)){
                break;
            }
            returnStr=A.charAt(i)+returnStr;
        }

        return returnStr;
    }

    public static boolean checkIsHW(String substr){
        if(substr.length()<=1){
            return true;
        }
        int length=substr.length();
        for(int i=0;i<length/2;i++){
            if(substr.charAt(i)!=substr.charAt(length-i-1)){
                return false;
            }
        }
        return true;
    }


}
