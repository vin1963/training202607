package model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 資料庫自動遞增
	private Integer id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "email", nullable = false, unique = true, length = 150)
	private String email;

	@Column(name = "department", nullable = false, length = 50)
	private String department;

	@Column(name = "salary")
	private Double salary;

	@Column(name = "hire_date")
	@JsonFormat(pattern = "yyyy-MM-dd") // Jackson 日期格式
	private LocalDate hireDate;

	@Column(name = "created_at", updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	// @PrePersist：INSERT 前自動填入時間戳
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	// @PreUpdate：UPDATE 前自動更新時間戳
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
    public void setId(Integer id) {
		this.id = id;
	}

	public Employee() {}
	public Employee(String name, String email, String department, Double salary) {
		
		this.name = name;
		this.email = email;
		this.department = department;
		this.salary = salary;
	}
	
}