package ru.cftfocusstart.task4;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class Application {
    private static final int THREADS_NUM = 10;

    public static void main(String[] args) {
        try {
            int iterNum = readIterNum();
            AtomicReference<Double> totalValue = new AtomicReference<>(0.0);
            CompletableFuture<?>[] partialSums = new CompletableFuture[THREADS_NUM];
            for (int i = 0; i < THREADS_NUM; ++i) {
                Task task = new Task(i, THREADS_NUM, iterNum);
                partialSums[i] = CompletableFuture.runAsync(task)
                        .thenApplyAsync((r) -> totalValue.getAndAccumulate(task.getResultValue(), Double::sum));
            }
            CompletableFuture.allOf(partialSums).join();
            System.out.println("Sum of serial is: " + totalValue.get());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

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
}
