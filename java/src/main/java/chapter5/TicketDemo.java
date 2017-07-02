package chapter5;

/**
 * Created by Administrator on 2017/6/12.
 */
class Ticket implements Runnable {
    private int num = 100;
    Object obj = new Object();

    public void run() {
        while (true) {
            synchronized (obj) {
                if (num > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "...sale..." + num--);
                }
            }
        }
    }
}

public class TicketDemo {
    public static void main(String[] args) {
        Ticket t1 = new Ticket();
//        Ticket t2 = new Ticket();
//        Ticket t3 = new Ticket();
        Thread t11 = new Thread(t1);
        Thread t12 = new Thread(t1);
        Thread t13 = new Thread(t1);
        t11.start();
        t12.start();
        t13.start();

    }
}
