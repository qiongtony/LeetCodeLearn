package concurrent.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 * 场景：
 * 限流
 * 拼团？
 */
public class T05_TestCyclicBarrier {
    public static void main(String[]args){
        // 栅栏，冲破了这一批就能过去了,所以最多是parties-1个线程在等待
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人了，发车！");
            }
        });
        for (int i = 0; i < 39; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        System.out.println("e = " + e.getMessage());
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        System.out.println("e = " + e.getMessage());
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +  "撞倒了");

                }
            },String.valueOf(i)).start();
        }
    }
}
