package Linkedin;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现两个函数: H() and O(), 这两个函数会被多线程调用。当一个线程调用H或O时，
 * 如果当前已经有至少两个线程call H和一个线程call O。那么让两个call H和一个
 * call O的线程返回（产生一个水分子），其他的都block。
 *
 * Condition的执行方式，是当在线程1中调用await方法后，线程1将释放锁，并且将自己沉睡，
 * 等待唤醒，线程2获取到锁后，开始做事，完毕后，调用Condition的signal方法，唤醒线程1，
 * 线程1恢复执行。以上说明Condition是一个多线程间协调通信的工具类，使得某个，或者某些
 * 线程一起等待某个条件（Condition）,只有当该条件具备( signal 或者 signalAll方法被
 * 带调用)时 ，这些等待线程才会被唤醒，从而重新争夺锁。
 *
 * Producer Consumer multi-threads problem. Here there's no explicit producer,
 * H() and O() are consumers, they should be blocked based on certain
 * conditions.
 *
 */
public class H2O {
    ReentrantLock lock = new ReentrantLock();
    Condition enoughH = lock.newCondition();
    Condition enoughO = lock.newCondition();
    AtomicInteger countH  = new AtomicInteger(0);
    AtomicInteger countO =  new AtomicInteger(0);

    void H() throws InterruptedException {
        lock.lock();
        try {
            countH.incrementAndGet();

            while (!check(true)) {
                enoughH.await();
            }
        } finally {
            lock.unlock();
        }
    }

    void O() throws InterruptedException {
        lock.lock();
        try {
            countO.incrementAndGet();

            while (!check(false)) {
                enoughO.await();
            }
        } finally {
            lock.unlock();
        }
    }

    boolean check(boolean isH) {
        if (countH.intValue() >= 2 && countO.intValue() >= 1) {
            countH.decrementAndGet();
            countH.decrementAndGet();
            countO.decrementAndGet();

            System.out.println("H2O");

            enoughH.signal();
            if (isH) { // count is added already and we will return true here, so just signal O
                enoughO.signal();
            } else {
                enoughH.signal();
            }

            return true;
        }

        return false;
    }


    public static void main(String[] args) {

    }
}

