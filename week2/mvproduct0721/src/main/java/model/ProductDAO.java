package model;

import java.util.*;

public interface ProductDAO {
    void insert(Product product);                // C
    Optional<Product> findById(int id);          // R (單筆)
    List<Product> findAll();                     // R (多筆)
    List<Product> searchByName(String name);     // R (模糊)
    List<Product> findByCategory(String category); // R (類別)
    void update(Product product);                // U
    void delete(int id);                         // D (軟刪除)
    long count();                                // 總筆數
}
