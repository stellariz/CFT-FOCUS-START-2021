package ru.cftfocusstart.task4;

import java.util.Scanner;

public class Application {

    private static final int NUM_THREADS = 100;

    private static int readIterNum() {
        System.out.print("Enter the number of iterations: ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                int iterNum = Integer.parseInt(scanner.nextLine());
                if (iterNum <= 0) {
                    System.out.print("Values less or equal zero can't be entered. Please enter again: ");
                } else {
                    return iterNum;
                }
            } catch (NumberFormatException ignored) {
                System.out.print("Value isn't an integer. Please enter again: ");
            }
        }
        throw new IllegalArgumentException("Error in reading number of iterations");
    }

    public static void main(String[] args) {
        try {
            int iterNum = readIterNum();
            Task[] tasks = new Task[NUM_THREADS];
            Thread[] threads = new Thread[NUM_THREADS];
            for (int i = 0; i < NUM_THREADS; ++i) {
                tasks[i] = new Task(i, NUM_THREADS, iterNum);
                threads[i] = new Thread(tasks[i]);
                threads[i].start();
            }
            double totalValue = 0.0;
            for (int i = 0; i < NUM_THREADS; ++i) {
                threads[i].join();
                System.out.println(tasks[i].getResultValue());
                totalValue += tasks[i].getResultValue();
            }
            System.out.println("Sum of serial is: " + totalValue);
        } catch (Exception e) {
            //log.error();
        }
    }
}
