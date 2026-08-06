package model;
import java.util.*;

import config.JpaUtil;
import jakarta.persistence.*;

public class OfficeDAO implements DAO<Office,String> {

	@Override
	public List<Office> findAll() {
		// TODO Auto-generated method stub
		EntityManager em=JpaUtil.createEntityManager();
		TypedQuery<Office> q=em.createQuery("select o from Office o ",Office.class);
		return q.getResultList();
	}

	@Override
	public Office findByKey(String id) {
		// TODO Auto-generated method stub
		EntityManager em=JpaUtil.createEntityManager();
		Office found=em.find(Office.class, id);	
		
		try {
			if(found==null)
				return null;				
			else {				
				return found;
			}
				
			
		}finally {
			em.close();
		}
	}

	@Override
	public Office save(Office obj) {
		// TODO Auto-generated method stub
		obj.getEmployees().forEach(e1->e1.setOffice(obj));
		EntityManager em=JpaUtil.createEntityManager();
		Office found=em.find(Office.class, obj.getOfficeCode());		
		EntityTransaction tx=em.getTransaction();
		try {
			if(found!=null)return null;
			tx.begin();
			em.persist(obj);
			tx.commit();			
			return obj;
		}catch(Exception ex) {
			System.out.println("save office error "+ex.getMessage());
			if(tx.isActive())tx.rollback();
			return null;
		}finally {
			em.close();
		}
		
	}

	@Override
	public Office update(String id, Office updated) {
		// TODO Auto-generated method stub		
		EntityManager em=JpaUtil.createEntityManager();
		Office found=em.find(Office.class, id);		
		EntityTransaction tx=em.getTransaction();
		try {
			if(found==null)return null;
			tx.begin();
			found.setAddressLine1(updated.getAddressLine1());
			found.setAddressLine2(updated.getAddressLine2());
			found.setCity(updated.getCity());
			found.setCountry(updated.getCountry());
			found.setPhone(updated.getPhone());
			found.setPostalCode(updated.getPostalCode());
			found.setState(updated.getState());
			found.setTerritory(updated.getTerritory());
			Office rs=em.merge(found);
			tx.commit();			
			return rs;
		}catch(Exception ex) {
			System.out.println("update office error "+ex.getMessage());
			if(tx.isActive())tx.rollback();
			return null;
		}finally {
			em.close();
		}
	}

	@Override
	public Office deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
