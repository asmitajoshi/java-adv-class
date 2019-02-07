/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.streams.play;

import java.util.stream.Stream;

public class StreamTest {

  public static void main(String[] args) {
    Stream<String> testStream = Stream.of("coffee", "tea", "seltzer", "cola");
    
    // TODO: determine if testStream is parallel and output the result
    System.out.println("is stream parallel " + testStream.isParallel());
    
    // TODO: call parallel() on testStream and show whether it's parallel or not
    testStream = testStream.parallel();
    System.out.println("is stream parallel " + testStream.isParallel());

    // TODO: call sequential() on the parallel stream above and show whether it's parallel or not
    testStream = testStream.sequential();
    System.out.println("is stream parallel " + testStream.isParallel());
  }
}