/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.streams.play;

import static java.util.stream.Collectors.*;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Adder {
  private Stream<Long> streamOfLong;
  private LongStream longStream;
  private long ceiling = 0;
  
  public Adder(long ceiling) {
    this.ceiling = ceiling;
  }
  
  public void init() {
    streamOfLong = Stream.iterate(1L, i -> i + 1).limit(ceiling);
    longStream = LongStream.rangeClosed(1, ceiling);
  }
  
  /**
   * TODO: calculate and return the sum of longStream [a LongStream].
   */
  public long sumLongStream() {
    long sum = 0;
    sum = longStream.sum();
    return sum;
  }
  
  /**
   * TODO: calculate in parallel and return the sum of longStream [a LongStream].
   */
  public long sumLongStreamParallel() {
    long sum = 0;
    sum = longStream.parallel().sum();
    return sum;
  }
  
  /**
   * TODO: calculate and return the sum of streamOfLong [a Stream<Long>].
   */
  public long sumStreamOfLong() {
    long sum = 0;
    sum = streamOfLong.collect(summingLong(l -> ((Long) l).longValue()));
    return sum;
  }
  
  /**
   * TODO: calculate in parallel and return the sum of streamOfLong [a Stream<Long>].
   */
  public long sumStreamOfLongParallel() {
    long sum = 0;
    sum = streamOfLong.parallel().collect(Collectors.summingLong(l -> ((Long) l).longValue()));
    return sum;
  }
}