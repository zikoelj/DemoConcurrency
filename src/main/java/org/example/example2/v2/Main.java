package org.example.example2.v2;

import java.util.concurrent.locks.*;

public class Main {
    public static void main(String[] args) {
        Buffer2 buffer2 = new Buffer2();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Producer producer = new Producer(buffer2, lock, condition);
        Consumer consumer = new Consumer(buffer2, lock, condition);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
