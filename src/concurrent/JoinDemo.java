package concurrent;

public class JoinDemo {
    public static void main(String[] args){
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }

    private static class A extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A start");
            for (int i = 0; i < 1000; i++){
                System.out.print(i + " ");
                if (i % 100 == 0){
                    System.out.println();
                }
            }
            System.out.println("A end");
        }
    }

    private static class B extends Thread{

        private A a;

        public B(A a){
            this.a = a;
        }

        @Override
        public void run() {
            super.run();
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    // synchronized关键字的使用

    public static class SynchronizedObjectLock implements Runnable {
        static SynchronizedObjectLock instence = new SynchronizedObjectLock();

        @Override
        public void run() {
            // 同步代码块形式——锁为this,两个线程使用的锁是一样的,线程1必须要等到线程0释放了该锁后，才能执行
            synchronized (this) {
                System.out.println("我是线程" + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结束");
            }
        }
    }
}
