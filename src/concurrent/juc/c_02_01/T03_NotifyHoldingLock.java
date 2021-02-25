package concurrent.juc.c_02_01;

import java.util.ArrayList;
import java.util.List;

/**
 * 关键点：
 * notify不会释放锁
 */
public class T03_NotifyHoldingLock {
    volatile List lists = new ArrayList<>();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args){
        T01_WithoutVolatile withoutVolatile = new T01_WithoutVolatile();
        Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public  void run() {
                synchronized(lock) {
                    System.out.println("t2运行");
                    if (withoutVolatile.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("监控成功！");
                }
            }
        }, "t2").start();


        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1运行");
                synchronized(lock) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("add " + i);
                        withoutVolatile.add(new Object());
                        if (withoutVolatile.size() == 5)
                        {
                            lock.notify();
                        }
                    }
                }
            }
        }, "t1").start();
    }
}
