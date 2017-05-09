package Demo8;

/**
 * Created by yodo1 on 17/5/8.
 */

interface IPCI{
    void usePCI();
}
interface  IUSB2{


    void swapData();
}

class Input{

}
//定义一个类，按照USB规范实现
class MouseUSBImpl implements IUSB2,IPCI{

    //接口里的方法，全是抽象方法，必须复写
    public void swapData() {

        //接口访问权限默认是public，那么我的实现方法在复写的时候就只能用public
        System.out.println("mouse ");
    }

    public void usePCI() {
        System.out.println("PCI");
    }
}
public class USBDemo2 {
    public static void main(String[] args){
        IPCI mouseUSB = new MouseUSBImpl();
       // mouseUSB.swapData();
        mouseUSB.usePCI();
    }
}
