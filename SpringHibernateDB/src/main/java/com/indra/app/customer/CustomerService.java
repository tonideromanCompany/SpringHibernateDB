package com.indra.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * @author arommartinez
 *
 */

@Service
public class CustomerService {
 
 @Autowired
 protected CustomerRepository repository;
 
 public List<Customer> getCustomers() {
 return repository.getCustomers();
 }
 
 public List<Customer> getCustomersbyID(int IDemployee) {
	 return repository.getCustomersbyID(IDemployee);
	 }
 
 public List<Customer> getCustomersbyIDcustomer(int IDcustomer) {
	 return repository.getCustomersbyIDcustomer(IDcustomer);
	 }
 
 public List<Customer> getCustomersbyLimit(int IDemployee, int first, int last) {
	 return repository.getCustomersbyLimit(IDemployee, first, last);
	 }
 
 public List<Customer> getCustomersbyNameAge(int IDemployee, String byname, int byagehigh, int byagelow) {
	 return repository.getCustomersbyNameAge(IDemployee, byname, byagehigh, byagelow);
	 }
 
 public List<Customer> getCustomersbyName(int IDemployee, String byname) {
	 return repository.getCustomersbyName(IDemployee, byname);
	 }
 
 public List<Customer> getCustomersbyAge(int IDemployee, int byagehigh, int byagelow) {
	 return repository.getCustomersbyAge(IDemployee, byagehigh, byagelow);
	 }
 
 public List<Customer> getCustomersbyDate(int IDemployee, Timestamp bydatehigh, Timestamp bydatelow) {
	 return repository.getCustomersbyDate(IDemployee, bydatehigh, bydatelow);
	 }
 
 public List<Customer> getCustomersbyNameAgeLimit(int IDemployee, String byname, int byagehigh, int byagelow, int first, int last) {
	 return repository.getCustomersbyNameAgeLimit(IDemployee, byname, byagehigh, byagelow, first, last);
	 }
 
 public List<Customer> getCustomersbyNameLimit(int IDemployee, String byname, int first, int last) {
	 return repository.getCustomersbyNameLimit(IDemployee, byname, first, last);
	 }
 
 public List<Customer> getCustomersbyAgeLimit(int IDemployee, int byagehigh, int byagelow, int first, int last) {
	 return repository.getCustomersbyAgeLimit(IDemployee, byagehigh, byagelow, first, last);
	 }
 
 public List<Customer> getCustomersbyDateLimit(int IDemployee, Timestamp bydatehigh, Timestamp bydatelow, int first, int last) {
	 return repository.getCustomersbyDateLimit(IDemployee, bydatehigh, bydatelow, first, last);
	 }
 
 public void deleteCustomer(Customer customer) {
	 repository.deleteCustomer(customer);
 }
 
 public void createCustomer(Customer customer) {
 repository.createCustomer(customer);
 }
}