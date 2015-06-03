package com.indra.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 



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
 
 public List<Customer> getCustomersbyLimit(int IDemployee, int first, int last) {
	 return repository.getCustomersbyLimit(IDemployee, first, last);
	 }
 
 public void createCustomer(Customer customer) {
 repository.createCustomer(customer);
 }
}