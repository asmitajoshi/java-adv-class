/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.personnel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Department {
  private String name;
  private Collection<Employee> employees = new ArrayList<>();
  private double totalSalary = 0;
  
  public static boolean DEBUG = false;
  private final Lock lock = new ReentrantLock();

  public Department() {
  }

  public Department(String name) {
    setName(name);
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public Collection<Employee> getEmployees() {
    return Collections.unmodifiableCollection(employees);
  }

  // helper method to add an Employee to the collection
  public void addEmployee(Employee emp) {
    employees.add(emp);
  }

  public int getEmployeeCount() {
    return employees.size();
  }

  public void listEmployees() {
    employees.forEach(System.out::println);
  }

  public double getTotalSalary() {
    return totalSalary;
  }

  public void setTotalSalary(double totalSalary) {
    if (DEBUG) System.out.println("Department.setTotalSalary: "+ totalSalary);
    this.totalSalary = totalSalary;
  }

  public void lock() {
    lock.lock();
  }

  public void unlock() {
    lock.unlock();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ": [name=" + getName() + "]";
  }
}