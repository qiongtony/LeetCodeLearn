package concurrent;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest01 {
    volatile int i;
    // 使用AtmoicInteger保证操作的原子性
    AtomicInteger integer;

    public void addI(){
        // i++不能保证原子性，本质上包含读、写两次操作
        i++;
    }

    public void addAtomicI(){
        integer.getAndIncrement();
    }

    {
        integer = new AtomicInteger(0);
    }

    public static void main(String[] args) throws InterruptedException {
        final  VolatileTest01 test01 = new VolatileTest01();
        for (int n = 0; n < 1000; n++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    test01.addI();
                    test01.addAtomicI();
                }
            }).start();
        }
        Thread.sleep(1000);//等待10秒，保证上面程序执行完成
        // 打印结果比1000少
//        System.out.println(test01.i);
        System.out.println(test01.integer.get());
    }
}
