package model;

import java.math.BigDecimal;

public class Product {
    private int id;              // 商品編號 (自動生成)
    private String name;         // 商品名稱
    private BigDecimal price;    // 商品價格 (精確十進位)
    private int stock;           // 庫存數量
    private String category;     // 商品類別
	public Product() {		
	}
	
	public Product(String name, BigDecimal price, int stock, String category) {		
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", category="
				+ category + "]";
	}
	
    
    
}
