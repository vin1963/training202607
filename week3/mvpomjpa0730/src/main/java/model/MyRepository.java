package model;
import java.util.*;

public interface MyRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    T update(T entity);
    void deleteById(ID id);
    boolean existsByName(String name);
    List<Employee> findByDepartment(String dept);
}
