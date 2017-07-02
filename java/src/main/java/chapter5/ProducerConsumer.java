package chapter5;

/**
 * Created by Administrator on 2017/6/19.
 * 生产者，消费者
 */

class Resource3 {
    private String name;
    private int count = 1;
    private Boolean flag = false;


    public synchronized void set(String name) {
        //单生产
//        if (flag)
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        this.name = name + count;
//        count++;
//        System.out.println(Thread.currentThread().getName() + "...produce..." + this.name);
//        flag = true;
//        notify();

        //多生产
        while (flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        this.name = name + count;
        count++;
        System.out.println(Thread.currentThread().getName() + "...produce..." + this.name);
        flag = true;
        notifyAll();
    }

    public synchronized void out() {
        //单消费
//        if (!flag)
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        System.out.println(Thread.currentThread().getName() + "...consumer..." + this.name);
//        flag = false;
//        notify();
//    }
        //多消费
        while (!flag)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(Thread.currentThread().getName() + "...consumer..." + this.name);
        flag = false;
        notifyAll();
    }
}

class Producer implements Runnable{

   private  Resource3 r;

    public Producer(Resource3 r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            r.set("烤鸭");
        }
    }
}

class Consumer implements Runnable{
    private Resource3 r;

    public Consumer(Resource3 r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            r.out();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {

        Resource3 r = new Resource3();
        Producer producer1 = new Producer(r);
        Consumer consumer1 = new Consumer(r);
        Producer producer2 = new Producer(r);
        Consumer consumer2 = new Consumer(r);
        Thread t1 = new Thread(producer1);
        Thread t2 = new Thread(producer2);
        Thread t3 = new Thread(consumer1);
        Thread t4 = new Thread(consumer2);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
