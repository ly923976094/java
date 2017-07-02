package chapter5;

/**
 * Created by Administrator on 2017/6/19.
 */

/**
 * Created by Administrator on 2017/6/13.
 *
 * 为甚么操作线程的方法 wait，notify，notifyAll 定义在object类中
 * 因为这些方法时间时期的方法，监视器其实就是
 */
class Resource1 {
    private String name;
    private String sex;
    private Boolean flag = false;
    public synchronized void set(String name, String sex) {
        if(flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        this.name = name;
        this.sex = sex;
        flag = true;
        notify();
    }


    public synchronized void out() {
        if(!flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println( "Resource1{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}');
        flag = false;
        notify();

    }
}

class Input1 implements Runnable {
    Resource1 r;

    Input1(Resource1 r) {
        this.r = r;
    }

    public void run() {
        int x = 0;
        while (true) {
            synchronized (r) {

                if (x == 0) {
                    r.set("liyang","man");
                } else {
                    r.set("xudai","women");
                }

            }
            x = (x + 1) % 2;
        }
    }
}

class Output1 implements Runnable {
    Resource1 r;

    Output1(Resource1 r) {
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
                 r.out();
            }
        }
    }
}

public class ResourceDemo {
    public static void main(String[] args) {

        Resource1 r = new Resource1();
        Input1 in = new Input1(r);
        Output1 out = new Output1(r);
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);
        t1.start();
        t2.start();

    }
}