package com.indra.rest.customer;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


import com.indra.rest.validator.Phone;

/**
 * 
 * @author arommartinez
 *
 */

@Entity
@Table
public class Customer {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 
 @Column
 private int idemployee;
 
 @Column
 @NotEmpty
 @Size(min=2, max=30)
 private String name;
 
 @Column
 @Email @NotEmpty
 private String email;
 
 @Column
 @NotNull @Min(18) @Max(100)
 private Integer age;
 
 @Column
 @NotNull
 private Gender gender;
 
 @Column
 private Date birthday;
 
 @Column
 @NotEmpty
 @Phone
 private String phone;
 
 private Timestamp currentdate;
 
 public enum Gender {
	 MALE, FEMALE
 }
 
 public int getId() {
 return id;
 }
 
 public void setId(int id) {
 this.id = id;
 }
 
 public int getIDEmployee() {
	 return idemployee;
 }
 
 public void setIDEmployee(int idemployee) {
	 this.idemployee = idemployee;
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
 
 public Integer getAge() {
	 return age;
 }
 
 public void setAge(Integer age){
	 this.age = age;
 }
 
 public Gender getGender() {
	 return gender;
 }

 public void setGender(Gender gender){
	 this.gender = gender;
 }
 
 public Date getBirthday() {
	 return birthday;
 }
 
 public void setBirthday(Date birthday){
	 this.birthday = birthday;
 }
 
 public String getPhone() {
	 return phone;
 }
 
 public void setPhone(String phone){
	 this.phone = phone;
 }
 
 public Timestamp getCurrentdate() {
	 return currentdate;
 }
 
 public void setCurrentdate(Timestamp currentdate) {
	 this.currentdate = currentdate;
 }
 
}