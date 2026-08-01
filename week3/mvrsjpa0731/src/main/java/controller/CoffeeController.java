package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.*;
import java.util.*;

@Path("/coffees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CoffeeController {
	
	CoffeeDAO dao=new CoffeeDAOImpl();
	@GET
	public Response getAllCoffees() {
        List<Coffee> data=dao.findAll();
        if(data!=null && data.size()>0)
        	  return Response.ok(data).build();
        else
        	  return Response.noContent().build();
	}
	@GET
	@Path("/{name}")
	public Response findByName(@PathParam("name")String name) {
		Optional<Coffee> found= dao.findByName(name);
		if(found.isPresent()) {
			return Response.ok(found.get()).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	 @POST
	 public Response saveCoffee(Coffee cof) {
	    	     Coffee saved=dao.save(cof);
	    	     if(saved!=null) {
	    	    	     return Response.ok(saved).build();
	    	     }else {
	    	    	   return Response.noContent().build();
	    	     }
	 }

}
