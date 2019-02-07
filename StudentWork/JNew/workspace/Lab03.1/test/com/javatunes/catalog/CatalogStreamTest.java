/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.catalog;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class CatalogStreamTest {
  
  private Collection<MusicItem> allMusicItems;

  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
  /**
   * TASK: find all MusicItems whose artist starts with "D" and sort them by price.
   * 
   * RESULT: MusicItems 11 and 10, in that order.
   */
  @Test
  public void testArtistStartsWithSortPrice() {
    List<MusicItem> items = allMusicItems.stream()
      .filter(item -> item.getArtist().startsWith("D"))
      .sorted(Comparator.comparing(item -> item.getPrice()))
      .collect(Collectors.toList());
    
    assertEquals(2, items.size());
    assertEquals(Long.valueOf(11), items.get(0).getId());
    assertEquals(Long.valueOf(10), items.get(1).getId());
  }
  
  /**
   * TASK: find all MusicItems where title is same as artist and sort them by natural order.
   * MusicItem implements Comparable: natural order is id increasing.
   * 
   * RESULT:
   */
  @Test
  public void testTitleEqualsArtistSortNaturalOrder() {
    List<MusicItem> items = allMusicItems.stream()
    		.filter(item -> item.getArtist().equals(item.getTitle()))
    		.collect(Collectors.toList());

    assertEquals(2, items.size());
    assertEquals(Long.valueOf(6), items.get(0).getId());
    assertEquals(Long.valueOf(7), items.get(1).getId());
  }
  
  /**
   * TASK: find all MusicItems whose price is less than 12.00 and sort them by genre (MusicCategory).
   * The natural order of an enum is the order in which they're declared (it's not alphabetical).
   * We'll provide that for you here: BLUES, CLASSICAL, JAZZ, RAP, COUNTRY, POP, ALTERNATIVE, ROCK, CLASSIC_ROCK
   * 
   * RESULT:
   */
  @Test
  public void testPriceLessThanSortMusicCategory() {
	  List<MusicItem> items = allMusicItems.stream()
			  .filter(item -> item.getPrice() < 12.00)
			  .sorted(Comparator.comparing(item -> item.getMusicCategory()))
			  .collect(Collectors.toList());
	  
	  assertEquals( 5, items.size() );
	  assertEquals( MusicCategory.CLASSICAL, items.get(0).getMusicCategory() );
	  assertEquals( MusicCategory.COUNTRY, items.get(1).getMusicCategory() );
	  assertEquals( MusicCategory.ROCK, items.get(2).getMusicCategory() );
	  assertEquals( MusicCategory.ROCK, items.get(3).getMusicCategory() );
	  assertEquals( MusicCategory.CLASSIC_ROCK, items.get(4).getMusicCategory() );
  }
  
  /**
   * TASK: find all "rock" items under 15 dollars and sort them by release date (oldest first).
   * 
   * RESULT:
   */
  @Test
  public void testSortMusicCategorySortReleaseDateDesc() {
	  List<MusicItem> items = allMusicItems.stream()
			  .filter(item -> item.getMusicCategory().equals(MusicCategory.ROCK))
			  .filter(item -> item.getPrice() < 15.00)
			  .sorted(Comparator.comparing(item -> ((MusicItem) item).getReleaseDate()))
			  .collect(Collectors.toList());
	  
	  System.out.println(items);
	  assertEquals( 3, items.size() );
	  assertEquals( "1983-10-16", items.get(0).getReleaseDate().toString() );
	  assertEquals( "1984-08-19", items.get(1).getReleaseDate().toString() );
	  assertEquals( "1997-01-19", items.get(2).getReleaseDate().toString() );
  }
  
  /**
   * TASK: find all items that cost more than 17 dollars and sort them by price descending, then by artist.
   * You can use visual inspection (sysout) on this one if you're getting tight on time.
   * 
   * RESULT:
   */
  @Test
  public void testPriceGreaterThanSortPriceDescThenMusicCategory() {
    List<MusicItem> items = allMusicItems.stream()
    		.filter(item -> item.getPrice() > 17.00)
    		.sorted(Comparator.comparing(item -> ((MusicItem) item).getPrice()).reversed()
    				.thenComparing(item -> ((MusicItem) item).getArtist()))
    		.collect(Collectors.toList());
	  System.out.println(items);
	  assertEquals( 7, items.size() );
	  assertEquals( "Aerosmith", items.get(0).getArtist() );
	  assertEquals( "Clay", items.get(1).getArtist() );
	  assertEquals( "Def Leppard", items.get(2).getArtist() );
  }
  
  /**
   * TASK: find all items under 12 dollars released in the 80s and sort them by artist.
   * Hint: To see if an item was released in the 80s, use item.getReleaseDate().toString().startsWith("198")
   * 
   * RESULT:
   */
  @Test
  public void testReleaseDateSortArtist() {
	  List<MusicItem> items = allMusicItems.stream()
			  .filter(item -> item.getPrice() < 12.00)
			  .filter(item -> item.getReleaseDate().toString().startsWith("198"))
			  .sorted(Comparator.comparing(item -> ((MusicItem) item).getArtist()))
			  .collect(Collectors.toList());
	  
	  assertEquals( 4, items.size() );
	  assertEquals( "Bobs", items.get(0).getArtist() );
	  assertEquals( "Yes", items.get(3).getArtist() );
  }
}