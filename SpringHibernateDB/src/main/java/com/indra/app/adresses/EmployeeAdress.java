package com.indra.app.adresses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author arommartinez
 *
 */

@Entity
@Table
public class EmployeeAdress {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 @Column
	 private int idemployee;
	 
	 @NotEmpty
	 @Column
	 private String adress;
	 
	 @NotEmpty
	 @Column
	 private String city;
	 
	 @NotEmpty
	 @Column
	 private String postalcode;
	 
	 @NotEmpty
	 @Column
	 private String country;
	 
	 public int getIDemployee() {
		 return idemployee;
	 }
	 
	 public void setIDemployee(int idemployee) {
		 this.idemployee = idemployee;
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
	 
	 public void setCity(String city) {
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
