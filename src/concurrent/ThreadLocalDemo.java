package concurrent;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        // thread1:threadLocal1->1;threadLocal2->1
        // thread2:threadLocal1->2;threadLocal2->2
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("thread 1 start");
                threadLocal1.wait(3000);
            } catch (InterruptedException e) {
                System.out.println("thread 1 e = " + e.toString());
                e.printStackTrace();
            }
            System.out.println("thread 1 end");
            threadLocal1.set(1);
            threadLocal2.set(1);
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread 2 start");
            threadLocal1.notify();
            threadLocal1.set(2);
            threadLocal2.set(2);
            System.out.println("thread 2 end");
        });
        thread1.start();
        thread2.start();
    }
}