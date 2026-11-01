package ru.aston.moodle4.deadlock;

import java.lang.management.ManagementFactory;
import java.util.concurrent.CountDownLatch;

public class DeadlockExample {
    public static Thread[] start() {
        Object firstLock = new Object();
        Object secondLock = new Object();
        CountDownLatch bothHaveFirstLock = new CountDownLatch(2);

        Thread first = new Thread(
                () -> lockInOrder(firstLock, secondLock, bothHaveFirstLock), "deadlock-1");
        Thread second = new Thread(
                () -> lockInOrder(secondLock, firstLock, bothHaveFirstLock), "deadlock-2");

        first.setDaemon(true);
        second.setDaemon(true);
        first.start();
        second.start();
        return new Thread[]{first, second};
    }

    private static void lockInOrder(Object first, Object second, CountDownLatch latch) {
        synchronized (first) {
            latch.countDown();
            
            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (second) {
                System.out.println("This line is unreachable");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        start();

        for (int i = 0; i < 100; i++) {
            if (ManagementFactory.getThreadMXBean().findDeadlockedThreads() != null) {
                System.out.println("Deadlock detected: both threads wait for each other's lock.");
                return;
            }
            Thread.sleep(10);
        }
        
        System.out.println("Deadlock was not detected in time.");
    }
}
