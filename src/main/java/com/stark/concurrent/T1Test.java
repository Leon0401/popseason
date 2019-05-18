package com.stark.concurrent;

/**
 * @auther leon
 * @create 2019/5/10 19:49
 */
public class T1Test {
    volatile int n =0;
    public void add(){
        n++;
    }

    public static void main(String[] args) {
        T1Test test = new T1Test();
        test.add();
    }
}
