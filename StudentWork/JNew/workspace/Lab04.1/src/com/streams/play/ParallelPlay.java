/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.streams.play;

import java.util.stream.IntStream;

public class ParallelPlay {

  public static void main(String[] args) {
    int[] numbers = createArray();  // creates an array of ints from 1-50
    
    // create an IntStream from the int[] array
    IntStream sequential = IntStream.of(numbers);
    
    // print the elements in the stream via forEach(Consumer)
    System.out.println("int stream forEach - sequential");
    sequential.forEach(i -> System.out.print(i + " "));    
    System.out.println("\n");
    
    System.out.println("int stream forEach - parallel one");
    // TODO: write a parallel version of the above
    // Note: you'll need to create another IntStream from the array - sequential is closed after the forEach()
    IntStream.of(numbers).parallel().forEach(i -> System.out.println(i + " "));;
    
    System.out.println("int stream forEach - parallel two");
    // TODO: write a parallel version of the above again
    // Note: again, you'll need to create another IntStream from the array
    IntStream.of(numbers).parallel().forEach(i -> System.out.println(i + " "));
    
  }

  private static int[] createArray() {
    return IntStream.rangeClosed(1, 50).toArray();
  }
}