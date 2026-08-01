package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.*;
public class OfficeDAO {
      EntityManager  createConnection(){
    	     try {
    	      EntityManagerFactory factory=Persistence.createEntityManagerFactory("mvrestjpa0730");
    	      return factory.createEntityManager();
    	     }catch(Exception ex) {
    	    	    System.out.println("createConnection error "+ex.getMessage());
    	    	    return null;
    	     }
      }
      public List<Office> getAll(){
    	      EntityManager mgr= createConnection();
    	      return mgr.createNamedQuery("Office.findAll").getResultList();
      }
}
