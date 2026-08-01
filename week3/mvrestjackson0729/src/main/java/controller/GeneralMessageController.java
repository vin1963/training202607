package controller;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Product;
import java.util.*;

import config.ApiResponse;

@Path("/general")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeneralMessageController {
	   static List<Product>  products=new ArrayList<Product>();
	   static {
	        products.add(new Product(1, "MacBook Pro", 59900.0));
	        products.add( new Product(2, "iPhone 15", 34900.0));
	        products.add( new Product(3, "AirPods Pro", 7990.0));
	    }
	   
	   @GET
	   public Response getAll() {
	       List<Product> list = products;
	       return Response.ok(ApiResponse.ok(list)).build();
	   }
	   @GET
	   @Path("/{id}")
	   public Response getById(@PathParam("id") int id) {
	       Product found=products.stream().filter(p->p.getId()==id).findAny().orElse(null);
	       if (found == null) {
	           return Response.status(Response.Status.NOT_FOUND)
	                   .entity(ApiResponse.error("Product not found: " + id))
	                   .build();
	       }
	       return Response.ok(ApiResponse.ok(found)).build();
	   }
}
