package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
	
	@GET
	@Produces(MediaType.TEXT_HTML+";charset=utf-8")
    public String say() {
    	   return "<h1>大家早安</h1>";
    }
}
