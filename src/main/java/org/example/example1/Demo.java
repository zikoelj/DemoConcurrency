package org.example.example1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private boolean ready = false;


    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void awaitFunction() throws InterruptedException {
        lock.lock();
        try {
            while (!ready) {
                condition.await(); // wait until ready becomes true
            }
            System.out.println("Thread resumed!");
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        lock.lock();
        try {
            ready = true;
            condition.signal(); // wake up one waiting thread
        } finally {
            lock.unlock();
        }
    }

    public void signalAll() {
        lock.lock();
        try {
            ready = true;
            condition.signalAll(); // wake up all waiting threads
        } finally {
            lock.unlock();
        }
    }
}
