package com.indra.rest.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.rest.customer.*;
import com.indra.rest.customer.Customer.Gender;
import com.indra.rest.employee.*;

/**
 * 
 * @author arommartinez
 *
 */

@RestController
public class CustomerController {
	
	@Autowired
	protected CustomerService custservice;
	
	@Autowired
	protected EmployeeService empservice;
	
	//GET of customer
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ListResults CustomerGET(@RequestParam(value="page", defaultValue="1") int page, 
	@RequestParam(value="nregisters", defaultValue="15") int nregisters) {
		int ncustomers = custservice.getCustomers().size();
		List<Customer> customersbylimit = custservice.getCustomersbyLimit(nregisters*(page-1), nregisters);
		return new ListResults(customersbylimit,ncustomers);
	}
	//POST of customer
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<?> CustomerPOST(String name, String email, String age, Gender gender, Date birthday, String phone) {
		Customer customer = new Customer();
		customer.setName(name); customer.setEmail(email); customer.setAge(Integer.parseInt(age));
		customer.setGender(gender); customer.setBirthday(birthday); customer.setPhone(phone);
		customer.setCurrentdate(CurrentDate());
		custservice.createCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	//GET of customer/id
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> IdGET(@PathVariable int id) {
		try {
			return new ResponseEntity<>(custservice.getCustomersbyIDcustomer(id).get(0), HttpStatus.OK);
		} catch(Exception ex) {
			String errorMessage ="404 NOT FOUND ==> Any customer with this id";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	//UPDATE of customer/id
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> IdUPDATE(@PathVariable("id") int id, String name, String email, String age, Gender gender, Date birthday, String phone) {
		try {
			System.out.println(name+email+age+gender+birthday+phone);
		Customer tmpcust = custservice.getCustomersbyIDcustomer(id).get(0);
		if(name!=null) tmpcust.setName(name); if(email!=null) tmpcust.setEmail(email);
		if(age!=null) tmpcust.setAge(Integer.parseInt(age)); if(gender!=null) tmpcust.setGender(gender);
		if(birthday!=null) tmpcust.setBirthday(birthday); if(phone!=null) tmpcust.setPhone(phone);
		tmpcust.setCurrentdate(CurrentDate());
		custservice.updateCustomer(tmpcust);
		return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception ex) {
			ex.printStackTrace();
			String errorMessage ="404 NOT FOUND ==> Can't delete it, any customer with this id";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	//DELETE of customer/id
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> IdDELETE(@PathVariable int id) {
		try {
			custservice.deleteCustomer(custservice.getCustomersbyIDcustomer(id).get(0));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception ex) {
			String errorMessage ="404 NOT FOUND ==> Can't delete it, any customer with this id";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	//GET of customer/id/employee
	@RequestMapping(value = "/customer/{id}/employee", method = RequestMethod.GET)
	public ResponseEntity<?> CustomerEmployee(@PathVariable int id) {
		try{
		int idemployee = custservice.getCustomersbyIDcustomer(id).get(0).getIDEmployee();
		return new ResponseEntity<>(empservice.getEmployeesbyId(idemployee), HttpStatus.OK);
		}catch(Exception ex) {
			String errorMessage = "404 NOT FOUND ==> Any customer with this id";
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		}
	}
	
	//NOT CONTROLLER FUNCTIONS	
	 public Timestamp CurrentDate() {
		 Date today = new Date();
		 return new Timestamp(today.getTime());
	 }
}
