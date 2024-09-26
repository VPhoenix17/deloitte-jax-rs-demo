package com.deloitte.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.deloitte.demo.model.Department;

public class DepartmentRepository{
	 
		private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");
		private EntityManager entityManager = entityManagerFactory.createEntityManager();
			public List<Department> getAllDepartments() {
	        TypedQuery<Department> query = entityManager.createQuery("SELECT d FROM Department d", Department.class);
	        return query.getResultList();
	    }

	    // Get department by ID
	    public Department getDepartmentById(int id) {
	        return entityManager.find(Department.class, id);
	    }

	    // Add new department
	    public void addDepartment(Department department) {
	        entityManager.getTransaction().begin();
	        entityManager.persist(department);
	        entityManager.getTransaction().commit();
	    }

	    // Update existing department
	    public void updateDepartment(int id, Department updatedDepartment) {
	        entityManager.getTransaction().begin();
	        Department department = entityManager.find(Department.class, id);

	        if (department != null) {
	            department.setName(updatedDepartment.getName());
	            department.setLocation(updatedDepartment.getLocation());
	        }

	        entityManager.getTransaction().commit();
	    }

	    // Delete department by ID
	    public void deleteDepartment(int id) {
	        entityManager.getTransaction().begin();
	        Department department = entityManager.find(Department.class, id);

	        if (department != null) {
	            entityManager.remove(department);
	        }

	        entityManager.getTransaction().commit();
	    }

	    // Clean up resources
	    public void close() {
	        entityManager.close();
	        entityManagerFactory.close();
	    }
	 
}