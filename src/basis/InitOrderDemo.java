package basis;

/**
 * 执行顺序：
 * 静态变量/静态语句块：从上往下
 * 静态变量/静态语句块>普通语句块>构造方法
 *
 * 父子的执行顺序
 * 父静态变量/静态语句块->子静态变量/静态语句块->父普通语句块->父构造方法->子普通语句块->子构造方法
 *
 */
public class InitOrderDemo {

    public static void main(String[] args){
        Father father = new Son();
    }

    public static class Father{
        public static String staticField = "Father静态变量";
        static {
            System.out.println("Father的静态代码块");
        }
        public String field = "Father实例变量";
        {
            System.out.println("Father普通语句块");
        }

        public Father() {
            System.out.println("Father构造方法");
        }

        static{
            System.out.println("Father的静态代码块2");
        }
    }

    public static class Son extends Father{
        public static String staticField = "Son静态变量";
        static {
            System.out.println("Son的静态代码块");
        }
        public String field = "Son实例变量";
        {
            System.out.println("Son普通语句块");
        }

        public Son() {
            System.out.println("Son构造方法");
        }

        static{
            System.out.println("Son的静态代码块2");
        }
    }
}
