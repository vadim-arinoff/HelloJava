package ru.aston.moodle4.livelock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class LivelockExample {
    private final CyclicBarrier barrier = new CyclicBarrier(2);
    private final AtomicInteger attempts = new AtomicInteger();
    private final Worker left = new Worker();
    private final Worker right = new Worker();
    private final Thread first = new Thread(() -> work(left, right), "livelock-1");
    private final Thread second = new Thread(() -> work(right, left), "livelock-2");
    private volatile boolean running = true;
    private volatile boolean finished;

    public void start() {
        first.start();
        second.start();
    }

    private void work(Worker me, Worker other) {
        try {
            while (running) {
                barrier.await();
                boolean sameDirection = me.goRight == other.goRight;
                barrier.await();
                if (!sameDirection) {
                    finished = true;
                    return;
                }

                me.goRight = !me.goRight;
                attempts.incrementAndGet();
                barrier.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
        }
    }

    public int getAttempts() {
        return attempts.get();
    }

    public boolean isFinished() {
        return finished;
    }

    public void stop() throws InterruptedException {
        running = false;
        first.interrupt();
        second.interrupt();
        first.join();
        second.join();
    }

    private static class Worker {
        private volatile boolean goRight = true;
    }

    public static void main(String[] args) throws InterruptedException {
        LivelockExample example = new LivelockExample();
        example.start();
        
        try {
            while (true) {
                Thread.sleep(1_000);
                System.out.println("Direction changes: " + example.getAttempts()
                        + ", finished: " + example.isFinished());
            }
        } finally {
            example.stop();
        }
    }
}
