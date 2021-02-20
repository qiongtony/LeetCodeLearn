package basis;

public class StringDemo {
    public static void main(String[] args){
        // String是不可变类
        String a1 = "haha";
        // new String("xxx")相当于创建了两个对象，一个是String Pool对象，一个是new的String对象，char数组和hash值用缓存池的
        String a2 = new String(a1);
        System.out.println("a1 = " + a1 + " a2 = " + a2 + "a1.hash == a2.hash ? " + (a1.hashCode() == a2.hashCode()));
        a2 = "test";
        System.out.println("a1 = " + a1 + " a2 = " + a2);
    }
}
