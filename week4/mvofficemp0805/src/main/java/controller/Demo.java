package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/demo")
public class Demo {
    @GET
    @Produces(MediaType.TEXT_HTML)
	public String test() {
    	   return "Offices/Employees test"; 
    }
}
