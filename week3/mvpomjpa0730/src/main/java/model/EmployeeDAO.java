package model;

import java.util.*;

import config.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
public class EmployeeDAO implements MyRepository<Employee,Integer> {
	
    public static List<Employee>  getAll(){
    	  EntityManager mgr= JpaUtil.createEntityManager();
    	  TypedQuery<Employee> query =mgr.createQuery("select e from Employee e", Employee.class);
    	  return query.getResultList();
    }

	@Override
	public Employee save(Employee emp) {
		EntityManager em = JpaUtil.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        em.persist(emp);      // INSERT INTO employees ...
	        tx.commit();
	        return emp;           // emp 的 id 會被自動填入
	    } catch (Exception e) {
	        if (tx.isActive()) tx.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		// TODO Auto-generated method stub
		 EntityManager em = JpaUtil.createEntityManager();
		    try {
		    	    Employee found=em.find(Employee.class, id);
		        return Optional.ofNullable(found);
		    } finally {
		        em.close();
		   }
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return getAll();
	}

	@Override
	public Employee update(Employee emp) {
		// TODO Auto-generated method stub
		EntityManager em = JpaUtil.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        Employee found=em.find(Employee.class, emp.getId());
	        if(found==null) return null;
	        found.setEmail(emp.getEmail());
	        found.setDepartment(emp.getDepartment());
	        found.setName(emp.getName());
	        found.setSalary(emp.getSalary());
	        found.setHireDate(emp.getHireDate());
	        Employee merged = em.merge(found);  // UPDATE employees SET ...
	        tx.commit();
	        return merged;                    // 回傳受管理的 entity
	    } catch (Exception e) {
	        if (tx.isActive()) tx.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		 EntityManager em = JpaUtil.createEntityManager();
		    EntityTransaction tx = em.getTransaction();
		    try {
		        tx.begin();
		        Employee emp = em.find(Employee.class, id);
		        if (emp != null) em.remove(emp);  // DELETE FROM employees WHERE id=?
		        tx.commit();
		    } catch (Exception e) {
		        if (tx.isActive()) tx.rollback();
		        throw e;
		    } finally {
		        em.close();
		    }
	}
    @Override
	public List<Employee> findByDepartment(String dept) {
		EntityManager em = JpaUtil.createEntityManager();
	    return em.createQuery(
	            "SELECT e FROM Employee e WHERE LOWER(e.department) = LOWER(:dept) ORDER BY e.name",
	            Employee.class)
	        .setParameter("dept", dept)
	        .getResultList();
	}
	
	@Override
	public boolean existsByName(String name) {
		EntityManager em = JpaUtil.createEntityManager();
	   List<Employee> r=em.createQuery(
	            "SELECT e FROM Employee e WHERE LOWER(e.name) = LOWER(:name) ",
	            Employee.class)
	        .setParameter("name", name).getResultList();
	        
		return r!=null && r.size()>0;
	}


}
