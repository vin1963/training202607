package controller;

import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Employee;
import model.EmployeeDAO;
import model.MyRepository;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {
	MyRepository<Employee,Integer> empdao=new EmployeeDAO();
	
    @GET
	public Response getAllEmployee() {
	   List<Employee> data= empdao.findAll();	
	   return Response.ok(data).build();
	}
    @GET
    @Path("/{eid}")
    public Response findById(@PathParam("eid")int id) {
    	   Optional<Employee> found=empdao.findById(id);
    	   if(found.isPresent()) {
    		   return Response.ok(found.get()).build();
    	   }else {
    		   return Response.status(Response.Status.NOT_FOUND ).build();
    	   }
    }
    @GET
    @Path("/department/{dpt}")
    public Response findByDepartment(@PathParam("dpt")String department) {
    	   List<Employee> emps= empdao.findByDepartment(department);
    	   if(emps!=null) {
    		   return Response.ok(emps).build();
    	   }else {
    		   return Response.noContent().build();
    	   }
    }
    @GET
    @Path("/employeename/{name}")
    public Response findEmployeeName(@PathParam("name")String name) {
    	   boolean b=empdao.existsByName(name);
    	   if(b) {
    		   String msg=String.format("{\"employeeName\":\"%s\"}", name);
    		   return Response.ok(msg).build();
    	   }else {
    		   return Response.status(Response.Status.NOT_FOUND ).build();
    	   }
    }
    @POST
    public Response saveEmployee(Employee emp) {
    	     Employee saved=empdao.save(emp);
    	     if(saved!=null) {
    	    	     return Response.ok(saved).build();
    	     }else {
    	    	   return Response.noContent().build();
    	     }
    }
    
    @PUT
    @Path("/{eid}")
    public Response updateById(@PathParam("eid")int id,Employee emp) {
    	   emp.setId(id);
    	   Employee found=empdao.update(emp);
    	   if(found!=null) {
    		   return Response.ok(found).build();
    	   }else {
    		   return Response.status(Response.Status.NOT_FOUND ).build();
    	   }
    }
    
    @DELETE
    @Path("/{eid}")
    public Response deleteById(@PathParam("eid")int id) {
    	      try {
    	       empdao.deleteById(id);    	  
    		   return Response.ok("delete success").build();
    	      }catch(Exception ex) {
    	    	      System.out.println("delete employee error "+ex.getMessage());
    		      return Response.status(Response.Status.NOT_FOUND ).build();
    	      }
    	   
    }
}
