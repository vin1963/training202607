package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import model.*;

@Path("/offices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OfficeController {
    DAO<Office,String> dao=new OfficeDAO();
	
    @GET
    public Response getAll(){
    	     List<Office> rs=dao.findAll();
    	     if(rs!=null && rs.size()>0)
    	    	    return Response.ok(rs).build();
    	     else
	        return Response.status(Response.Status.NO_CONTENT).build();	
	}
    
    @POST
    public Response saveOffice(Office ofc) {
    	     Office rs= dao.save(ofc);
    	     if(rs!=null)
 	    	    return Response.ok(rs).build();
 	     else
	        return Response.status(Response.Status.NOT_FOUND).build();	
    }
    
    @PUT
    @Path("/{code}")
    public Response updateOffice(@PathParam("code")String code ,Office ofc) {
    	     Office rs= dao.update(code, ofc);
	     if(rs!=null)
	    	    return Response.ok(rs).build();
	     else
        return Response.status(Response.Status.NOT_FOUND).build();	
    }
    
    @GET
    @Path("/{code}")
    public Response findOffice(@PathParam("code")String code ) {
    	     Office rs= dao.findByKey(code);
	     if(rs!=null)
	    	    return Response.ok(rs).build();
	     else
        return Response.status(Response.Status.NOT_FOUND).build();	
    }
}
