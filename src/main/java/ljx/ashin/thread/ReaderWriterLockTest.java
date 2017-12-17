package ljx.ashin.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的应用
 * Created by AshinLiang on 2017/12/17.
 */
public class ReaderWriterLockTest {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//     ReaderWriterLockTest readerWriterLockTest = new ReaderWriterLockTest();
    public static void main(String[] args) {
        final ReaderWriterLockTest readerWriterLockTest = new ReaderWriterLockTest();
        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("myThread1");

                readerWriterLockTest.getDate();
                readerWriterLockTest.writeDate();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("myThread2");
//                ReaderWriterLockTest readerWriterLockTest = new ReaderWriterLockTest();
                readerWriterLockTest.getDate();
                readerWriterLockTest.writeDate();
            }
        }).start();
    }

    /**
     * 读锁，获取数据
     */
    private void getDate(){
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+" 读锁，获取数据");
            for (int i = 0; i < 100; i++) {
                Thread.sleep(2*1000);
                System.out.println(Thread.currentThread().getName()+"正在读取数据 i="+i);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            readWriteLock.readLock().unlock();
            System.out.println("成功释放了锁");
        }
    }

    /**
     * 写锁，写入数据
     */
    private void writeDate(){
        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+" 写锁，写入数据");
            for (int i = 0; i < 100; i++) {
                Thread.sleep(2*1000);
                System.out.println(Thread.currentThread().getName()+"正在写数据 i="+i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
            System.out.println("成功释放了锁");
        }
    }
}
