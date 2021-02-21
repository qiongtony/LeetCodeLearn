package basis.referencetype;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * 虚引用
 * 管理堆外内存：虚引用对象被回收了->在引用队列获取到它，对象不为空说明对象被回收了，所以相关联的堆外内存也不需要了，这时候对堆外内存进行回收
 */
public class T04_PhantomReference {
    public static List<Object> LIST = new ArrayList<>();
    private static ReferenceQueue QUEUE = new ReferenceQueue();

    public static void main(String[] args){
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(()->{
            while (true){
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() ->{
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null){
                    System.out.println("虚引用对象被JVM回收了 -----" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 内存为20M时的打印
    /**
     * null
     * null
     * null
     * null
     * null
     * null
     * null
     * null
     * null
     * null
     * finalize
     * null
     * null
     * null
     * null
     * null
     * 虚引用对象被JVM回收了 -----java.lang.ref.PhantomReference@2f791ac9
     * null
     * null
     */
}
