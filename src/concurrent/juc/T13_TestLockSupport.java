package concurrent.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 停车
 * 对标：wait、sleep，不需要加锁，trycatch
 * 且只能提前存一个可释放的车位
 */
public class T13_TestLockSupport {
    public static void main(String[]args){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 10; i++){
                    System.out.println(i);
                    if (i == 5 || i == 7){
                        LockSupport.park();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        LockSupport.unpark(t);

        try {
            TimeUnit.SECONDS.sleep(9);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(t);
    }
}
