package com.indra.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.rest.adresses.*;
import com.indra.rest.customer.Customer;
import com.indra.rest.customer.CustomerService;
import com.indra.rest.customer.ListResults;
import com.indra.rest.employee.*;

/**
 * 
 * @author arommartinez
 *
 */
@RestController
public class CorporationAddressController {
	
	@Autowired
	protected CorporationAdressService corpservice;
	@Autowired
	protected EmployeeService empservice;
	@Autowired
	protected CustomerService custservice;
	
	//GET of corporation
	@RequestMapping( value = "/corporation", method = RequestMethod.GET)
	public ListResultsCorporation CorporationGET(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="nregisters", defaultValue="15") int nregisters) {
		List<CorporationAdress> corpad = corpservice.getCorporationAdressesbyLimit(nregisters*(page-1), nregisters);
		int ncorporations = corpservice.getCorporationAdresses().size();
		return new ListResultsCorporation(corpad,ncorporations);
	}
	//POST of corporation
	@RequestMapping( value = "/corporation", method = RequestMethod.POST)
	public ResponseEntity<?> CorporationPOST(CorporationAdress corporationaddress) {
		corpservice.createCorporationAdress(corporationaddress);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	//GET of corporation/id
	@RequestMapping(value = "/corporation/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> IdGET(@PathVariable int id) {
		try{
			return new ResponseEntity<>(corpservice.getCorporationAdressesID(id),HttpStatus.OK);
		}catch(Exception E) {
			String errorMessage = "404 NOT FOUND ==> Any corporation with this id";
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		}
	}
	//UPDATE of corporation/id
	@RequestMapping(value = "/corporation/{id}", method = RequestMethod.PUT)
	public void IdUPDATE(CorporationAdress corporationaddress) {
		corpservice.updateCorporationAdress(corporationaddress);
	}
	//DELETE of corporation/id
	@RequestMapping(value = "/coporation/{id}", method = RequestMethod.DELETE)
	public void IdDELETE(CorporationAdress corporationaddress) {
		corpservice.deleteCorporationAdress(corporationaddress);
	}
	//GET of corporation/id/employee
	@RequestMapping(value = "/corporation/{id}/employee", method = RequestMethod.GET)
	public ResponseEntity<?> CorporationEmployeeGET(@PathVariable int id, @RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="nregisters", defaultValue="15") int nregisters){
		try{
			String corporation = corpservice.getCorporationAdressesID(id).get(0).getCorporation();
			int nemployees = empservice.getEmployeesbyCorporation(corporation).size();
			List<Employee> employeebycorporation = empservice.getEmployeesbyCorporationbyLimit(corporation,nregisters*(page-1), nregisters);
			ListResultsEmployee listresults = new ListResultsEmployee(employeebycorporation,nemployees);
			return new ResponseEntity<>(listresults,HttpStatus.OK);
		}catch(Exception ex){
			String errorMessage = "404 NOT FOUND ==> Any corporation with this id";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	//POST of corporation/id/employee
	@RequestMapping(value = "/corporation/{id}/employee", method = RequestMethod.POST)
	public ResponseEntity<?> CorporationEmployeePOST(@PathVariable int id, Employee employee) {
		try{
			String corporation = corpservice.getCorporationAdressesID(id).get(0).getCorporation();
			employee.setCorporation(corporation);
			empservice.updateEmployee(employee);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(Exception ex) {
			String errorMessage = ("404 NOT FOUND ==> Impossible to create the relationship between this employee with this corporation, may be the corporation or employee doesn't exists");
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	/*UPDATE of corporation/id/employee
	 * Action for the list
	 */
	
	/*DELETE of corporation/id/employee
	 * Action for the list
	 */
	//GET of corporation/id/employee/ide
	@RequestMapping(value = "/corporation/{id}/employee/{ide}", method = RequestMethod.GET)
	public ResponseEntity<?> EmployeeIdGET(@PathVariable int id, @PathVariable int ide) {
		try{
			String corporation = corpservice.getCorporationAdressesID(id).get(0).getCorporation();
			Employee employee = empservice.getEmployeesbyCorporationbyID(corporation, ide).get(0);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		}catch(Exception ex) {
			String errorMessage = "404 NOT FOUND ==> Any employee with this id in this corporation";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	//UPDATE of corporation/id/employee/ide
	@RequestMapping(value = "/corporation/{id}/employee/{ide}", method = RequestMethod.PUT)
	public void EmployeeIdUPDATE(@PathVariable int id, Employee employee) {
		String corporation = corpservice.getCorporationAdressesID(id).get(0).getCorporation();
		employee.setCorporation(corporation);
		empservice.updateEmployee(employee);
	}
	//DELETE of corporation/id/employee/ide
	@RequestMapping(value = "/corporation/{id}/employee/{ide}", method = RequestMethod.DELETE)
	public void EmployeeIdDELETE(Employee employee) {
		empservice.deleteEmployee(employee);
	}
	//GET of corporation/{id}/customer
	@RequestMapping(value = "/corporation/{id}/customer", method = RequestMethod.GET)
	public ResponseEntity<?> CorporationCustomerGET(@PathVariable int id) {
		try {
			return new ResponseEntity<>(CorporationCustomerGet(id), HttpStatus.OK);
		}catch(Exception ex) {
			String errorMessage = ("404 NOT FOUND ==> Any corporation with this id");
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	//GET of corporation/id/customer/idc
	@RequestMapping(value = "/corporation/{id}/customer/{idc}", method = RequestMethod.GET)
	public ResponseEntity<?> CustomerIdGET(@PathVariable int id, @PathVariable int idc) {
		try {
			Customer custresult = new Customer();
			ListResults listresults = CorporationCustomerGet(id);
			List<Customer> customers = listresults.getResults();
			for(int i=0; i<customers.size();i++){
				if(customers.get(i).getId()==idc)
					custresult = customers.get(i);
			}
			return new ResponseEntity<>(custresult,HttpStatus.OK);
		}catch(Exception ex) {
			String errorMessage = "404 NOT FOUND ==> Any corporation with this id";
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	//////////////////////////ANOTHER FUNCTIONS///////////////////////////////////////
	public ListResults CorporationCustomerGet(int id) {
		ArrayList<Customer> arr = new ArrayList<>();
		String corporation = corpservice.getCorporationAdressesID(id).get(0).getCorporation();
		List<Employee> employeebycorporation = empservice.getEmployeesbyCorporation(corporation);
		for(int i=0; i<employeebycorporation.size(); i++) {
			List<Customer> customers = custservice.getCustomersbyID(employeebycorporation.get(i).getID());
			for(int j=0; j<customers.size();j++) {
				arr.add(customers.get(j));
			}
		}
		return new ListResults(arr,arr.size());	
	}
}