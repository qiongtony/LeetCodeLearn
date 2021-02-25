package basis;

import java.util.Objects;

/**
 * 测试浅拷贝和深拷贝
 * 浅拷贝：
 * 基本数据类型拷贝值，引用数据类型，不创建新对象，指向原地址
 *
 * 深拷贝：
 * 基本数据类型拷贝值，引用数据类型，创建新对象，并复制其内容
 *
 * 区别：
 * 对引用数据类型的处理，是不是新建对象复制值还是用原对象
 *
 */
public class CopyDemo {

    public static void main(String[] args){

        int n = 100 - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println("n = " + n);

        Father father = new Father();
        Child child = new Child();
        father.age = 50;
        father.name = "张三";
        child.age = 30;
        father.child = child;

        Father fatherClone = null;
        try {
            fatherClone = (Father) father.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("father == fatherClone ? " + (father == fatherClone));
            System.out.println("father.hash = " + father.hashCode() + " fatherClone.hash = " + fatherClone.hashCode());
            System.out.println("father.name = " + father.name + " fatherClone.name = " + fatherClone.name);
            System.out.println("child == fatherClone.child ? " + (child == fatherClone.child));
            System.out.println("child.hash = " + child.hashCode() + " fatherClone.child.hash = " + fatherClone.child.hashCode());


    }


    static class Father implements Cloneable{
        public int age;
        public String name;
        public Child child;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Father clone = (Father) super.clone();
            // 不做该操作，则Father是浅拷贝，做了，则是深拷贝
//            clone.child = (Child) clone.child.clone();
            return clone;
        }
    }
    static class Child implements Cloneable{
        public int age;
        public String name;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        /*@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Child)) return false;
            Child child = (Child) o;
            return age == child.age &&
                    Objects.equals(name, child.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }*/
    }
}
