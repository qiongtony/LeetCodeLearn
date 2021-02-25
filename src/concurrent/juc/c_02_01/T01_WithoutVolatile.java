package concurrent.juc.c_02_01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T01_WithoutVolatile {
    // 不可见，且要用同步容器，就是出现脏读
//    List lists = new ArrayList();

    // 错误的实现：volatile是在对象改变时才会更新
    volatile List lists = Collections.synchronizedList(new ArrayList<>());

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args){
        T01_WithoutVolatile withoutVolatile = new T01_WithoutVolatile();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i< 10;i++){
                    System.out.println("add " + i);
                    withoutVolatile.add(new Object());
//                    try {
//                        Thread.sleep(1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (withoutVolatile.size() == 5){
                        break;
                    }
                }
                System.out.println("监控成功！");
            }
        }, "t2").start();
    }
}
