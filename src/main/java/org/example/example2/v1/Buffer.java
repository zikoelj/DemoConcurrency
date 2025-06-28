package org.example.example2.v1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private int data;
    private boolean hasData = false; // Indique si le buffer contient un élément
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    // Méthode du producteur
    public void produce(int value) throws InterruptedException {
        lock.lock(); // Étape 1 : on entre en section critique
        try {
            while (hasData) {
                // Pause : le producteur attend que le consommateur consomme
                System.out.println("Buffer2 plein, producteur en attente...");
                condition.await(); // le producteur se met en pause
            }
            // Production de la donnée
            data = value;
            hasData = true;
            System.out.println("Produit : " + value);

            // Notification du consommateur
            condition.signal(); // on réveille un consommateur
        } finally {
            lock.unlock(); // Étape finale : on libère le verrou
        }
    }

    // Méthode du consommateur
    public int consume() throws InterruptedException {
        lock.lock(); // Étape 1 : on entre en section critique
        try {
            while (!hasData) {
                // Pause : le consommateur attend qu'un élément soit produit
                System.out.println("Buffer2 vide, consommateur en attente...");
                condition.await(); // le consommateur se met en pause
            }
            // Consommation de la donnée
            int value = data;
            hasData = false;
            System.out.println("Consommé : " + value);

            // Notification du producteur
            condition.signal(); // on réveille un producteur
            return value;
        } finally {
            lock.unlock(); // Étape finale : on libère le verrou
        }
    }
}
