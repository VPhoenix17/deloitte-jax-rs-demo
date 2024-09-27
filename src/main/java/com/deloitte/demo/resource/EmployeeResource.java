package com.deloitte.demo.resource;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.model.Department;
import com.deloitte.demo.repository.EmployeeRepository;
import com.deloitte.demo.repository.DepartmentRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final DepartmentRepository departmentRepository = new DepartmentRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
        return Response.ok(employee).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department department = departmentRepository.getDepartmentById(employee.getDepartment().getId());
            if (department != null) {
                employee.setDepartment(department);
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Department not found").build();
            }
        }
        employeeRepository.addEmployee(employee);
        System.out.println(employee);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") int id, Employee updatedEmployee) {
        if (updatedEmployee.getDepartment() != null && updatedEmployee.getDepartment().getId() != null) {
            Department department = departmentRepository.getDepartmentById(updatedEmployee.getDepartment().getId());
            if (department != null) {
                updatedEmployee.setDepartment(department);
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Department not found").build();
            }
        }
        employeeRepository.updateEmployee(id, updatedEmployee);
        Employee newEmployee = employeeRepository.getEmployeeById(id);
        return Response.status(Response.Status.OK).entity(newEmployee).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") int id) {
        employeeRepository.deleteEmployee(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Clean up resources
    @DELETE
    @Path("/close")
    @Produces(MediaType.APPLICATION_JSON)
    public Response close() {
        employeeRepository.close();
        departmentRepository.close();
        return Response.ok("Resources closed").build();
    }
}
