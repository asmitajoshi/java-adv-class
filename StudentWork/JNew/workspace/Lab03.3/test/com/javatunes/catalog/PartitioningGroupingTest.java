/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.catalog;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class PartitioningGroupingTest {
  
  private Collection<MusicItem> allMusicItems;
  
  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * TASK: partition MusicItems into self-titled albums (artist = title), and all others.
   * 
   * RESULT:
   *  true    List<MusicItem>[6,7]
   *  false   List<MusicItem>[1,2,3,4,5,8,9,10,11,12,13,14,15,16,17,18]
   */
  @Test
  public void testPartitionBySelfTitled() {
    Map<Boolean,List<MusicItem>> itemMap = allMusicItems.stream()
      .collect(partitioningBy(item -> item.getArtist().equals(item.getTitle())));
    
    // all partition maps have exactly two entries, one of the entries might contain an empty List
    assertEquals(2, itemMap.size());
    
    List<MusicItem> trueList = itemMap.get(true);
    List<MusicItem> falseList = itemMap.get(false);
    
    assertEquals(2, trueList.size());
    assertEquals(16, falseList.size());
    
    // A neat JUnit technique
    // Recall that Collection has a forEach(Consumer) method
    // However, this isn't testing your code, it just verifies that partitioningBy works correctly (it better!)
    trueList.forEach(item -> assertTrue(item.getArtist().equals(item.getTitle())));
    falseList.forEach(item -> assertFalse(item.getArtist().equals(item.getTitle())));
    
    // convenient way to dump a Map to stdout
    dumpMap(itemMap);
  }
  
  /**
   * TASK: partition MusicItems by price <= 14.00.
   * 
   * RESULT:
   */
  @Test
  public void testPartitionByPrice() {
    Map<Boolean, List<MusicItem>> itemMap = allMusicItems.stream()
    		.collect(partitioningBy(item -> item.getPrice() <= 14.00));
    
    assertEquals(itemMap.size(), 2);
    List<MusicItem> trueL = itemMap.get(true);
    List<MusicItem> falseL = itemMap.get(false);
    
    assertEquals( trueL.size(), 7 );
    assertEquals( falseL.size(), 11 );
    
    dumpMap(itemMap);
  }
  
  /**
   * TASK: partition MusicItems into ROCK+CLASSIC_ROCK, and all others. Verify.
   * 
   * RESULT: 
   */
  @Test
  public void testPartitionByMusicCategoryRock() {
    Map<Boolean, List<MusicItem>> itemMap = allMusicItems.stream()
    		.collect(partitioningBy(item -> item.getMusicCategory().toString().contains("ROCK")));
    
    dumpMap(itemMap);
    assertEquals( itemMap.get(true).size(), 7 );
    assertEquals( itemMap.get(false).size(), 11 );
    
  }
  
  /**
   * TASK: group MusicItems by music category. Verify each entry in the map.
   * 
   * RESULT: 
   */
  @Test
  public void testGroupByMusicCategory() {
    Map<MusicCategory, List<MusicItem>> itemMap = allMusicItems.stream()
    		.collect(groupingBy(item -> item.getMusicCategory()));
    
    dumpMap(itemMap);
    
    assertEquals( itemMap.get(MusicCategory.ALTERNATIVE).get(0).getArtist(), "Tori Amos" );
    assertEquals( itemMap.get(MusicCategory.BLUES).get(1).getArtist(), "Jonny Lang" );
  }
  
  /**
   * TASK: group MusicItems by price and verify.
   * 
   * RESULT: 
   */
  @Test
  public void testGroupByPrice() {
    Map<Double, List<MusicItem>> itemMap = allMusicItems.stream()
    		.collect(groupingBy(item -> item.getPrice()));
    
    dumpMap(itemMap);
    
    assertEquals( itemMap.get(11.97).size(), 4 );
    assertEquals( itemMap.get(14.99).size(), 3 );
  }
  
  /*
   * Helper method to dump map to standard out. Map is assumed to have entries with Collections.
   */
  private static void dumpMap(Map<?,? extends Collection<?>> map) {
    map.entrySet().forEach(System.out::println);
    System.out.println();
    
    // Map.forEach() takes a BiConsumer<K,V>. This block lambda is passed key-value pairs each iteration.
    map.forEach((key, list) -> {
      System.out.println(key);
      list.forEach(System.out::println);
    });
  }
}