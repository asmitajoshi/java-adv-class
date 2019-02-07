/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.personnel;

public class Employee {
  private String name;
  private double salary;
  private Department dept;  // ref to owning Department
  
  public static boolean DEBUG = false;
  
  public Employee() {
  }
  
  public Employee(String name, double salary, Department dept) {
    this.dept = dept;
    setName(name);
    setSalary(salary);
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public double getSalary() {
    return salary;
  }
  
  /**
   * This method is responsible for two things:
   * 1. set this Employee's salary to its new value.
   * 2. update its Department's total salary to the new total value.
   */
  public void setSalary(double salary) {
    // dept.lock();
    double oldDeptTotal = dept.getTotalSalary();  // read current Department total
    if (DEBUG) System.out.println("Employee.setSalary: old department total = " + oldDeptTotal);
    
    double oldSalary = this.salary;
    this.salary = salary;
    
    double newDeptTotal = oldDeptTotal - oldSalary + salary;  // calculate new total
    if (DEBUG) System.out.println("Employee.setSalary: new department total = " + newDeptTotal);
    
    dept.setTotalSalary(newDeptTotal);  // write new Department total
    // dept.unlock();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ": [name=" + getName() + ", salary=" + getSalary() + "]";
  }
}