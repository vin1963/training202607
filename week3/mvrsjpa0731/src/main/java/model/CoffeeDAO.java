package model;
import java.util.*;

public interface CoffeeDAO {
   List<Coffee> findAll();
   Optional<Coffee> findByName(String name);
   Coffee save(Coffee cof);
   Optional<Coffee> update(String name,Coffee cof);
   Coffee deleteById(String name);   
}
