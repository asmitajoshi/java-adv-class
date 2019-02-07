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

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class CollectorsTest {
  
  private Collection<MusicItem> allMusicItems;
  
  @Before
  public void setUp() {
    Catalog catalog = new InMemoryCatalog();
    allMusicItems = catalog.getAll();
  }
  
	@Test
	public void testCollectorsDefaultImpl() {
	  // TODO: obtain a List of music categories via Collectors.toList() and print the name of the List impl class
	  List<MusicCategory> cats = allMusicItems.stream()
			  .map(MusicItem::getMusicCategory)
			  .collect(Collectors.toList());
	  // TODO: verify size of list via JUnit assertion (should be same as size of allMusicItems)
	  assertEquals( allMusicItems.size(), cats.size() );
	  
	  // TODO: obtain a Set of music categories via Collectors.toSet() and print the name of the Set impl class
	  Set<MusicCategory> catsEt = allMusicItems.stream()
			  .map(MusicItem::getMusicCategory)
			  .collect(Collectors.toSet());
	  
	  // TODO: verify size of set (hint: no duplicates)
	  assertTrue( allMusicItems.size() > catsEt.size() );
	  
	}
	
	@Test
	public void testCollectorsSpecifiedImpl() {
	  // TODO: obtain a Deque (double-ended queue, pronounced "deck") of music items, sorted by artist natural order
	  // Use java.util.ArrayDeque as the implementation - it is a standard java.util collection
	  Deque<MusicItem> dq = new ArrayDeque(allMusicItems.stream()
			  .sorted(Comparator.comparing(item -> ((MusicItem) item).getArtist()))
	  .collect(Collectors.toList()));
    // TODO: verify the specified Deque impl class is being used
	  
	  // TODO: verify first and last elements in the collection are what you expect (hint: see Deque API)
	  // First artist is Aerosmith (id of 15), last artist is Yes (id of 16)
	  assertEquals( dq.getFirst().getArtist(), "Aerosmith" );
	  assertEquals( dq.getLast().getArtist(), "Yes" );

	  Deque<MusicItem> dq1 = allMusicItems.stream()
			  .sorted(Comparator.comparing(item -> ((MusicItem) item).getArtist()))
	  .collect(Collectors.toCollection(ArrayDeque::new));
	  assertEquals( dq1.getFirst().getArtist(), "Aerosmith" );
	  assertEquals( dq1.getLast().getArtist(), "Yes" );
	}
	
	@Test
  public void testCollectorsReturnType() {
    // TODO: change the type of the "result" variable from Object to a more specific, more meaningful type
    // Hint: See javadoc for Collectors.joining()
	  String result = allMusicItems.stream()
	    .filter(item -> item.getMusicCategory() == MusicCategory.POP)
	    .map(MusicItem::getArtist)
	    .sorted()
	    .collect(Collectors.joining("|"));

	  // TODO: write a JUnit assertion to verify the result value
	  assertEquals( "Annie Lennox|Peter Gabriel|Seal|Sting", result );
  }
}
