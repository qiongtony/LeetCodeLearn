package basis.referencetype;

import java.lang.ref.WeakReference;

/**
 * 弱引用的应用：
 * ThreadLocal：Thread里的ThreadLocalMap存的key是ThreadLocal，而且用的是弱引用
 *
 */
public class T03_WeakReference {
    public static void main(String[]args){
        WeakReference<M> m  = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }

    /**
     * 打印：
     * basis.referencetype.M@1b6d3586
     * null
     * finalize
     */
}
