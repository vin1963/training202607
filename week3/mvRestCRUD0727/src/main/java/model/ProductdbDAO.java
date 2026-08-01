package model;

import java.sql.*;
import java.util.*;

public class ProductdbDAO {
	List<Product> data = new ArrayList<>();

	Connection create() {
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "1234";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception ex) {
			System.out.println("Connection error " + ex.getMessage());
			return null;
		}
	}

	public List<Product> getAll() {
		Connection cn = create();
		String sql = "select * from demo.products";
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("pid");
				String name = rs.getString("name");
				double p = rs.getDouble("price");
				Product pt1 = new Product(id, name, p);
				data.add(pt1);
			}
			cn.close();
			return data;
		} catch (Exception ex) {
			if (cn != null)
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("getAll error " + ex.getMessage());
			return new ArrayList<Product>();
		}
	}

	public Optional<Product> find(String id) {
		Connection cn = create();
		String sql = "select * from demo.products where pid=?";
		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1,id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String pid = rs.getString("pid");
				String name = rs.getString("name");
				double p = rs.getDouble("price");
				Product pt1 = new Product(pid, name, p);				
				return  Optional.of(pt1);
			}
			cn.close();
			return Optional.empty();
			
		} catch (Exception ex) {
			if (cn != null)
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("find(id) error " + ex.getMessage());
			return Optional.empty();
		}
	}

	public Optional<Product> findByName(String name) {
		
		return null;
	}

	public boolean addProduct(Product p) {
		Connection cn = create();
		String sql = "insert into demo.products(pid,name,price)values(?,?,?);";
		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1,p.getPid());
			st.setString(2, p.getName());
			st.setDouble(3, p.getPrice());
			int rs = st.executeUpdate();
			if (rs>0) {
				cn.close();
				return true;
			}
			cn.close();
			return false;
			
		} catch (Exception ex) {
			if (cn != null)
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("addProduct error " + ex.getMessage());
			return false;
		}
		
	}

	public Product updateById(String id, Product updateObj) {
		Connection cn = create();
		String sql = "update demo.products set name=? , price=? where pid=? ";
		try {
			updateObj.setPid(id);
			PreparedStatement st = cn.prepareStatement(sql);			
			st.setString(1, updateObj.getName());
			st.setDouble(2, updateObj.getPrice());
			st.setString(3, id);
			int rs = st.executeUpdate();
			if (rs>0) {
				cn.close();
				return updateObj;
			}
			cn.close();
			return new Product();
			
		} catch (Exception ex) {
			if (cn != null)
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("updateProduct error " + ex.getMessage());
			return new Product();
		}

	}

	public Product deleteById(String id) {
		Connection cn = create();
		String sql = "delete from demo.products where pid=? ";
		try {
			Optional<Product> found=find(id);
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1,id);			
			int rs = st.executeUpdate();
			if (rs>0) {
				cn.close();
				return found.get();
			}
			cn.close();
			return found.orElse(new Product());
			
		} catch (Exception ex) {
			if (cn != null)
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("deleteById error " + ex.getMessage());
			return new Product();
		}
		

	}
}