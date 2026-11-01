package ru.aston.moodle4.alternation;

import java.io.PrintStream;

public class AlternatingPrinter {
    private final Object lock = new Object();
    private final PrintStream output;
    private final long limit;
    private long printed;
    private int turn = 1;

    private AlternatingPrinter(PrintStream output, long limit) {
        this.output = output;
        this.limit = limit;
    }

    public static void print(PrintStream output, long limit) throws InterruptedException {
        AlternatingPrinter printer = new AlternatingPrinter(output, limit);
        Thread first = new Thread(() -> printer.write(1), "printer-1");
        Thread second = new Thread(() -> printer.write(2), "printer-2");

        first.start();
        second.start();
        first.join();
        second.join();
    }

    private void write(int digit) {
        try {
            while (true) {
                synchronized (lock) {
                    while (turn != digit && (limit == -1 || printed < limit)) {
                        lock.wait();
                    }
                    if (limit != -1 && printed >= limit) {
                        lock.notifyAll();
                        return;
                    }
                    
                    output.print(digit);
                    printed++;
                    turn = digit == 1 ? 2 : 1;
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        print(System.out, -1);
    }
}
