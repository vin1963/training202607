package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf;

    static {                                        // 類別載入時只建立一次
        emf = Persistence.createEntityManagerFactory("mvsuppliercoffee0804");
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();           // 每次請求拿一個新 EntityManager
    }
}
