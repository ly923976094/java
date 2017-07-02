package chapter5;

/**
 * Created by Administrator on 2017/6/12.
 */
class Bank {
    private int sum;

    private Object obj = new Object();
    public void add(int num) {
        synchronized (obj) {
            sum += num;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sum=" + sum);
        }
    }

}

//class Bank {
//    private int sum;
//
//    public synchronized void add(int num) {
//            sum += num;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("sum=" + sum);
//    }
//
//}


class Cus implements Runnable {

    private Bank b = new Bank();

    public void run() {
        for (int x = 0; x < 3; x++)
            b.add(100);
    }
}

public class BankDemo {

    public static void main(String[] args) {

        Cus c = new Cus();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        Thread t3 = new Thread(c);
        t1.start();
        t2.start();
        t3.start();

    }
}
