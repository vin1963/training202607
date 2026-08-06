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

@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierController {
	SupplierDAO dao = new SupplierDAO();

	@GET
	public Response findAllSuppliers() {
		List<Supplier> data = dao.getAll();
		if (data != null && data.size() > 0) {
			return Response.ok(data).build();
		} else {
			return Response.noContent().build();
		}
	}

	@POST
	public Response saveSupplier(Supplier sp) {
		try {
			sp.getCoffees().forEach(c -> c.setSupplier(sp));
			Supplier rs = dao.save(sp);
			return Response.ok(rs).build();
		} catch (Exception ex) {
			System.out.println("Save Error " + ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{id}/coffees")	
	public Response getCoffeesBySupplier(@PathParam("id") int id) {
		List<Coffee> coffees = new CoffeeDAO().findBySupId(id);
		if(coffees!=null && coffees.size()>0)
		   return Response.ok(coffees).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();

	}
}
