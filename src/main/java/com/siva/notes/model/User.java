package com.siva.notes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "user")
public class User extends BaseModel {


	@Column
	@NotBlank(message = "Name is mandatory")
	@NotNull
	private String name;
	
	@Column(name = "username")
	@NotBlank(message = "User Name is mandatory")
	@NotNull
	private String userName;

	@Column
	@NotBlank(message = "Email is mandatory")
	@NotNull
	private String email;
	
	@Column
	@NotBlank(message = "Password is mandatory")
	@NotNull
	private String password;
	
	

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(BaseModel baseModel) {
		super(baseModel);
		// TODO Auto-generated constructor stub
	}

	public User(@NotBlank(message = "Name is mandatory") @NotNull String name,
			@NotBlank(message = "User Name is mandatory") @NotNull String userName,
			@NotBlank(message = "Email is mandatory") @NotNull String email,
			@NotBlank(message = "Password is mandatory") @NotNull String password) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
