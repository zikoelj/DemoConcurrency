package org.example.example2.v2;

import org.example.example2.v2.Buffer2;

import java.util.concurrent.locks.*;

public class Producer implements Runnable {
    private final Buffer2 buffer2;
    private final Lock lock;
    private final Condition condition;

    public Producer(Buffer2 buffer2, Lock lock, Condition condition) {
        this.buffer2 = buffer2;
        this.lock = lock;
        this.condition = condition;
    }

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (buffer2.hasData()) {
                System.out.println("Buffer2 plein, producteur en attente...");
                condition.await();
            }
            buffer2.setData(value);
            buffer2.setHasData(true);
            System.out.println("Produit : " + value);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                produce(i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}