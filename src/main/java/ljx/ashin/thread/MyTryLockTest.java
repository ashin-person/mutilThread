package ljx.ashin.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock尝试获得锁
 * Created by AshinLiang on 2017/12/17.
 */
public class MyTryLockTest {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        new Thread(new Runnable() {
            public void run() {
                if (lock.tryLock()){
                    Thread.currentThread().setName("myThread1");
                    System.out.println(Thread.currentThread().getName()+"获得锁成功");
                    try {
                        Thread.sleep(5*1000);
                        System.out.println("进行业务处理");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println(Thread.currentThread().getName()+"释放锁了");
                        lock.unlock();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                if (lock.tryLock()){
                    Thread.currentThread().setName("myThread2");
                    System.out.println(Thread.currentThread().getName()+"获得锁成功");
                    try {
                        Thread.sleep(5*1000);
                        System.out.println(Thread.currentThread().getName()+"进行业务处理");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println(Thread.currentThread().getName()+"释放锁了");
                        lock.unlock();
                    }
                }
            }
        }).start();
    }
}
