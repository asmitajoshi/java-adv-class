/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.catalog;

//TODO: Uncomment the static imports that we supply as you need them
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

public class ReducingSummarizingTest {
  
  private Collection<MusicItem> allMusicItems;
  
  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * TASK: determine the number of items in each music category.
   * 
   * RESULT:
   *  POP           4
   *  BLUES         2
   *  ALTERNATIVE   2
   *  CLASSICAL     1
   *  ROCK          6
   *  RAP           1
   *  COUNTRY       1
   *  CLASSIC_ROCK  1
   *  JAZZ          null   note: map has no entry for JAZZ at all
   */
  @Test
  public void testCountMusicCategory() {
    Map<MusicCategory,Long> countMap = allMusicItems.stream()
      .collect(groupingBy(MusicItem::getMusicCategory, counting()));
    
    assertEquals(Long.valueOf(4), countMap.get(MusicCategory.POP));
    // alternative technique
    assertTrue(4 == countMap.get(MusicCategory.POP));
    assertTrue(2 == countMap.get(MusicCategory.BLUES));
    assertTrue(2 == countMap.get(MusicCategory.ALTERNATIVE));
    assertTrue(1 == countMap.get(MusicCategory.CLASSICAL));
    assertTrue(6 == countMap.get(MusicCategory.ROCK));
    assertTrue(1 == countMap.get(MusicCategory.RAP));
    assertTrue(1 == countMap.get(MusicCategory.COUNTRY));
    assertTrue(1 == countMap.get(MusicCategory.CLASSIC_ROCK));
    assertNull(countMap.get(MusicCategory.JAZZ));
  }
  
  /**
   * TASK: determine the average price of the items in each music category.
   * 
   * EXPECTED RESULT: via calculator
   *  POP           16.23
   *  BLUES         16.48
   *  ALTERNATIVE   16.48
   *  CLASSICAL      9.97
   *  ROCK          15.64
   *  RAP           16.97
   *  COUNTRY       11.97
   *  CLASSIC_ROCK  11.97
   */
  @Test
  public void testAveragePriceMusicCategory() {
    Map<MusicCategory, Double> countMap = allMusicItems.stream()
    		.collect(groupingBy(MusicItem::getMusicCategory, averagingDouble(MusicItem::getPrice)));
    
    assertEquals(countMap.get(MusicCategory.POP), 16.23, 0.01);
    assertEquals(countMap.get(MusicCategory.BLUES), 16.48, 0.01);
    assertEquals(countMap.get(MusicCategory.ALTERNATIVE), 16.48, 0.01);
    assertEquals(countMap.get(MusicCategory.CLASSICAL), 9.97, 0.01);
    assertEquals(countMap.get(MusicCategory.ROCK), 15.64, 0.01);
    assertEquals(countMap.get(MusicCategory.RAP), 16.97, 0.01);
    assertEquals(countMap.get(MusicCategory.COUNTRY), 11.97, 0.01);
    assertEquals(countMap.get(MusicCategory.CLASSIC_ROCK), 11.97, 0.01);
  }

  /**
   * TASK: divide all items into the following two groups:
   *  Rock   items in the ROCK and CLASSIC_ROCK music categories.
   *  Other  all others.
   *  Hint: Decide should your test be to see if the item is in one of the two categories above, then use it to partition
   * 
   * For each group, determine the number of items, the highest price, the lowest price, and the average price.
   * You can use one of the summarizing collectors to get this info
   * 
   * EXPECTED RESULT:  count  highest  lowest  average
   *  Rock      7     18.97    11.97   15.116
   *  Other    11     17.97     9.97   15.432
   */
  @Test
  public void testPricingDataRockOther() {
    Map<Boolean, DoubleSummaryStatistics> countMap = allMusicItems.stream()
    		.collect(partitioningBy(item -> item.getMusicCategory().toString().contains("ROCK"), 
    				summarizingDouble(MusicItem::getPrice)));
    
    assertEquals( countMap.get(true).getAverage(), 15.116, 0.01 );
    assertEquals( countMap.get(false).getAverage(), 15.432, 0.01 );
    assertEquals( countMap.get(true).getCount(), 7, 0.01 );
    assertEquals( countMap.get(false).getCount(), 11, 0.01 );
    assertEquals( countMap.get(true).getMax(), 18.97, 0.01 );
    assertEquals( countMap.get(false).getMax(), 17.97, 0.01 );
    assertEquals( countMap.get(true).getMin(), 11.97, 0.01 );
    assertEquals( countMap.get(false).getMin(), 9.97, 0.01 );
  }

