/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Id;

public class MusicItem
implements Serializable {
  // PROPERTIES
  @Id
  private Long id;  // PK column in ITEM table
  private String num;
  private String title;
  private String artist;
  private Date releaseDate;
  private BigDecimal listPrice;
  private BigDecimal price;
  
  // CONSTRUCTORS
  public MusicItem() { 
  }

  public MusicItem(String num, String title, String artist, Date releaseDate, BigDecimal listPrice, BigDecimal price) {
    this.setNum(num);
    this.setTitle(title);
    this.setArtist(artist);
    this.setReleaseDate(releaseDate);
    this.setListPrice(listPrice);
    this.setPrice(price);
  }
  
  // ACCESSOR METHODS
  public Long getId() {
    return id;
  }

  public String getNum() {
    return num;
  }
  public void setNum(String num) {
    this.num = num;
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }
  public void setArtist(String artist) {
    this.artist = artist;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }
  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public BigDecimal getListPrice() {
    return listPrice;
  }
  public void setListPrice(BigDecimal listPrice) {
    this.listPrice = listPrice;
  }

  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  
  @Override
  public String toString() {
    return this.getClass().getName() + ": id=" + this.getId() 
      + " num=" + this.getNum() + " title=" + this.getTitle() 
      + " artist=" + this.getArtist() + " releaseDate=" + this.getReleaseDate()
      + " listPrice=" + this.getListPrice() + " price=" + this.getPrice();
  }
}