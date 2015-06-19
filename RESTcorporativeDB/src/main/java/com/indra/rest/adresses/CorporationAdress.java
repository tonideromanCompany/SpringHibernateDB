package com.indra.rest.adresses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author arommartinez
 *
 */

@Entity
@Table
public class CorporationAdress {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 
	 @Column
	 private String corporation;
	 
	 @Column
	 private String office;
	 
	 @Column
	 private String department;
	 
	 @Column
	 private String adress;
	 
	 @Column
	 private String city;
	 
	 @Column
	 private String postalcode;
	 
	 @Column
	 private String country;
	 
	 public String getCorporation() {
		 return corporation;
	 }
	 
	 public void setCorporation(String corporation) {
		 this.corporation = corporation;
	 }
	 
	 public String getOffice () {
		 return office;
	 }
	 
	 public void setOffice (String office) {
		 this.office = office;
	 }
	 
	 public String getDepartment() {
		 return department;
	 }
	 
	 public void setDepartment(String department) {
		 this.department = department;
	 }
	 
	 public String getAdress() {
		 return adress;
	 }
	 
	 public void setAdress(String adress) {
		 this.adress = adress;
	 }
	 
	 public String getCity() {
		 return city;
	 }
	 
	 public void setCity (String city) {
		 this.city = city;
	 }
	 
	 public String getPostalcode() {
		 return postalcode;
	 }
	 
	 public void setPostalcode(String postalcode) {
		 this.postalcode = postalcode;
	 }
	 
	 public String getCountry() {
		 return country;
	 }
	 
	 public void setCountry(String country) {
		 this.country = country;
	 }

}
