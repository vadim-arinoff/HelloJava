package ru.aston.moodle4.livelock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LivelockExampleTest {
    @Test
    void workersChangeDirectionButNeverFinish() throws InterruptedException {
        LivelockExample example = new LivelockExample();
        example.start();
        try {
            long deadline = System.currentTimeMillis() + 2_000;
            while (example.getAttempts() < 10 && System.currentTimeMillis() < deadline) {
                Thread.sleep(10);
            }

            assertTrue(example.getAttempts() >= 10);
            assertFalse(example.isFinished());
        } finally {
            example.stop();
        }
    }
}
