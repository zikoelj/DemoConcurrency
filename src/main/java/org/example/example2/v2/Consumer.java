package org.example.example2.v2;

import org.example.example2.v2.Buffer2;

import java.util.concurrent.locks.*;

public class Consumer implements Runnable {
    private final Buffer2 buffer2;
    private final Lock lock;
    private final Condition condition;

    public Consumer(Buffer2 buffer2, Lock lock, Condition condition) {
        this.buffer2 = buffer2;
        this.lock = lock;
        this.condition = condition;
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (!buffer2.hasData()) {
                System.out.println("Buffer2 vide, Consumer en attente...");
                condition.await();
            }
            int value = buffer2.getData();
            buffer2.setHasData(false);
            System.out.println("Consommé : " + value);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                consume();
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}