package concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class Test {
    public static void main(String [] args){
        FooBar fooBar = new FooBar(50);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    fooBar.bar(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("bar");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    fooBar.foo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("foo");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 解法：可重入锁ReentrantLock+Condition+volatile
     * ReentrantLock可重入锁，同时可添加多个Condition去释放/唤醒锁，内部锁的加强版
     */
    static class FooBar {
        private int n;
        private ReentrantLock reentrantLock = new ReentrantLock();
        private Condition foo = reentrantLock.newCondition();
        private volatile boolean flag = true;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; ) {
                try {
                    reentrantLock.lock();
                    if (flag){
                        // printFoo.run() outputs "foo". Do not change or remove this line.
                        printFoo.run();
                        i++;
                        flag = false;
                    }else{
                        // 可以看出Condition的await会缩放可重入的锁
                        foo.await();
                    }
                }finally {
                    reentrantLock.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n;) {
                try {
                    reentrantLock.lock();
                    if(!flag){
                        // printBar.run() outputs "bar". Do not change or remove this line.
                        printBar.run();
                        i++;
                        flag = true;
                        foo.signal();
                    }else{

                    }
                }finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    static class ZeroEvenOdd {
        private int n;
        private AtomicInteger atomicInteger;
        private ReentrantLock reentrantLock;
        private Condition evenAndOdd;
        private Condition zero;
        private volatile boolean zeroBoolean = true;

        public ZeroEvenOdd(int n) {
            this.n = n;
            atomicInteger = new AtomicInteger(n * 2);
            reentrantLock = new ReentrantLock();
            evenAndOdd = reentrantLock.newCondition();
            zero = reentrantLock.newCondition();
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            try {
                reentrantLock.lock();
                if (zeroBoolean){
                    printNumber.accept(0);
                    zeroBoolean = false;
                    evenAndOdd.signal();
                }else{
                    zero.wait();
                }
            }finally {
                reentrantLock.unlock();
            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            try {
                reentrantLock.lock();
                if (atomicInteger.get() % 2 == 1){
                    printNumber.accept( 2 * n - atomicInteger.getAndDecrement());
                    zero.signal();
                }else{
                    evenAndOdd.wait();
                }
            }finally {
                reentrantLock.unlock();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            try {
                reentrantLock.lock();
                if (atomicInteger.get() % 2 == 0){
                    printNumber.accept( 2 * n - atomicInteger.getAndDecrement());
                    zero.signal();
                }else{
                    evenAndOdd.wait();
                }
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    static class ConcurrentTest{
        void test(){
            ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
            concurrentHashMap.put("11", "22");
        }
    }
}
