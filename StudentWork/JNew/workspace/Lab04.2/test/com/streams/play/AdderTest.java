/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.streams.play;

import static org.junit.Assert.*;
import java.util.function.LongSupplier;
import org.junit.Before;
import org.junit.Test;

public class AdderTest {
  private static final long MAX_VALUE = 10_000_000;  // Java 7: same as 10000000 (readability)
  private Adder adder;
  
  @Before
  public void setUp() {
    adder = new Adder(MAX_VALUE);
  }
  
  @Test
  public void testSumLongStream() {
    // EXAMPLE DONE FOR YOU: pass in Adder's sumLongStream() method (via method reference)
    // we covered this form of method reference in the course, but haven't used it in any code yet
    // it's an excellent example of passing behavior with Java 8's functional programming model
    TestResult sequentialResult = measurePerformance(adder::sumLongStream);
    System.out.println("LongStream - sequential: " + sequentialResult);
    System.out.println();
    
    // TODO: pass in Adder's sumLongStreamParallel() method
    TestResult parallelResult = measurePerformance(adder::sumLongStreamParallel);
    System.out.println("LongStream - parallel: " + parallelResult);
    System.out.println();
    
    // TODO: uncomment assertion below when both results have been computed
    assertEquals(sequentialResult.getSum(), parallelResult.getSum());
  }

  @Test
  public void testSumStreamOfLong() {
    // TODO: pass in Adder's sumStreamOfLong() method
    TestResult sequentialResult = measurePerformance(adder::sumStreamOfLong);
    System.out.println("Stream<Long> - sequential: " + sequentialResult);
    System.out.println();

    // TODO: pass in Adder's sumStreamOfLongParallel() method
    TestResult parallelResult = measurePerformance(adder::sumStreamOfLongParallel);
    System.out.println("Stream<Long> - parallel: " + parallelResult);
    System.out.println();
    
    // TODO: uncomment assertion below when both results have been computed
    assertEquals(sequentialResult.getSum(), parallelResult.getSum());
  }
  
  
  /**
   * NOTE that the instance methods of Adder are LongSuppliers, i.e., they take nothing and return a long.
   * Recall that the function descriptor for Supplier<T> is () -> T.
   * LongSupplier is a primitive specialization of Supplier, with function descriptor () -> long.
   */
  private TestResult measurePerformance(LongSupplier supplier) {
    int iterations = 10;
    
    long fastestTime = Long.MAX_VALUE;
    long fastestSum = 0;
    long sum = 0;
    
    for (int i = 1; i <= iterations; i++) {
      adder.init();  // create streams, must be done each iteration (once a stream is consumed, it's "done")
      
      long start = System.nanoTime();
      // this does the actual work, in our case by calling the Adder method passed in
      // this is an *excellent* example of method references at work
      sum = supplier.getAsLong();
      long duration = System.nanoTime() - start;
      
      System.out.println("Execution " + i + ": " + duration/1000000.0 + "ms");
      
      if (duration < fastestTime) {
        fastestTime = duration;
        fastestSum = sum;
      }
    }
    return new TestResult(fastestTime, fastestSum);
  }
  
  
  /**
   * Inner class to encapsulate test run results: duration and sum.
   */
  private class TestResult {
    private long duration;
    private long sum;
    
    TestResult(long duration, long sum) {
      this.duration = duration;
      this.sum = sum;
    }
    
    long getDuration() {
      return this.duration;
    }
    
    long getSum() {
      return this.sum;
    }
    
    @Override
    public String toString() {
      return getClass().getSimpleName() + " [duration: " + duration/1000000.0 + "ms | sum: " + sum + "]";
    }
  }
}