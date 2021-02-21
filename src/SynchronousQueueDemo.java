import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue测试例子
 * 实际上不是一个真正的队列，put会阻塞直到take执行为止
 * 适用于消费者多于生产者的场景
 */
public class SynchronousQueueDemo {
        static class SynchronousQueueProducer implements Runnable {

            protected BlockingQueue<String> blockingQueue;
            final Random random = new Random();

            public SynchronousQueueProducer(BlockingQueue<String> queue) {
                this.blockingQueue = queue;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        String data = UUID.randomUUID().toString();
                        System.out.println(System.currentTimeMillis() + " Put: " + data);
                        blockingQueue.put(data);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        static class SynchronousQueueConsumer implements Runnable {

            protected BlockingQueue<String> blockingQueue;

            public SynchronousQueueConsumer(BlockingQueue<String> queue) {
                this.blockingQueue = queue;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        String data = blockingQueue.take();
                        System.out.println(System.currentTimeMillis() + Thread.currentThread().getName()
                                + " take(): " + data);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        public static void main(String[] args) {
            final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();

            SynchronousQueueProducer queueProducer = new SynchronousQueueProducer(
                    synchronousQueue);
            new Thread(queueProducer).start();

            SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(
                    synchronousQueue);
            new Thread(queueConsumer1).start();

            SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(
                    synchronousQueue);
            new Thread(queueConsumer2).start();

        }
}
