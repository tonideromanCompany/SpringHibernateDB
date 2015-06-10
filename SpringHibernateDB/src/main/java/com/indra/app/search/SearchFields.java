package com.indra.app.search;

import java.sql.Timestamp;
import java.util.Date;

//import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author arommartinez
 *
 */

public class SearchFields {

	private String byname;
	
	private int byagehigh;
	
	private int byagelow;
	
	private Date bydatelow;
	
	private Date bydatehigh;
	
	public String getByname() {
		return byname;
	}
	
	public void setByname(String byname) {
		this.byname = byname;
	}
	
	public int getByagehigh() {
		return byagehigh;
	}
	
	public void setByagehigh(int byagehigh) {
		this.byagehigh = byagehigh;
	}
	
	public int getByagelow() {
		return byagelow;
	}
	
	public void setByagelow(int byagelow) {
		this.byagelow = byagelow;
	}
	
	public Date getBydatelow() {
		return bydatelow;
	}
	
	public void setBydatelow(Date bydatelow) {
		this.bydatelow = bydatelow;
	}
	
	public Date getBydatehigh() {
		return bydatehigh;
	}
	
	public void setBydatehigh(Date bydatehigh) {
		this.bydatehigh = bydatehigh;
	}

}
