package com.deloitte.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.deloitte.demo.model.Employee;

public class EmployeeService {

    
	private List<Employee> empList = new ArrayList<>(Arrays.asList(
		    new Employee("Sonu", 90.25,2), 
		    new Employee("Monu", 95.75,3), 
		    new Employee("Tonu", 92.25,4)
		));

    // get by ID
    public Employee getEmployeeById(int id) {
        Optional<Employee> employeeOpt = empList.stream().filter(emp -> emp.getId() == id).findFirst();
        return employeeOpt.orElse(null);
    }
    
    // add to List
    public void addEmployee(Employee employee) {
        empList.add(employee);
    }

    // update employee
    public void updateEmployee(Employee employee) {
        int index = empList.indexOf(getEmployeeById(employee.getId()));
        if (index >= 0) {
            empList.set(index, employee);
        }
    }

    // delete employee
    public void deleteEmployee(int id) {
        empList.removeIf(emp -> emp.getId() == id);
    }
}
