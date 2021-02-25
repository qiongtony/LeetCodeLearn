package concurrent.juc;

import java.util.concurrent.Exchanger;

/**
 * Exchanger
 * 线程间交换数据：交换时是阻塞的（类似于生产者-消费者，只是篮子只有一个）
 * 特点：只能在两个线程间，两个以上就随机，而且落下的就一直阻塞住了
 */
public class T03_TestExchanger {
    public static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = "t1";
                try {
                    s = exchanger.exchange(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + s);
            }
        }, "T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = "t2";
                try {
                    s = exchanger.exchange(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + s);
            }
        }, "T2").start();
    }
}
