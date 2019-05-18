package com.stark.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @auther leon
 * @create 2019/5/3 22:35
 */


class Data {
    //    int number = 0;

    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }
}

/**
 *  测试可见性    变量没有被volatile修饰，没有可见性
 * 这里一定要让线程AAA睡3秒，模拟计算过程。
 */
public class VolatileTest {
    public static void main(String[] args) {
        seeOkByVolatile();

    }

    /**
     * 1 验证volatile的可见性
     *      1.1 volatile可以保证可见性，及时通知其他线程，主内存的变量已经被修改。
     *      1.2 volatile可以解决可见性问题。
     *
     * 2 验证volatile不保证原子性
     *      2.1
     */
    private static void seeOkByVolatile() {
        Data data = new Data();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "  come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo60();
            System.out.println(Thread.currentThread().getName() + data.number);

        }, "AAA").start();

        while (data.number == 0) {

        }
        //System.out.println(Thread.currentThread().getName()+"mission is over "+data.number);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + data.number);
        }, "BBB").start();
    }
}
