package concurrent.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 写互斥
 * 读不互斥
 */
public class T01_TestReadWriteLock {
    static Lock lock = new ReentrantLock();
    public static int value;

    // 读写锁，写互斥，读不互斥
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            // 模拟读取操作
            Thread.sleep(1000);
            System.out.println("read over");
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v){
        try {
            lock.lock();
            // 模拟写操作
            Thread.sleep(1000);
            value = v;
            System.out.println("write over");
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 18;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    read(readLock);
                }
            }).start();
        }
        for (int i = 0; i < 2;i++){
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    write(writeLock, finalI);
                }
            }).start();
        }
    }
}