  /**
   * TASK: determine the most recently released item in each music category.
   * Hint: You can get the item with the "maximum" release date to get the most recently released
   * 
   * EXPECTED RESULT:
   *  POP          item  1 (1992-01-04)
   *  BLUES        item  4 (1997-08-26)
   *  ALTERNATIVE  item  9 (1997-12-04)
   *  CLASSICAL    item  7 (1993-12-05)
   *  ROCK         item 12 (1999-10-21)
   *  RAP          item 11 (2000-12-06)
   *  COUNTRY      item 13 (1987-02-20)
   *  CLASSIC_ROCK item 18 (1981-02-25)
   */
  @Test
  public void testMostRecentItemMusicCategory() {
    Map<String, Optional<MusicItem>> countMap = allMusicItems.stream()
    		.collect(groupingBy(item -> item.getMusicCategory().toString(), 
    				maxBy(comparing(MusicItem::getReleaseDate))));
    
    assertEquals( countMap.get("POP").get().getReleaseDate().toString(), "1992-01-04" );
    assertEquals( countMap.get("BLUES").get().getReleaseDate().toString(), "1997-08-26" );
    assertEquals( countMap.get("ALTERNATIVE").get().getReleaseDate().toString(), "1997-12-04" );
    assertEquals( countMap.get("CLASSICAL").get().getReleaseDate().toString(), "1993-12-05" );
    assertEquals( countMap.get("ROCK").get().getReleaseDate().toString(), "1999-10-21" );
    assertEquals( countMap.get("RAP").get().getReleaseDate().toString(), "2000-12-06" );
    assertEquals( countMap.get("COUNTRY").get().getReleaseDate().toString(), "1987-02-20" );
    assertEquals( countMap.get("CLASSIC_ROCK").get().getReleaseDate().toString(), "1981-02-25" );

  }
  
  /**
   * TASK: build an "id-entry" map, i.e., the item's ID is the key, and the item itself is the value.
   * 
   * EXPECTED RESULT:
   *  1L   MusicItem 1
   *  2L   MusicItem 2
   *  3L   MusicItem 3
   *  ...
   *  18L  MusicItem 18 
   */
  @Test
  public void testMusicItemIdEntryMap() {
    Map<Long, MusicItem> itemMap = allMusicItems.stream()
    		.collect(toMap(MusicItem::getId, Function.identity()));
    
    assertEquals( itemMap.get(1L).getArtist(), "Annie Lennox");
    assertEquals( itemMap.get(16L).getArtist(), "Yes");
  }
  
  /**
   * OPTIONAL: partition artists into two groups: those with a self-titled album, and all others.
   * Careful: we want artist lists here.
   * 
   * EXPECTED RESULT:
   *  true    List<String>["Seal", "Ian Moore"]
   *  false   List<String>["Annie Lennox", "Sting", ... "Journey"]
   */
  @Test
  public void testPartitionArtistsBySelfTitledAlbum() {
    Map<Boolean, List<String>> itemMap = allMusicItems.stream()
    		.collect(partitioningBy(item -> item.getTitle().equals(item.getArtist()), 
    				mapping(MusicItem::getArtist, toList())));
    
    assertEquals( itemMap.get(true).get(0), "Seal" );
    assertEquals( itemMap.get(true).get(1), "Ian Moore" );
    assertEquals( itemMap.get(false).get(0), "Annie Lennox" );
    assertEquals( itemMap.get(false).get(1), "Sting" );
  }
  
  /**
   * CHALLENGE: what is our most popular "release year," i.e., we have more items released in that year than any other.
   * Hint: you'll need to compare the map entries by value - see Javadoc for Map.Entry
   * 
   * EXPECTED RESULT: 1997 (4 items)
   */
  @Test
  public void testMostPopularReleaseYear() {
    Map<String, Long> mostPop = allMusicItems.stream()
    		.collect(groupingBy(item -> item.getReleaseDate().toString().substring(0, 4),
    				counting()));
    
    assertEquals( mostPop.get("1997"), 4L, 0.01 );
    
//    mostPop.entrySet().stream()
  //  	sorted(Map.Entry.comparingByValue());
  }
  
  /**
   * EXTRA CREDIT: divide all items into the following two groups:
   *  Rock   items in the ROCK and CLASSIC_ROCK music categories.
   *  Other  all others.
   *  
   *  Partition each group into "cheap" and "expensive" items, where "cheap" is <= 14.00.
   *  Yes! this is a partition of partitions.
   * 
   * EXPECTED RESULT: 
   *  Rock    Map<Boolean,List<MusicItem>   8,10,12,15,16,17,18
   *          true  List<MusicItem>[8,16,17,18]
   *          false List<MusicItem>[10,12,15]
   *  
   *  Other   Map<Boolean,List<MusicItem>>  1,2,3,4,5,6,7,9,11,13,14
   *          true  List<MusicItem>[1,7,13]
   *          false List<MusicItem>[2,3,4,5,6,9,11,14]

   */
  @Test
  public void testPriceBreakdownRockOther() {
    // TODO
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