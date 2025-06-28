package org.example.example1;


import org.example.example1.Demo;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();

        // Create and start a waiting thread
        Thread waitingThread = new Thread(() -> {
            try {
                System.out.println("Waiting thread is going to wait...");
                demo.awaitFunction();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        waitingThread.start();

        // Give the waiting thread time to start
        Thread.sleep(1000);

        // Signal the waiting thread
        System.out.println("Main thread is going to signal...");
        demo.signal();

        // Wait for the waiting thread to finish
        waitingThread.join();

        // Demonstrate signalAll with multiple threads
        System.out.println("\nTesting signalAll with multiple threads:");
        Thread[] threads = new Thread[3];
        demo.setReady(false); // reset the flag

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting...");
                    demo.awaitFunction();
                    System.out.println(Thread.currentThread().getName() + " has been resumed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        Thread.sleep(1000);
        System.out.println("Main thread is going to signal all...");
        demo.signalAll();

        for (Thread t : threads) {
            t.join();
        }
    }
}
