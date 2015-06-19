package com.indra.rest.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.indra.rest.validator.Phone;

/**
 * 
 * @author arommartinez
 *
 */

@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	@NotEmpty
	@Size(min=2, max=30)
	private String corporation;
	
	@Column
	@NotEmpty
	@Size(min=2, max=30)
	private String user;
	
	@Column
	@NotEmpty
	@Size(min=2, max=30)
	private String name;
	
	@Column
	@NotEmpty
	@Size(min=2, max=30)
	private String surname;
	
	@Column
	@NotEmpty
	private String password;
	
	@Column
	@NotEmpty
	private String password2;
	
	@Column
	@NotEmpty
	@Phone
	private String phone;
	
	@Column
	@NotEmpty
	private String role;
	
	public int getID() {
		return id;
	}
	
	public String getCorporation() {
		return corporation;
	}
	
	public void setCorporation(String corporation){
		this.corporation = corporation;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user){
		this.user = user;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword2(){
		return password2;
	}
	
	public void setPassword2(String password2){
		this.password2 = password2;
	}
	
	 public String getPhone() {
		 return phone;
	 }
	 
	 public void setPhone(String phone){
		 this.phone = phone;
	 }
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role){
		this.role = role;
	}
}
