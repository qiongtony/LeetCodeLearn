package concurrent.juc;

import java.util.concurrent.Semaphore;

/**
 * Semaphore：信号量
 * 同时可以有多少个线程可以执行，可以设置锁是非公平还是公平
 * 用处：限流
 */
public class T02_TestSemaphore {
    public static void main(String[] args){
//        Semaphore s = new Semaphore(1);
        Semaphore s = new Semaphore(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.acquire();
                    System.out.println("T1 running...");
                    Thread.sleep(200);
                    System.out.println("T1 running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s.release();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.acquire();
                    System.out.println("T2 running...");
                    Thread.sleep(200);
                    System.out.println("T2 running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s.release();
                }
            }
        }).start();
    }
}
