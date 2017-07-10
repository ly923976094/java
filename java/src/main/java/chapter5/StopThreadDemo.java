package chapter5;

/**
 * Created by Administrator on 2017/7/10.
 * 停止线程：
 * 1. stop方法
 * 2. run方法结束
 * 怎么控制线程的任务结束呢？
 * 任务中都会有循环结构
 * 但是如果线程处于冻结状态，无法读取标记，如何结束呢
 *
 * 可以使用interrupt方法将线程从冻结状态恢复到运行状态，让线程具备CPU的执行资格
 *
 * 当时强制动作会发生interruptedException 记得要处理
 */


class StopThread implements Runnable{


    private boolean flag = true;
    @Override
    public synchronized void run() {
        while(flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + e);
            }
            System.out.println(Thread.currentThread().getName() + " +++");
        }
    }

    public void setFlag() {
        this.flag = false;
    }


}
public class StopThreadDemo {
    public static void main(String[] args){

        StopThread st = new StopThread();
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);

        t1.start();
        t2.setDaemon(true);
        t2.start();
        int num = 1;

        for(;;){
            if(++num == 50){
//                st.setFlag();
                t1.interrupt();
//                t2.interrupt();
                break;
            }
            System.out.println("main..." + num);
        }
        System.out.println("over");

    }

}
