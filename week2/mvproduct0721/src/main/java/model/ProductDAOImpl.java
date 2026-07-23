package model;

import java.util.*;
import java.util.Optional;
import java.math.BigDecimal;
import java.sql.*;
public class ProductDAOImpl implements ProductDAO {

	@Override
	public void insert(Product product) {
		String sql = "INSERT INTO mydb.products (name, price, stock, category) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		    ps.setString(1, product.getName());
		    ps.setDouble(2, Double.parseDouble(product.getPrice().toString()));
		    ps.setInt(3, product.getStock());
		    ps.setString(4, product.getCategory());
		    ps.executeUpdate();
		    try (ResultSet rs = ps.getGeneratedKeys()) {
		        if (rs.next()) {
		            product.setId(rs.getInt(1));   // 將自增 ID 回寫到物件
		        }
		    }
		    System.out.println("insert product :"+product);
		}catch(Exception ex) {
			System.out.println("Insert Product Error "+ex.getMessage());
		}			
	}	
	   @Override
	    public Optional<Product> findById(int id) {
	        String sql = "SELECT * FROM mydb.products WHERE id = ? AND deleted = 0";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return Optional.of(mapRow(rs));
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("查詢商品失敗, id=" + id, e);
	        }
	        return Optional.empty();
	    }
	private Product mapRow(ResultSet rs) throws SQLException {
	    Product product = new Product();
	    product.setId(rs.getInt("id"));
	    product.setName(rs.getString("name"));
	    product.setPrice(new BigDecimal(rs.getDouble("price")));
	    product.setStock(rs.getInt("stock"));
	    product.setCategory(rs.getString("category"));
	    return product;
	}
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM mydb.products WHERE deleted = 0";
		List<Product> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
			  Statement st = conn.createStatement()) {
			  ResultSet rs=st.executeQuery(sql); 
			  while (rs.next()) {
				    list.add(mapRow(rs));
			  } 
			  return list;  
			}catch(Exception ex) {
				System.out.println("findAll Products Error "+ex.getMessage());
			}		
		return new ArrayList();
	}

	@Override
	public List<Product> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		String sql = "UPDATE mydb.products SET name = ?, price = ?, stock = ?, category = ? WHERE id = ? AND deleted = 0";
		try (Connection conn = DBUtil.getConnection();
			     PreparedStatement ps = conn.prepareStatement(sql)) {
			    ps.setString(1, product.getName());
			    ps.setDouble(2, Double.parseDouble(product.getPrice().toString()));
			    ps.setInt(3, product.getStock());
			    ps.setString(4, product.getCategory());
			    ps.setInt(5, product.getId());
			    ps.executeUpdate();			   
			    System.out.println("updated product :"+product);
			}catch(Exception ex) {
				System.out.println("update Product Error "+ex.getMessage());
			}			
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE mydb.products SET deleted = 1 WHERE id = ?";
		try (Connection conn = DBUtil.getConnection();
			     PreparedStatement ps = conn.prepareStatement(sql)) {
			   ps.setInt(1, id);
			   ps.executeUpdate();
			   System.out.println("delete product id :"+id);
			}catch(Exception ex) {
				System.out.println("delete Product Error "+ex.getMessage());
			}			
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
