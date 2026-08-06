package model;

import java.util.*;

public interface DAO<T,K> {
    List<T> findAll();
    T findByKey(K id);
    T save(T obj);
    T update(K id, T updated);
    T deleteById(K id);    
}
