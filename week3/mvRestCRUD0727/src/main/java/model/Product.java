package model;

public class Product {
   String pid;
   String name;
   double price;
   public Product() {	
   }
   public Product(String pid, String name, double price) {
	
	this.pid = pid;
	this.name = name;
	this.price = price;
   }
   public String getPid() {
	return pid;
   }
   public void setPid(String pid) {
	this.pid = pid;
   }
   public String getName() {
	return name;
   }
   public void setName(String name) {
	this.name = name;
   }
   public double getPrice() {
	return price;
   }
   public void setPrice(double price) {
	this.price = price;
   }
   @Override
   public String toString() {
	return "Product [pid=" + pid + ", name=" + name + ", price=" + price + "]";
   }
   
   
}
