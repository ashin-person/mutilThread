package ljx.ashin.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁的使用
 * Created by AshinLiang on 2017/12/17.
 */
public class LockTest {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("myThread1");
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                try {
                    Thread.sleep(3*1000);
                    System.out.println(Thread.currentThread().getName()+"业务处理结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    System.out.println("释放了锁");
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("myThread2");
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得了锁");
                try {
                    Thread.sleep(3*1000);
                    System.out.println(Thread.currentThread().getName()+"业务处理结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    System.out.println("释放了锁");
                }
            }
        }).start();
    }
}
