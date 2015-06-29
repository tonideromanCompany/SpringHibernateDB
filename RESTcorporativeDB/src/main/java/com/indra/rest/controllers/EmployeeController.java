package com.indra.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.rest.adresses.EmployeeAdress;
import com.indra.rest.adresses.EmployeeAdressService;
import com.indra.rest.adresses.ListAddress;
import com.indra.rest.customer.*;
import com.indra.rest.employee.*;

/**
 * 
 * @author arommartinez
 *
 */

@RestController
public class EmployeeController {
	
	@Autowired
	protected EmployeeService empservice;
	@Autowired
	protected CustomerService custservice;
	@Autowired
	protected EmployeeAdressService empadservice;
	
	//GET of employee
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ListResultsEmployee EmployeeGET(@RequestParam(value="page", defaultValue="1") int page, 
	@RequestParam(value="nregisters", defaultValue="10") int nregisters) {
		int nemployees = empservice.getEmployees().size();
		List<Employee> employeesbylimit = empservice.getEmployeesbyLimit(nregisters*(page-1), nregisters);
		return new ListResultsEmployee(employeesbylimit,nemployees);
	}
	//POST of employee
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<?> EmployeePOST(Employee employee) {
			empservice.createEmployee(employee);
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	//GET of employee/address ==> Useless...
	@RequestMapping(value = "/employee/address")
	public ListAddress AddressGET() {
		List<EmployeeAdress> listresult = empadservice.getEmployeeAdress();
		return new ListAddress(listresult,listresult.size());
	}
	//POST of employee/address
	@RequestMapping(value = "/employee/address", method = RequestMethod.POST)
	public void AddressPOST(EmployeeAdress employeeadress) {
		empadservice.createEmployeeAdress(employeeadress);
	}
	//GET of employee/id
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> IdGET(@PathVariable int id) {
		try {
			return new ResponseEntity<>(empservice.getEmployeesbyId(id).get(0), HttpStatus.OK);
		} catch(Exception ex) {
			String errorMessage = "404 NOT FOUND ==> Any employee with this id";
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		}
	}
	//UPDATE of employee/id
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> IdUPDATE(Employee employee) {
		empservice.updateEmployee(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//DELETE of employee/id
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> IdDELETE(Employee employee) {
		empservice.deleteEmployee(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//GET of employee/id/address
	@RequestMapping(value = "/employee/{id}/address", method = RequestMethod.GET)
	public ResponseEntity<?> IdAddressGET(@PathVariable int id) {
		try {
			return new ResponseEntity<>(empadservice.getEmplyoyeeAdressbyID(id),HttpStatus.OK);
		}catch(Exception ex) {
			String errorMessage = "404 ==> Any employee with this id";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	//POST of employee/id/address
	@RequestMapping(value = "/employee/{id}/address", method = RequestMethod.POST)
	public ResponseEntity<?> IdAddressPOST(@PathVariable int id, EmployeeAdress employeeadress) {
		employeeadress.setIDemployee(id);
		empadservice.updateEmployeeAdress(employeeadress);
		return new ResponseEntity<>("Relation between employee & address created",HttpStatus.CREATED);
	}
	//UPDATE of employee/id/address
	@RequestMapping(value = "/employee/{id}/address", method = RequestMethod.PUT)
	public void IdAddresUPDATE(@PathVariable int id, EmployeeAdress employeeadress) {
		empadservice.updateEmployeeAdress(employeeadress);
	}
	//DELETE of employee/id/address
	@RequestMapping(value = "/employee/{id}/address", method = RequestMethod.DELETE)
	public void IdAddressDELETE(EmployeeAdress employeeadress){
		empadservice.deleteEmployeeAdress(employeeadress);
	}
	//GET of employee/id/customer
	@RequestMapping(value = "/employee/{id}/customer", method = RequestMethod.GET)
	public ListResults CustomerGET(@PathVariable int id, @RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="nregisters", defaultValue="10") int nregisters) {
		List<Customer> customersbyLimit = custservice.getCustomersbyLimitID(id, nregisters*(page-1), nregisters);
		int ncustomers = custservice.getCustomersbyID(id).size();
		return new ListResults(customersbyLimit, ncustomers);
	}
	//POST of employee/id/customer
	@RequestMapping(value = "/employee/{id}/customer", method = RequestMethod.POST)
	public ResponseEntity<?> CustomerPOST(@PathVariable int id, Customer customer) {
		customer.setIDEmployee(id);
		custservice.updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	//UPDATE of employee/id/customer --> Possible utility if we need to transfer all the customers of one employee to another employee
	@RequestMapping( value = "/employee/{id}/customer", method = RequestMethod.PUT)
	public ResponseEntity<?> CustomerUPDATE(@PathVariable int id, List<Customer> customers) {
		for(int i=0; i<customers.size(); i++){
			Customer customer = customers.get(i);
			customer.setIDEmployee(id);
			custservice.updateCustomer(customer);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//DELETE of employee/id/customer
	@RequestMapping( value = "/employee/{id}/customer", method = RequestMethod.DELETE)
	public ResponseEntity<?> CustomerDELETE(@PathVariable int id, Customer customer) {
		custservice.deleteCustomer(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//GET of employee/id/customer/idc
	@RequestMapping( value = "/employee/{id}/customer/{idc}", method = RequestMethod.GET)
	public ResponseEntity<?> IdCustomerGET(@PathVariable int id, @PathVariable int idc) {
		try {
			return new ResponseEntity<>(custservice.getCustomerbyIDandIDcustomer(id, idc), HttpStatus.OK);
		}catch(Exception ex) {
			String errorMessage = "404 NOT FOUND ==> Any customer with this id";
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	}
		}
	//UPDATE of employee/id/customer/idc
	@RequestMapping( value = "/employee/{id}/customer/{idc}", method = RequestMethod.PUT)
	public ResponseEntity<?> IdCustomerUPDATE(@PathVariable int id, Customer customer) {
		customer.setIDEmployee(id);
		custservice.updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//DELETE of employee/id/customer/idc
	@RequestMapping( value = "/employee/{id}/customer/{idc}", method = RequestMethod.DELETE)
	public ResponseEntity<?> IdCustomerDELETE(@PathVariable int id, @PathVariable int idc, Customer customer) {
		custservice.deleteCustomer(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
