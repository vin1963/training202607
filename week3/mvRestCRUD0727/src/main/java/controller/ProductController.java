package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import model.*;

import java.net.URI;
import java.util.*;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {
    ProductdbDAO dao=new ProductdbDAO();
    
    @GET
    public Response getAllProducts(){
    	   List<Product> data=dao.getAll();
    	   if(data!=null && data.size()>0)
    	      return Response.ok(data).build();
    	   else
    		  return Response.status(Response.Status.NO_CONTENT).build();
    }
    @GET
    @Path("/query")
    public Response queryByName(@DefaultValue("na") @QueryParam("name") String name) {
    	    Optional<Product> found=dao.findByName(name);
    	    if (found.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Product not found: " + name)
                        .build();
            }
            return Response.ok(found.get()).build();
    	    
    }      
    
    @GET
    @Path("/{id}")
    public Response productgetById(@PathParam("id") String id) {
        Optional<Product> data= dao.find(id);
        if (data.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product not found: " + id)
                    .build();
        }
        return Response.ok(data.get()).build();
    }
    
    // ── @POST + @Context UriInfo ──────────────────────────────
    // 對應 POST /api/employees
    // @Context UriInfo 注入請求 URI 資訊，用來建構 Location Header
    // 成功回傳 201 Created，含 Location Header 指向新資源
    // 必填欄位不合法回傳 400 Bad Request
    //────────────────────────────────────────────────────────────
    @POST   
    public Response create(Product pt, @Context UriInfo uriInfo) {
        if (pt.getName() == null || pt.getName().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Product Name is required")
                    .build();
        }

        dao.addProduct(pt);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(pt.getPid()))
                .build();

        return Response.created(location)
                .entity(pt)
                .build();
    }
    
    @POST
    @Path("/myform")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response formcreate(@FormParam("pid")String pid,@FormParam("name")String name,@FormParam("price")double price,
    		                      @Context UriInfo uriInfo) {
        if (name == null || name.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Product Name is required")
                    .build();
        }
        Product pt=new Product(pid,name,price);
        dao.addProduct(pt);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(pt.getPid()))
                .build();

        return Response.created(location)
                .entity(pt)
                .build();
    }
    
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Product updated) {
    	    Product pt=dao.updateById(id, updated);
        if (pt==null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product not found: " + id)
                    .build();
        }
        if (updated.getName() == null || updated.getName().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Product Name is required")
                    .build();
        }       
        return Response.ok(pt).build();
    }
    
    // ── @PATCH + @Path + @PathParam + Map<String, Object> ────
    // 對應 PATCH /api/employees/{id}
    // 只更新請求中有提供的欄位，其餘保持不變
    // 數值型別轉換需用 ((Number) value).doubleValue()
    // 成功回傳 200 OK，不存在回傳 404
    //────────────────────────────────────────────────────────────
    @PATCH
    @Path("/{id}")
    public Response partialUpdate(@PathParam("id") String id,
                                  Map<String, Object> fields) {
        Optional<Product> found=dao.find(id);
        if (found.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product not found: " + id)
                    .build();
        }
        Product pt=found.get();
        fields.forEach((key, value) -> {
            switch (key) {
                case "name"      -> pt.setName((String) value);               
                case "price"     -> pt.setPrice(((Number) value).doubleValue());
            }
        });
        
        dao.updateById(id,pt);
        return Response.ok(pt).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
    	    Product obj=dao.deleteById(id);
        if (obj==null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product not found: " + id)
                    .build();
        }
       
        return Response.ok(obj).build();
    }
    
    
}
