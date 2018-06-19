package es.symbioserver.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "User")

public class UserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Name cannot be null")
	@Size (min=2, max=50, message="Name 50 characters")
	private String name;
	
	@NotNull(message = "Password cannot be null")
	@Size (min=2, max=50, message="Password 50 characters")
	private String password;
	
	@NotNull(message = "Email cannot be null")
	@Size (min=2, max=50, message="Email 50 characters")
	@Email(message="Email format is wrong")
	private String email;
	
	@NotNull(message = "Role cannot be null")
	@Size (min=2, max=50, message="Role 50 characters")
	private String role;
	
	

	public UserBean(){
		id = 0;
		name = "";
		password = "";
		email = "";
		role = "";
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	
	
}
