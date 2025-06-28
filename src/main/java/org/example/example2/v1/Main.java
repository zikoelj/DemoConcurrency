package org.example.example2.v1;


public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        // Thread producteur
        Thread Producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i); // produit une valeur
                    Thread.sleep(500); // délai pour simuler une production lente
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Thread consommateur
        Thread Consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume(); // consomme une valeur
                    Thread.sleep(800); // délai pour simuler une consommation lente
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Démarrage des deux threads
        Producer.start();
        Consumer.start();
    }
}