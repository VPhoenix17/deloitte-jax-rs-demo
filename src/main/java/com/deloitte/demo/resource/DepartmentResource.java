package com.deloitte.demo.resource;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.repository.DepartmentRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
public class DepartmentResource {

    private final DepartmentRepository departmentRepository = new DepartmentRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") int id) {
        Department department = departmentRepository.getDepartmentById(id);
        if (department == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
        return Response.ok(department).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDepartment(Department department) {
        departmentRepository.addDepartment(department);
        return Response.status(Response.Status.CREATED).entity(department).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@PathParam("id") int id, Department updatedDepartment) {
        departmentRepository.updateDepartment(id, updatedDepartment);
        Department newDepartment = departmentRepository.getDepartmentById(id);
        return Response.status(Response.Status.OK).entity(newDepartment).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(@PathParam("id") int id) {
        departmentRepository.deleteDepartment(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Clean up resources
    @DELETE
    @Path("/close")
    @Produces(MediaType.APPLICATION_JSON)
    public Response close() {
        departmentRepository.close();
        return Response.ok("Resources closed").build();
    }
}
