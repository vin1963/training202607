package model;

import java.util.List;
import java.util.Optional;

import config.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CoffeeDAOImpl implements CoffeeDAO{

	@Override
	public List<Coffee> findAll() {
		// TODO Auto-generated method stub
		EntityManager mgr=JpaUtil.createEntityManager();
		List<Coffee> data=mgr.createNamedQuery("Coffee.findAll", Coffee.class).getResultList();
		return data;
	}

	@Override
	public Optional<Coffee> findByName(String name) {
		// TODO Auto-generated method stub
		EntityManager mgr=JpaUtil.createEntityManager();
		Coffee cf=mgr.find(Coffee.class, name);
		return Optional.ofNullable(cf);
	}

	@Override
	public Coffee save(Coffee cof) {
		EntityManager em = JpaUtil.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        em.persist(cof);      // INSERT INTO employees ...
	        tx.commit();
	        return cof;           // emp 的 id 會被自動填入
	    } catch (Exception e) {
	        if (tx.isActive()) tx.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public Optional<Coffee> update(String name, Coffee cof) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Coffee deleteById(String name) {
		// TODO Auto-generated method stub
		return null;
	}
   
}
