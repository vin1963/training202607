package repository;

import java.util.List;
import java.util.Optional;

import config.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Book;

public class BookDAO implements Repository<Book, Long> {

	@Override
	public Book save(Book book) {
		EntityManager em = JpaUtil.createEntityManager(); // 1. 取得 EntityManager
		EntityTransaction tx = em.getTransaction(); // 2. 取得交易
		try {
			tx.begin(); // 3. 開始交易
			em.persist(book); // 4. 執行操作
			tx.commit(); // 5. 提交（真正寫入 DB）
			return book;
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback(); // 6. 失敗就復原
			throw e;
		} finally {
			em.close(); // 7. 一定要關閉，釋放資源
		}
	}

	@Override
	public Optional<Book> findById(Long id) {
		EntityManager em = JpaUtil.createEntityManager();
		try {
			return Optional.ofNullable(em.find(Book.class, id));
		} finally {
			em.close();
		}
	}

	@Override
	public List<Book> findAll() {

		EntityManager em = JpaUtil.createEntityManager();
		try {
			return em.createQuery("SELECT b FROM Book b ORDER BY b.id", Book.class).getResultList();
		} finally {
			em.close();
		}

	}

	@Override
	public Book update(Long id,Book book) {
	    EntityManager em = JpaUtil.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        Book found=em.find(Book.class, id);
	        if(found!=null) {
	        	   found.setAuthor(book.getAuthor());
	        	   found.setCategory(book.getCategory());
	        	   found.setStock(book.getStock());
	        	   found.setTitle(book.getTitle());
	        	   found.setIsbn(book.getIsbn());
	           Book merged = em.merge(found);   // ← 關鍵
	           tx.commit();
		       return merged;
	        }else {
	        	   return null;
	        }
	       
	    } catch (Exception e) {
	        if (tx.isActive()) tx.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = JpaUtil.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        Book book = em.find(Book.class, id);
	        if (book != null) em.remove(book);   // ← 先查出 managed 才能 remove
	        tx.commit();
	    } catch (Exception e) {
	        if (tx.isActive()) tx.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 // ==================== 進階查詢 ====================

    /** 依分類查詢（不分大小寫） */
    public List<Book> findByCategory(String category) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b FROM Book b WHERE LOWER(b.category) = LOWER(:cat) ORDER BY b.title",
                Book.class)
                .setParameter("cat", category)
                .getResultList();
        } finally {
            em.close();
        }
    }

    /** 依價格區間查詢 */
    public List<Book> findByPriceRange(Double min, Double max) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return em.createQuery(
                "SELECT b FROM Book b WHERE b.price BETWEEN :min AND :max ORDER BY b.price",
                Book.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        } finally {
            em.close();
        }
    }

    /** 分頁查詢（page 從 1 開始） */
    public List<Book> findAllPaged(int page, int size) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return em.createQuery("SELECT b FROM Book b ORDER BY b.id", Book.class)
                     .setFirstResult((page - 1) * size)
                     .setMaxResults(size)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    /** 取得總筆數 */
    public long count() {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(b) FROM Book b", Long.class)
                     .getSingleResult();
        } finally {
            em.close();
        }
    }

}
