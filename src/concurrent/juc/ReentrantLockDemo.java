package concurrent.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock demo
 * 测试：公平锁和非公平锁的区别
 * 公平锁：根据等待时间去获取锁
 * 非公平锁：谁刚好去获取锁就给它。
 */
public class ReentrantLockDemo {

    static class MyThread extends Thread {
        private Lock lock;

        public MyThread(String name, Lock lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " running");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    static class VolatileTest {
        // volatile不能保证自增自减的原子性->包含三个操作，1、从内存获取值；2、+1；3、更新内存
        // volatile能保证基础赋值、new的原子性（防止指令重排序？）
        private volatile int i = 0;

        public void start() {
            for (int j = 0; j < 5000; j++) {
                i++;
            }
        }

        public int getI() {
            return i;
        }
    }

    // object：wait、notify/notifyAll、join
    // Thread：sleep、yield
    public static void main(String[] args) {
//        Lock lock = new ReentrantLock(true);
        /*Lock lock = new ReentrantLock(false);
        lock.newCondition();

        MyThread t1 = new MyThread("t1", lock);
        MyThread t2 = new MyThread("t2", lock);
        MyThread t3 = new MyThread("t3", lock);
        try {
            t1.wait();
            t1.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(100);
            Thread.yield();
            // 等run完
            t1.join();
            Object o = new Object();
            o.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();
        t1.start();
        t2.start();
        t3.start();*/
        VolatileTest test = new VolatileTest();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                super.run();
                test.start();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                super.run();
                test.start();
            }
        };
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + test.getI());
    }
}
