package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedDemo2 {
//    Object object = new Object();
//    public void method1() {
//        synchronized (object) {
//
//        }
//    }
    public static String test03(String a, String b, String c){
        // 用StringBuilder，然后append实现
        String s = a + b + c;
        return s;
    }

    public static String test04(String a, String b, String c){
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(b);
        sb.append(c);
        return sb.toString();
    }
}
