package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock demo
 * 测试：公平锁和非公平锁的区别
 * 公平锁：根据等待时间去获取锁
 * 非公平锁：谁刚好去获取锁就给它。
 */
public class ReentrantLockDemo {

    static class MyThread extends Thread{
        private Lock lock;

        public MyThread(String name, Lock lock){
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
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
//        Lock lock = new ReentrantLock(true);
        Lock lock = new ReentrantLock(false);
        lock.newCondition();


        MyThread t1 = new MyThread("t1", lock);
        MyThread t2 = new MyThread("t2", lock);
        MyThread t3 = new MyThread("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }
}
