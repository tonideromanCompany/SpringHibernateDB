package com.indra.app.employee;

import org.hibernate.validator.constraints.NotEmpty;

import com.indra.app.validator.Phone;

public class EmployeeUpdate {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String surname;
	
	@NotEmpty
	private String user;
	
	@Phone
	private String phone;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
