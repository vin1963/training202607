package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/helloworld")
public class HelloWorld {
	@GET
	@Produces(MediaType.TEXT_HTML+";charset=utf-8")
    public String say() {
    	   return "<h1>Hello World</h1>";
    }
}
