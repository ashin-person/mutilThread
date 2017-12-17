package ljx.ashin.thread;

import java.util.Random;

/**
 * 多线程中synchronized的锁对象
 * 如果synchronized使用的锁对象是同一个，那么就会synchronized里面的代码
 * 只能允许一个线程执行
 * Created by AshinLiang on 2017/12/17.
 */
public class SynchronizedTestApp {

    private static SynchronizedTestApp synchronizedTestApp1 = new SynchronizedTestApp();
    private static SynchronizedTestApp synchronizedTestApp2 = new SynchronizedTestApp();

    public static void main(String[] args) {
        //第一个线程
        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("myThread1");
                System.out.println("第一个线程开始了。。。");
                synchronized(synchronizedTestApp1){//使用synchronizedTestApp1作为锁
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName+" : 进入到了锁的内容中");
                    Random random = new Random();
                    int randomInt = random.nextInt(10);
                    try {
                        Thread.sleep(randomInt*1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadName+" :执行完了任务，退出锁的内容");
                }
            }
        }).start();

        //第二个线程
        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("myThread2");
                System.out.println("第二个线程开始了。。。");
                synchronized (synchronizedTestApp1){//使用synchronizedTestApp1作为锁，
                    // myThread2和myThread1使用同一个锁对象，那么只能有一个线程在执行锁里面的代码片段
                    //如果换成是使用synchronizedTestApp2作为锁，那么两个线程之间就没有关系了
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName+" : 进入到了锁的内容中");
                    Random random = new Random();
                    int randomInt = random.nextInt(10);
                    System.out.println("我是第二个线程，现在我要睡觉了");
                    try {
                        Thread.sleep(randomInt*1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadName+" :睡醒了，把锁还给你，退出锁的内容");
                }
            }
        }).start();
    }
}
