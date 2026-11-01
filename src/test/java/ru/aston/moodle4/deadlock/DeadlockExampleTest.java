package ru.aston.moodle4.deadlock;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlockExampleTest {
    @Test
    void twoThreadsReallyDeadlock() throws InterruptedException {
        Thread[] threads = DeadlockExample.start();
        long deadline = System.currentTimeMillis() + 2_000;
        long[] deadlocked;

        do {
            deadlocked = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
            if (deadlocked != null) {
                break;
            }
            Thread.sleep(10);
        } while (System.currentTimeMillis() < deadline);

        assertTrue(deadlocked != null
                && Arrays.stream(deadlocked).anyMatch(id -> id == threads[0].getId())
                && Arrays.stream(deadlocked).anyMatch(id -> id == threads[1].getId()));
    }
}
