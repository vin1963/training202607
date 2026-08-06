package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.*;
import java.util.*;

@Path("/coffees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeController {
	CoffeeDAO dao=new CoffeeDAO();
	
    @POST
    public Response saveCoffee(Coffee cf) {
    	   Coffee saved= dao.saveCoffee(cf);
    	   if(saved!=null) {
    		   return Response.ok(saved).build();    		   
    	   }else {
    		   return Response.status(Response.Status.BAD_REQUEST).build();
    	   }
    }
    @PUT
    @Path("/{name}")
    public Response updateCoffee(@PathParam("name")String name,Coffee cof) {
    	   Coffee c= dao.updateCoffee(name, cof);
    	   if(c!=null) {
    		   return Response.ok(c).build();    		   
    	   }else {
    		   return Response.status(Response.Status.NOT_FOUND).build();
    	   }
    }
}
