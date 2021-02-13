package com.naumau;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Parallel {
  private static class HelloThread extends Thread {
    private int sum;
    private List<Integer> numbers;

    HelloThread(List<Integer> numbers) {
      this.numbers = numbers;
    }

    @Override
    public void run() {
      for (int number: numbers) {
        sum += number;
      }
    }

    public int getSum() {
      return sum;
    }
  }

  static int sumParallel(List<Integer> numbers) throws InterruptedException {
    var firstThread = new HelloThread(numbers.subList(numbers.size() / 2, numbers.size()));
    var secondThread = new HelloThread(numbers.subList(0, numbers.size() / 2));

    firstThread.start();
    secondThread.start();

    firstThread.join();
    secondThread.join();

    return firstThread.getSum() + secondThread.getSum();
  }

  static int sumParallelStream(List<Integer> numbers) throws InterruptedException, ExecutionException {
    ForkJoinPool customThreadPool = new ForkJoinPool(4);
    return customThreadPool.submit(() -> numbers.parallelStream().reduce(0, Integer::sum)).get();
  }
}
