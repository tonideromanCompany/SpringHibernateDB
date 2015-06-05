package com.indra.app.search;

//import org.hibernate.validator.constraints.NotEmpty;

public class SearchFields {

	private String byname;
	private int byage;
	
	public String getByname() {
		return byname;
	}
	
	public void setByname(String byname) {
		this.byname = byname;
	}
	
	public int getByage() {
		return byage;
	}
	
	public void setByage(int byage) {
		this.byage = byage;
	}

}
