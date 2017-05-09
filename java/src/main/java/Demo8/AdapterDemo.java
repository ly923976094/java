package Demo8;

/**
 * Created by yodo1 on 17/5/8.
 */

interface IWindow {
    void max();

    void min();

    void close();
}


//此时可以考虑使用适配器模式，abstract
abstract class WindowAdapter implements IWindow {

    public void max() {

    }

    public void min() {

    }

    public void close() {

    }
}

class myWindow extends WindowAdapter {

    @Override
    public void close() {
        System.out.println("关闭我的小窗口");
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        IWindow win = new myWindow();
        win.close();

    }
}
