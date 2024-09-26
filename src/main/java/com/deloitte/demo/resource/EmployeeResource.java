package com.deloitte.demo.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.repository.EmployeeRepository;

@Path("/employees")
public class EmployeeResource {

	private EmployeeRepository employeeRepository = new EmployeeRepository();;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}
	
	
	// get by ID
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployeeById(@PathParam("id") int id) {
	    return employeeRepository.getEmployeeById(id);
	}
	
	// Add via Post
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		employeeRepository.addEmployee(employee);
	    List<Employee> allEmployees = employeeRepository.getAllEmployees();  
	    return Response.status(Response.Status.CREATED).entity(allEmployees).build();
	}
	
	// Update details
		@PUT
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateEmployee(@PathParam("id") int id, Employee employee) {
		    Employee existingEmployee = employeeRepository.getEmployeeById(id);
		    
		    if (existingEmployee == null) {
		        return Response.status(Response.Status.NOT_FOUND).build();
		    }
		    existingEmployee.setName(employee.getName());
		    existingEmployee.setSalary(employee.getSalary());
		    existingEmployee.setdeptId(employee.getdeptId());
		    employeeRepository.updateEmployee(employee.getId(),existingEmployee);
		    List<Employee> allEmployees = employeeRepository.getAllEmployees();  
		    return Response.status(Response.Status.OK).entity(allEmployees).build();
		}
		
		// Delete Employee details
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteEmployee(@PathParam("id") int id) {
		    Employee employee = employeeRepository.getEmployeeById(id);
		    if (employee == null) {
		        return Response.status(Response.Status.NOT_FOUND).build();
		    }
		    employeeRepository.deleteEmployee(id);
		    List<Employee> allEmployees = employeeRepository.getAllEmployees();  
		    return Response.status(Response.Status.OK).entity(allEmployees).build();
		}

	
	

	
	


//	implement these methods - 
//	getEmployeeById
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	addEmployee
//	updateEmployee 
//	deleteEmployee 

}