/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.entertainment;

public class Television
implements Volume {
  // CLASS (static) VARIABLES
  public static final int MIN_VOLUME = 0;
  public static final int MAX_VOLUME = 100;

  public static final String DEFAULT_BRAND  = "RCA";
  public static final int    DEFAULT_VOLUME = 10;

  public static final String[] VALID_BRANDS = {"Sony", "Zenith", "Hitachi", "RCA"};

  // INSTANCE VARIABLES
  private String brand;
  private int volume;

  // for muting behavior
  private int oldVolume;
  private boolean isMuted;

  // CONSTRUCTORS
  public Television() {
    // call "detailed" constructor with default values
    this(DEFAULT_BRAND, DEFAULT_VOLUME);
  }

  public Television(String brandIn) {
    // call "detailed" constructor with supplied brand and default volume
    this(brandIn, DEFAULT_VOLUME);
  }

  public Television(int volumeIn) {
    // call "detailed" constructor with default brand and supplied volume
    this(DEFAULT_BRAND, volumeIn);
  }

  public Television(String brandIn, int volumeIn) {
    // call setter methods to actually set the data
    setBrand(brandIn);
    setVolume(volumeIn);

    if (getBrand() == null) {
      setBrand(DEFAULT_BRAND);
    }
  }

  // BEHAVIORAL METHODS
  @Override  // interface Volume
  public void mute() {
    if (!isMuted()) { // not currently muted
      // store current volume in oldVolume
      oldVolume = getVolume();

      // set volume to zero
      setVolume(0);
    }
    else { // currently muted
      // restore volume from oldVolume value
      setVolume(oldVolume);
    }
    // toggle muted flag
    isMuted = !isMuted;
  }

  // ACCESSOR METHODS
  public void setBrand(String brandIn) {
    if (isValidBrand(brandIn)) {
      brand = brandIn;
    }
  }

  public String getBrand() {
    return brand;
  }
  
  @Override  // interface Volume
  public void setVolume(int volumeIn) {
    int new_volume = Math.min(volumeIn, MAX_VOLUME);
    volume = Math.max(new_volume, MIN_VOLUME);
  }
  
  @Override  // interface Volume
  public int getVolume() {
    return volume;
  }
  
  @Override  // interface Volume
  public boolean isMuted() {
    return isMuted;
  }

  // returns a string representation of this class instance
  @Override  // class Object
  public String toString() {
    // display <muted> instead of 0 if currently muted
    String volumeDisplayMessage;

    if (!isMuted()) { // not currently muted
      volumeDisplayMessage = String.valueOf(getVolume());
    }
    else {
      volumeDisplayMessage = "<muted>";
    }
    return "Television: brand=" + getBrand() + ", volume=" + volumeDisplayMessage;
  }

  // PRIVATE METHODS
  private static boolean isValidBrand(String brandIn) {
    // declare return value
    boolean isValid = false;

    // iterate over VALID_BRANDS, looking for a match
    for (String curBrand : VALID_BRANDS) {
      if (brandIn.equals(curBrand)) {
        isValid = true; // got a match
        break; // terminate search
      }
    }
    return isValid;
  }
}