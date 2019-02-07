/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.personnel;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class RaceConditionTest {
  // use for performance testing to give reasonable load
  // private static final int EMPS_PER_DEPT = 1_000_000;
  
  // use for testing correctness so we can more easily view intermediate steps
  private static final int EMPS_PER_DEPT = 100;
  
  private static final double STARTING_SALARY = 1000;
  private static final double SALARY_INCREMENT = 10;
  
  private static Department dept;
  
  /**
   * Creates Department and populates it with EMPS_PER_DEPT Employees.
   */
  @BeforeClass
  public static void setUpDepartment() {
    dept = new Department("Racing Department");
    for (int i = 1; i <= EMPS_PER_DEPT; i++) {
      Employee emp = new Employee("Employee-" + i, STARTING_SALARY, dept);
      dept.addEmployee(emp);
    }
  }

  @Test
  public void testRaceCondition() {
    System.out.println("Salary data before incrementing employee salaries");
    displayAverageSalaries();
    System.out.println();
    
    // Employee.DEBUG = true;
    // Department.DEBUG = true;
    
    long duration = measurePerformance(false);  // serial
    // long duration = measurePerformance(true);  // parallel
    System.out.println("Fastest duration: " + duration/1000000.0 + "ms");
    System.out.println();
    
    System.out.println("Salary data after incrementing employee salaries");
    displayAverageSalaries();
    System.out.println();
    
    // correctness assertion
    double avgSalFromEmployees = averageSalaryFromEmployees();
    double avgSalFromDepartment = averageSalaryFromDepartment();
    assertEquals(avgSalFromEmployees, avgSalFromDepartment, .001);
  }
  
  
  /**
   * TODO: get employees from department, create serial stream, calculate and return average salary
   */
  private double averageSalaryFromEmployees() {
    double avgSal = 0.0;
    return avgSal;
  }
  
  private double averageSalaryFromDepartment() {
    return dept.getTotalSalary() / dept.getEmployeeCount();
  }
  
  /**
   * TODO: create a stream from the department's employees (serial or parallel, depending on boolean flag).
   * Use forEach() on the stream to increment each employee's salary by SALARY_INCREMENT.
   * Your lambda will be of the form: emp -> emp.setSalary(emp.getSalary() + SALARY_INCREMENT).
   */
  private long measurePerformance(boolean parallel) {
    int iterations = 10;
    long fastest = Long.MAX_VALUE;
    
    for (int i = 1; i <= iterations; i++) {
      long start = System.nanoTime();
      
      // TODO: create appropriate stream (serial or parallel) here and do the salary increment
      
      long duration = System.nanoTime() - start;
      System.out.println("Duration: " + duration/1000000.0 + "ms");
      if (duration < fastest) {
        fastest = duration;
      }
    }
    return fastest;
  }
  
  private void displayAverageSalaries() {
    System.out.println("*** Calc from Department ***");
    System.out.println("Total salary: " + dept.getTotalSalary() + " | Employee count: " + dept.getEmployeeCount() + " | Average salary: " + averageSalaryFromDepartment());
    System.out.println("*** Calc from Employees ***");
    System.out.println("Average salary: " + averageSalaryFromEmployees());
  }
}