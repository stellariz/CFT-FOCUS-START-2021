package ru.cftfocusstart.task4;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Application {
    private static final int THREADS_NUM = 3;

    private static int readIterNum() {
        System.out.print("Enter the number of iterations: ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                int iterNum = Integer.parseInt(scanner.nextLine());
                if (iterNum <= 0) {
                    System.out.print("Values less or equal to zero can't be entered. Please enter again: ");
                    continue;
                }
                return iterNum;
            } catch (NumberFormatException e) {
                log.warn("Incorrect number was entered", e);
                System.out.print("Value isn't an integer. Please enter again: ");
            }
        }
        throw new IllegalArgumentException("Error in reading number of iterations");
    }

    public static void main(String[] args) {
        try {
            int iterNum = readIterNum();
            Task[] tasks = new Task[THREADS_NUM];
            Thread[] threads = new Thread[THREADS_NUM];
            for (int i = 0; i < THREADS_NUM; ++i) {
                tasks[i] = new Task(i, THREADS_NUM, iterNum);
                threads[i] = new Thread(tasks[i]);
                threads[i].start();
            }
            double totalValue = 0.0;
            for (int i = 0; i < THREADS_NUM; ++i) {
                threads[i].join();
                totalValue += tasks[i].getResultValue();
            }
            System.out.println("Sum of serial is: " + totalValue);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
