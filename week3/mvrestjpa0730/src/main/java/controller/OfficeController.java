package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.*;
import java.util.*;

@Path("/offices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfficeController {
	 OfficeDAO dao=new OfficeDAO();
     
	 @GET
     public Response getAllOffice() {
    	    List<Office> data=dao.getAll();
    	    return Response.ok(data).build();
     }
     
}
