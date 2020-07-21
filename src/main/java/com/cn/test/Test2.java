package com.cn.test;

/**
 * Created by Administrator on 2020-05-27.
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        /*
        下面这么写是有问题的，因为这是多线程，当main线程执行完了，其他线程还没执行完，
        代码就已经到end那了，这样时间就不对了，算的只是main线程的时间。
         */
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }

        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));

    }
}
