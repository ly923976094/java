package Demo8;

/**
 * Created by yodo1 on 17/5/8.
 */

interface ICellPhone{
    void sendMsg();
}

class Android implements ICellPhone{
    public void sendMsg() {
        System.out.println("Android sen message");
    }
}


class ITCAST implements ICellPhone{
    public void sendMsg() {
        System.out.println("传智播客发送短信");
    }
}

class IPhone implements ICellPhone{
    public void sendMsg() {
        System.out.println("apple 发送短信");
    }
}
class CellPhoneFactory{
    //造手机
    public static ICellPhone getInstance(String type){

        ICellPhone p = null;
        if ("iphone".equals(type)) {
            return new IPhone();
        }else if("android".equals(type)){
            return new Android();
        }
        return p;
    }
}
/**
 *
 */
public class USBDemo3 {
    public static void main(String[] args){
        ICellPhone p = new Android();
        p.sendMsg();

        p = CellPhoneFactory.getInstance("iphone");
        p.sendMsg();

    }
}
