package com.indra.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import com.indra.app.employee.*;

/**
 * 
 * @author arommartinez
 *
 */

@Controller
public class CustomerController {

 Employee currentemployee;

 MessageDigest md;
 byte[] bytesOfPass;

 
 @Autowired
 protected CustomerService custservice;
 @Autowired
 protected EmployeeService empservice;
 

 @RequestMapping(value = {"/*", "/employees"}, method = RequestMethod.GET)
 public String getEmployees(Model model) {
 model.addAttribute("employeelog", new EmployeeLogin());
 return "employees";
 }
 
 @RequestMapping(value = "employees", method = RequestMethod.POST)
 public String getEmployeePost(@ModelAttribute("employeelog") EmployeeLogin employeelog, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	List<Employee> EmployeeLog = empservice.getEmployeesLogin(employeelog.getCorporation(),employeelog.getUser());
	if(EmployeeLog.size()==1)
		currentemployee=EmployeeLog.get(0);
	else
		return "employees";
	String savedfailpass = employeelog.getPassword();
	employeelog.setPassword(ReturnedHash(employeelog.getPassword()));
	if(employeelog.getPassword().equals(currentemployee.getPassword()))
		return "redirect:personalpage";
	else {
		employeelog.setPassword(savedfailpass);
		return "employees";
	}
 }
 
 @RequestMapping(value = "add-employee")
 public String addEmployeeGet(Model model) {
 model.addAttribute("employee", new Employee());
 return "add-employee";
 }
 
 
 @RequestMapping(value = "add-employee", method = RequestMethod.POST)
 public String addEmployeePost(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingresult, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	 if(bindingresult.hasErrors()){
		 return "add-employee";
	 }
	 
	 if(employee.getPassword().equals(employee.getPassword2())){
		 employee.setPassword(ReturnedHash(employee.getPassword()));
		 empservice.createEmployee(employee);
		 return "redirect:employees";
	 }
	 else {
		 model.addAttribute("fail","Passwords must be equals");
		 return "add-employee";
	 }
 }
 
 @RequestMapping(value="/personalpage")
 public String getPersonalInfo(Model model) {
	 model.addAttribute("currentemployeeC",currentemployee.getCorporation());
	 model.addAttribute("currentemployeeN",currentemployee.getName());
	 model.addAttribute("currentemployeeS",currentemployee.getSurname());
	 model.addAttribute("currentemployeeE",currentemployee.getUser());
	 model.addAttribute("currentemployeeR",currentemployee.getRole());
	 model.addAttribute("currentemployeeP",currentemployee.getPhone());
	 return "personalpage";
 }
 
 @RequestMapping(value = "/customers", method = RequestMethod.GET)
 public String getCustomers(Model model) {
 model.addAttribute("EmployeeName", currentemployee.getName());
 model.addAttribute("EmployeeRole", currentemployee.getRole());
 model.addAttribute("EmployeeCorporation", currentemployee.getCorporation());
 List<Customer> customers = custservice.getCustomersbyID(currentemployee.getID());
 model.addAttribute("customers", customers);
 return "customers";
 }
 
 @RequestMapping(value = "add-customer")
 public String addCustomerGet(Model model) {
 model.addAttribute("customer", new Customer());
 return "add-customer";
 }
 
 @RequestMapping(value = "add-customer", method = RequestMethod.POST)
 public String addCustomerPost(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {
	 if(bindingResult.hasErrors()){
		 return "add-customer";
	 }
	 customer.setIDEmployee(currentemployee.getID());
	 custservice.createCustomer(customer);
	 return "redirect:personalpage";
 }
 
 //NOT CONTROLLERS FUNCTIONS
 
 //Hash a string and return the hashed string
 public String ReturnedHash(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	 md = MessageDigest.getInstance("MD5");
	 bytesOfPass = pass.getBytes("UTF-8");
	 md.reset();
	 md.update(bytesOfPass);
	 byte[] thedigest = md.digest();
	 BigInteger bigInt = new BigInteger(1,thedigest);
	 String hashtext = bigInt.toString(16);
	 while(hashtext.length() < 32){
		 hashtext = "0"+hashtext;
	 }
	 return hashtext;
 }
}