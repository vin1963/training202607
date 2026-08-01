package model;

import java.util.*;

public class ProductDAO {
    static List<Product> products=new ArrayList<>();
    static {
    	   products.add(new Product("P01","Apple",30.0));
    	   products.add(new Product("P02","Banana",10.0));
    	   products.add(new Product("P03","Cherry",300.0));
    }
    public List<Product> getAll(){
    	    return products;
    }
    
    public Optional<Product> find(String id) {
    	   return  products.stream().filter(p-> p.getPid().equalsIgnoreCase(id)).findFirst();
    }
    
    public Optional<Product> findByName(String name) {
 	   return  products.stream().filter(p-> p.getName().equalsIgnoreCase(name)).findFirst();
 }
    public boolean addProduct(Product p) {
    	    return products.add(p);
    }
    
    public Product updateById(String id,Product updateObj) {
    	    Product obj=products.stream().filter(p-> p.getPid().equalsIgnoreCase(id))
    	    		               .findFirst().orElse(null);
    	    if(obj!=null) {
    	    	    obj.setPid(id);
    	    	    obj.setName(updateObj.getName());
    	    	    obj.setPrice(updateObj.getPrice());
    	    	    return obj;
    	    }else {
    	    	   return null;
    	    }
    }
    
    public Product deleteById(String id) {
//    	    Optional<Product>  found=products.stream().
//    	    		   filter(p-> p.getPid().equalsIgnoreCase(id)).findFirst();
//    	    if(found.isPresent()) {
//    	    	  Product del=found.get();
//    	    	  products.remove(del);
//    	    	  return del;
//    	    }else {
//    	    	    return null;
//    	    }
      	 Optional<Product>  found=products.stream().
	    		   filter(p-> p.getPid().equalsIgnoreCase(id)).findFirst();
    	      boolean flag=products.removeIf(p-> p.getPid().equalsIgnoreCase(id));
    	      if(flag) {
              return found.get();  	    	      
    	      }else {
    	    	      return null;
    	      }
    }
}
