package model;

import java.util.*;

import config.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CoffeeDAO {
	public List<Coffee> findBySupId(int sid) {
		EntityManager em = JpaUtil.createEntityManager();
		try {
			TypedQuery<Coffee> q = em.createQuery("SELECT c FROM Coffee c WHERE c.supplier.supId = :supId",
					Coffee.class);
			q.setParameter("supId", sid); // 設定 JPQL 參數
			List<Coffee> coffees = q.getResultList();
			return coffees;
		} finally {
			em.close();
		}
	}
	public Coffee saveCoffee(Coffee cf) {
	  EntityManager em=JpaUtil.createEntityManager();
    	  EntityTransaction tx=em.getTransaction();
 	   try {
 		   Coffee found=em.find(Coffee.class, cf.getCofName());
 		   if(found==null) {
 			   tx.begin();
 			   em.persist(cf);
 			   tx.commit();
 			   return cf;
 		   }else {
 		      return null;
 		   }
 		   
 	   }catch(Exception ex) {
 		  System.out.println("save coffee jpa error "+ex.getMessage());
 		  throw ex;   
 	   }    	   
 	   finally {
 		   if(tx.isActive())tx.rollback();
 		   em.close();    		   
 	   }
	}
	public Coffee updateCoffee(String cofName , Coffee cof) {
		 EntityManager em=JpaUtil.createEntityManager();
		 Coffee existing = em.find(Coffee.class, cofName);
		 if (existing == null) return null;
		 em.getTransaction().begin();
		 existing.setPrice(cof.getPrice());         // 更新各欄位
		 existing.setSales(cof.getSales());
		 if (cof.getSupplier() != null) {
		     existing.setSupplier(cof.getSupplier());  // 可選：更新供應商
		 }
		 existing.setTotal(cof.getTotal());
		 Coffee cf=em.merge(existing);
		 em.getTransaction().commit();
		 return cf;
	}
}
