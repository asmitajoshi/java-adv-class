/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.entertainment;

import static org.junit.Assert.*;
import org.junit.Test;

public class InterfaceMethodsTest {
  
  @Test
  public void testDefaultMethods() {
    // create instances of Volume implementations
    Television tv = new Television("Zenith", 17);
    Radio r = new Radio(5);
    
    // create the Volume array to hold them
    Volume[] volArray = {tv, r};
    
    // iterate over the array, silencing each element in it, then testing that it worked
    for (Volume vol : volArray) {
      vol.silence();
      assertTrue("volume should be zero", vol.getVolume() == 0);
    }
  }
  
  @Test
  public void testStaticMethods() {
    Television tv = new Television("Zenith", 17);
    Radio r = new Radio(5);
    
    // create the Volume array to hold them
    Volume[] volArray = {tv, r};
    
    Volume.silenceAll(volArray);
    
    // iterate over the array, testing that they are all silent
    for (Volume vol : volArray) {
      // all items should be at zero volume
      assertTrue("volume should be zero", vol.getVolume() == 0);
    }
  }
}