package Demo8;

/**
 * Created by yodo1 on 17/5/8.
 */
interface IUSB{

    Integer AGE = 17;

    abstract public void show();
}


public class USBDemo {
    public static void main(String[] args){
        System.out.println(IUSB.AGE);
    }
}
