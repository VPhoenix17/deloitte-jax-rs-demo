package com.deloitte.demo.repository;

import com.deloitte.demo.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    // Get all employees
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        return entityManager.find(Employee.class, id);
    }

    // Add new employee
    public void addEmployee(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    // Update existing employee
    public void updateEmployee(int id, Employee updatedEmployee) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);

        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
        }

        entityManager.getTransaction().commit();
    }

    // Delete employee by ID
    public void deleteEmployee(int id) {
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);

        if (employee != null) {
            entityManager.remove(employee);
        }

        entityManager.getTransaction().commit();
    }

    // Clean up resources
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}