package concurrent.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * 倒计时，倒计时完了await结束
 */
public class T04_TestCountDownLatch {
    public static void main(String[]args){
        usingJoin();
//        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = 0;
                    for (int j = 0;j < 10000; j++){
                        result+=j;
                    }
                    countDownLatch.countDown();
                }
            });
        }

        for (int i = 0; i < threads.length; i++){
            threads[i].start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("end countDown");

    }

    /**
     * 这样也可以实现，其实不太好用，join是用在B等A完成继续执行的情况
     */
    public static void usingJoin(){
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = 0;
                    for (int j = 0;j < 10000; j++){
                        result+=j;
                    }
                }
            });
        }

        for (int i = 0; i < threads.length; i++){
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("end join");
    }


}
