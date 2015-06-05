package com.indra.app.customer;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.indra.app.adresses.CorporationAdress;
import com.indra.app.adresses.CorporationAdressService;
import com.indra.app.adresses.EmployeeAdress;
import com.indra.app.adresses.EmployeeAdressService;
import com.indra.app.employee.Employee;
import com.indra.app.employee.EmployeeLogin;
import com.indra.app.employee.EmployeeService;
import com.indra.app.employee.EmployeeUpdate;
import com.indra.app.search.SearchFields;

/**
 * 
 * @author arommartinez
 *
 */

@Controller
public class CustomerController {

 public static final int NUMREGIST = 12;
 Employee currentemployee;
 EmployeeAdress currentemployeeadress;
 CorporationAdress currentcorporationadress;
 SearchFields currentsfield;

 MessageDigest md;
 byte[] bytesOfPass;
 int nposition, initialpage, nbutton;

 
 @Autowired
 protected CustomerService custservice;
 @Autowired
 protected EmployeeService empservice;
 @Autowired
 protected CorporationAdressService corpservice;
 @Autowired
 protected EmployeeAdressService empadservice;
 

 @RequestMapping(value = {"/*", "/employees"}, method = RequestMethod.GET)
 public String getEmployees(Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
 model.addAttribute("employeelog", new EmployeeLogin());
 return "employees";
 }
 
 @RequestMapping(value = "employees", method = RequestMethod.POST)
 public String getEmployeePost(@ModelAttribute("employeelog") EmployeeLogin employeelog, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	List<Employee> EmployeeLog = empservice.getEmployeesLogin(employeelog.getUser());
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
 model.addAttribute("stateAE","active");
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
	 model.addAttribute("stateH","active");
	 model.addAttribute("currentemployee",currentemployee);
	 List<EmployeeAdress> employeeadress = empadservice.getEmplyoyeeAdressbyID(currentemployee.getID());
	 if(employeeadress.size()==1){
	 currentemployeeadress = employeeadress.get(0);
	 model.addAttribute("employeeadress", currentemployeeadress);
	 }
	 else{
		 currentemployeeadress=null;
		 model.addAttribute("employeeadress", currentemployeeadress);
	 }
	 List<CorporationAdress> corporationadress = corpservice.getCorporationAdressbyCorporation(currentemployee.getCorporation());
	 if(corporationadress.size()==1)
	 currentcorporationadress = corporationadress.get(0);
	 model.addAttribute("corporationadress", currentcorporationadress);
	 return "personalpage";
 }
 
 @RequestMapping(value = "personalpage-edit")
 public String addAdressGet(Model model) {
 model.addAttribute("stateH","active");
 model.addAttribute("employeeadressGET", new EmployeeAdress());
 model.addAttribute("employeeadress", currentemployeeadress);
 model.addAttribute("corporationadress", currentcorporationadress);
 model.addAttribute("currentemployee", currentemployee);
 return "personalpage-edit";
 }
 
 
 @RequestMapping(value = "personalpage-edit", method = RequestMethod.POST)
 public String addAdressPost(@ModelAttribute("employeeadressGET") @Valid EmployeeAdress employeeadress, 
		  BindingResult bindingresult, Model model) {
	 if(bindingresult.hasErrors()){
		 return "personalpage-edit";
	 }
	 employeeadress.setIDemployee(currentemployee.getID());
	 empadservice.updateEmployeeAdress(employeeadress);	 
	 currentemployeeadress = employeeadress;
	 return "redirect:personalpage";
 }
 
 @RequestMapping(value = "personalpage-editinfo")
 public String addInfoGet(Model model) {
 model.addAttribute("stateH","active");
 model.addAttribute("employeeGET", new EmployeeUpdate());
 model.addAttribute("employeeadress", currentemployeeadress);
 model.addAttribute("corporationadress", currentcorporationadress);
 model.addAttribute("currentemployee", currentemployee);
 return "personalpage-editinfo";
 }
 
 
 @RequestMapping(value = "personalpage-editinfo", method = RequestMethod.POST)
 public String addInfoPost(@ModelAttribute("employeeGET") @Valid EmployeeUpdate employeeupdate, BindingResult bindingresult, Model model) {
	 if(bindingresult.hasErrors()){
		 return "personalpage-editinfo";
	 }
	 currentemployee.setName(employeeupdate.getName());
	 currentemployee.setSurname(employeeupdate.getSurname());
	 currentemployee.setUser(employeeupdate.getUser());
	 currentemployee.setPhone(employeeupdate.getPhone());
	 empservice.updateEmployee(currentemployee);
	 return "redirect:personalpage";
 }
 
 @RequestMapping(value = "/customers", method = RequestMethod.GET)
 public String getCustomers(@RequestParam(value="page") int nposition, Model model) {
 model.addAttribute("stateC","active");
 model.addAttribute("currentemployee", currentemployee);
 List<Customer> customers = custservice.getCustomersbyID(currentemployee.getID());
 Pagination(model, customers, nposition);
 return "customers";
 }
 
 @RequestMapping(value = "search", method = RequestMethod.GET)
 public String getSearch(Model model) {
	 model.addAttribute("stateS","active");
	 model.addAttribute("currentemployee", currentemployee);
	 model.addAttribute("Searchfields", new SearchFields());
	 return "search";
 }
 
 @RequestMapping(value = "search", method = RequestMethod.POST)
 public String doSearch(@ModelAttribute("Searchfields") SearchFields sfields, Model model) {
 model.addAttribute("currentemployee", currentemployee);
 if(sfields!=null){
	 if(sfields.getByname()!=null && sfields.getByage()==0){
		 List<Customer> searching = custservice.getCustomersbyName(currentemployee.getID(), sfields.getByname());
		 if(searching.size()!=0)
			 model.addAttribute("searching", searching);
	 }
	 else if(sfields.getByage()!=0 && sfields.getByname()==null) {
		 List<Customer> searching = custservice.getCustomersbyAge(currentemployee.getID(), sfields.getByage());
		 if(searching.size()!=0)
			 model.addAttribute("searching", searching);
	 }
	 else {
		 List<Customer> searching = custservice.getCustomersbyNameAge(currentemployee.getID(), sfields.getByname(), sfields.getByage());
		 if(searching.size()!=0)
			 model.addAttribute("searching", searching);
	 }
	 }
 return "search";
 }
 
 @RequestMapping(value = "add-customer")
 public String addCustomerGet(Model model) {
 model.addAttribute("stateAC","active");
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
 
 public void Pagination(Model model, List<Customer> customers, int nposition){
	 int n = customers.size();
	 int npages = (n/CustomerController.NUMREGIST)+1;
	 int ncustomers;
	 if(customers.size()==0)
		 ncustomers=0;
	 else
		 ncustomers = customers.size();
	 model.addAttribute("firstpage", nposition);
	 model.addAttribute("npages", npages);
	 model.addAttribute("ncustomers",ncustomers);
	 if(customers.size()!=0){
	 List<Customer> listing = custservice.getCustomersbyLimit(currentemployee.getID(), CustomerController.NUMREGIST*(nposition-1), CustomerController.NUMREGIST);
	 model.addAttribute("listing",listing);
	 }
 }
 
 
 @InitBinder
 public void binder(WebDataBinder binder) {
	 binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		    public void setAsText(String value) {
		            try {
						setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
					} catch (ParseException e) {
						e.printStackTrace();
						setValue(null);
					}
		    
		    }

		    public String getAsText() {
		    	if (null!=getValue())
		    		return new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
		    	else
		    		return "";
		    }        

		});
 }
}