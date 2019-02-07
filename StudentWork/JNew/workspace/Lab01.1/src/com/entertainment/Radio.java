/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * CopyrightLearningPatterns Inc.
 */
package com.entertainment;

public class Radio
implements Volume {
  // INSTANCE VARIABLES
  private int volume;
  
  // for muting behavior
  private int oldVolume;
  private boolean isMuted;

  // CONSTRUCTORS
  public Radio() {
  }

  public Radio(int volumeIn) {
    setVolume(volumeIn);
  }

  // ACCESSOR METHODS
  @Override  // interface Volume
  public void setVolume(int volumeIn) {
    volume = volumeIn;
  }

  @Override  // interface Volume
  public int getVolume() {
    return volume;
  }

  // BEHAVIORAL METHODS
  @Override  // interface Volume
  public void mute() {
    if (!isMuted()) { // not currently muted
      // store current volume in oldVolume
      oldVolume = volume;

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

  @Override  // interface Volume
  public boolean isMuted() {
    return isMuted;
  }

  // returns a string representation of this class instance
  @Override  // class Object
  public String toString() {
    // this method uses the "ternary" (three terms) operator
    // (expression ? true-value : false-value)
    // it's just a very compact if-else statement
    return "Radio: volume=" + (!isMuted() ? String.valueOf(volume) : "<muted>");
  }
}