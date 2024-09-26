package com.deloitte.demo.model;


import javax.persistence.*; 

@Entity //Mandatory
@Table(name = "emps")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private double salary;
    
    @Column(name = "deptId")
    private int deptId;

    public Employee() {
    }

    public Employee(String name, double salary,int deptId) {
        this.name = name;
        this.salary = salary;
        this.deptId=deptId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public int getdeptId() {
        return deptId;
    }

    public void setdeptId(int deptId) {
        this.deptId = deptId;
    }


    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", dept=" + deptId + "]";
    }
}