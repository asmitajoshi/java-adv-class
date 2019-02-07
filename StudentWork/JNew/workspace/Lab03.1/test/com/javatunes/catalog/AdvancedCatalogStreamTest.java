/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.catalog;

// TODO: Uncomment the static imports that we supply as you need them
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class AdvancedCatalogStreamTest {
  
  private Collection<MusicItem> allMusicItems;

  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * TASK: determine average price of our low-cost, extensive catalog of music.
   * 
   * RESULT: 15.309 (via calculator)
   */
  @Test
  public void testAveragePrice() {
    OptionalDouble avgPriceOptional = allMusicItems.stream()
      .mapToDouble(MusicItem::getPrice)  // item -> item.getPrice()
      .average();
    double avgPrice = avgPriceOptional.getAsDouble();
    
    assertEquals(15.309, avgPrice, .001);
  }
  
  /**
   * TASK: determine the titles of all "pop" items, sorted by natural order.
   * 
   * RESULT: 
   */
  @Test
  public void testTitlesPopSortNaturalOrder() {
	  List<String> items = allMusicItems.stream()
			  .filter(item -> item.getMusicCategory().equals(MusicCategory.POP))
			  .map(MusicItem::getTitle)
			  .collect(Collectors.toList());
	  
	  assertEquals( 4, items.size() );
	  assertEquals( "Diva", items.get(0) );
	  assertEquals( "Seal", items.get(2) );
  }
  
  /**
   * TASK: are all items priced at least $10?
   * 
   * RESULT: 
   */
  @Test
  public void testAllPriceGreaterThanEqual() {
    boolean allPricedGreaterThan = allMusicItems.stream()
    		.allMatch(item -> item.getPrice() > 10);
    
    System.out.println("all priced greater than 10? " + allPricedGreaterThan);
    
    assertFalse( allPricedGreaterThan );
  }
  
  /**
   * TASK: do we sell any "jazz" items?
   * 
   * RESULT: 
   */
  @Test
  public void testAnyMusicCategory() {
    boolean anyJazzItems = allMusicItems.stream()
    		.anyMatch(item -> item.getMusicCategory().equals(MusicCategory.JAZZ));
    
    assertFalse( anyJazzItems );
  }
  
  /**
   * TASK: how many "blues" items do we sell?
   * 
   * RESULT: 
   */
  @Test
  public void testCountMusicCategory() {
    long numBlues = allMusicItems.stream()
    		.filter(item -> item.getMusicCategory().equals(MusicCategory.BLUES))
    		.count();
    
    assertEquals( 2, numBlues );
  }
  
  /**
   * TASK: print to stdout any 3 "rock" items.
   * 
   * RESULT: 
   */
  @Test
  public void testPrintAny3MusicCategory() {
    allMusicItems.stream()
    .filter(item -> item.getMusicCategory().equals(MusicCategory.ROCK))
    .limit(3)
    .forEach(item -> System.out.println(item));
    
  }
  
  /**
   * TASK: determine the average price of the 3 most recent items we sell.
   * 
   * RESULT: 
   */
  @Test
  public void testAvgPrice3Newest() {
    OptionalDouble avg = allMusicItems.stream()
    		.sorted(Comparator.comparing(item -> ((MusicItem) item).getReleaseDate()).reversed())
    		.limit(3)
    		.mapToDouble(MusicItem::getPrice)
    		.average();
    
    assertEquals( 17.97, avg.getAsDouble(), 0.01 );
  }
  
  /**
   * TASK: determine the 2 highest-priced MusicItems, sorted by title.
   * HINT: you will need to sort twice in the pipeline.
   * 
   * RESULT: 
   */
  @Test
  public void testPrice2HighestSortTitle() {
    List<MusicItem> items = allMusicItems.stream()
    		.sorted(Comparator.comparing(item -> ((MusicItem) item).getPrice()).reversed())
    		.limit(2)
    		.sorted(Comparator.comparing(item -> ((MusicItem) item).getTitle()))
    		.collect(Collectors.toList());
    
    assertEquals( 2, items.size() );
    assertEquals( "Big Ones", items.get(0).getTitle() );
    assertEquals( "Human Clay", items.get(1).getTitle() );
  }
  
  /**
   * TASK: what is the price of our cheapest "pop" item?
   * 
   * RESULT: 
   */
  @Test
  public void testMinPriceMusicCategory() {
    OptionalDouble cheapest = allMusicItems.stream()
    		.mapToDouble(item -> item.getPrice())
    		.min();
    
    assertEquals( cheapest.getAsDouble(), 9.97, 0.01 );
  }
}