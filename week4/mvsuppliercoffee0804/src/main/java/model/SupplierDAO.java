package model;
import java.util.*;

import config.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class SupplierDAO {
	
    public List<Supplier> getAll(){
    	   EntityManager em=JpaUtil.createEntityManager();
    	   try {
    		  TypedQuery<Supplier> query= em.createQuery("select s from Supplier s join fetch s.coffees order by s.supId ", Supplier.class);
    		  return query.getResultList();
    		   
    	   }catch(Exception ex) {
    		  throw ex;   
    	   }    	   
    	   finally {
    		   em.close();    		   
    	   }
    }
    
    public Supplier save(Supplier sp) {
    	EntityManager em=JpaUtil.createEntityManager();
    	EntityTransaction tx=em.getTransaction();
 	   try {
 		   Supplier found=em.find(Supplier.class, sp.getSupId());
 		   if(found==null) {
 			   tx.begin();
 			   em.persist(sp);
 			   tx.commit();
 			   return sp;
 		   }else {
 		      return null;
 		   }
 		   
 	   }catch(Exception ex) {
 		  System.out.println("save jpa error "+ex.getMessage());
 		  throw ex;   
 	   }    	   
 	   finally {
 		   if(tx.isActive())tx.rollback();
 		   em.close();    		   
 	   }
    }
}
