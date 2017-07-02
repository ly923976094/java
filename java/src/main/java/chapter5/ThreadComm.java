package chapter5;


/**
 * Created by Administrator on 2017/6/13.
 *
 * 为甚么操作线程的方法 wait，notify，notifyAll 定义在object类中
 * 因为这些方法时间时期的方法，监视器其实就是
 */
class Resource {
    String name;
    String sex;
    Boolean flag = false;
}

class Input implements Runnable {
    Resource r;

    Object obj = new Object();

    Input(Resource r) {
        this.r = r;
    }

    public void run() {
        int x = 0;
        while (true) {
            synchronized (r) {
                if (r.flag)
                    try {
                        r.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                if (x == 0) {
                    r.name = "liyang";
                    r.sex = "man";
                } else {
                    r.name = "xudaidai";
                    r.sex = "women";
                }
                r.flag = true;
                r.notify();
            }
            x = (x + 1) % 2;
        }
    }
}

class Output implements Runnable {
    Resource r;
    Object obj = new Object();

    Output(Resource r) {
        this.r = r;
    }

    public void run() {
//        Resource r = new Resource();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (r) {
                if(!r.flag)
                    try {
                        r.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                System.out.println(r.name + "   " + r.sex);
                r.flag = false;
                r.notify();
            }
        }
    }
}

public class ThreadComm {
    public static void main(String[] args) {

        Resource r = new Resource();
        Input in = new Input(r);
        Output out = new Output(r);
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);
        t1.start();
        t2.start();

    }
}
