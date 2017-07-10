package chapter5;

/**
 * Created by Administrator on 2017/7/10.
 */

class Demo implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<50; i++) {
            System.out.println(Thread.currentThread().toString() + "....." + i);
            Thread.yield();
        }
    }
}
public class JoinDemo {
    public static void main(String[] args){

        Demo d = new Demo();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);

        t1.start();

//        try {
//            t1.join(); //申请加入进来运行，临时加入一个线程运算时可以使用join方法
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        t2.start();
        t2.setPriority(Thread.MAX_PRIORITY);



        for(int i=0; i<50; i++){
//            System.out.println(Thread.currentThread().toString() + "................." + i);
        }


    }

}