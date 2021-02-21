package basis.referencetype;

import java.lang.ref.SoftReference;

/**
 * 软引用适合做内存
 */
public class T02_SoftReference {
    public static void main(String[]args){
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        //再分配一个数组，如果不够会把软引用干掉
        // 实践，为啥是爆OOM而不是回收呢？
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }
}
