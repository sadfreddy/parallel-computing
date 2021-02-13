package com.naumau;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class ParallelTest {

  @Test
  public void sumParallel() throws InterruptedException {
    var result = Parallel.sumParallel(Arrays.asList(1, 2, 3, 4, 5));
    assertEquals(result, 15);
  }

  @Test
  public void sumParallelStream() throws InterruptedException, ExecutionException {
    var result = Parallel.sumParallelStream(Arrays.asList(1, 2, 3, 4, 5));
    assertEquals(result, 15);
  }
}