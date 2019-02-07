/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.db.internal;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.derby.tools.ij;

public class EmbeddedDerby {
  
  private static final String URL = "jdbc:derby:JavaTunesEmbeddedDB;create=true";
  
  public static Connection getConnection()
  throws Exception {
    return DriverManager.getConnection(URL);
  }
  
  private static void initializeDb(Connection conn)
  throws Exception {
    FileInputStream createScript = new FileInputStream("dbCreate.sql");
    FileInputStream queryScript  = new FileInputStream("dbQuery.sql");
    
    OutputStream out = new OutputStream() { 
      public void write(int b) { }
    };
    
    ij.runScript(conn, createScript, "UTF-8", out, "UTF-8");
    System.out.println("Tables created.");
    
    ij.runScript(conn, queryScript, "UTF-8", System.out, "UTF-8");
    System.out.println("Tables queried.");
  }
  
  private static void showConnection(Connection conn, String url)
  throws Exception {
    DatabaseMetaData metadata = conn.getMetaData();
    System.out.println("Connected to '" + url + "': " + metadata.getDatabaseProductName() + " " + metadata.getDatabaseMajorVersion() + "." + metadata.getDatabaseMinorVersion() + ".");
  }
  
  
  public static void main(String[] args)
  throws Exception {
    Connection conn = getConnection();
    showConnection(conn, URL);
    if (args.length == 1) {
      if ("create".equals(args[0])) {
        initializeDb(conn);
      }
    }
    conn.close();
  }
}