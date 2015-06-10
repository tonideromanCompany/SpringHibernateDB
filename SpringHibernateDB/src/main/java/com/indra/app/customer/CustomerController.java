package com.indra.app.customer;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 
 int currentpage;

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
 model.addAttribute("ArrayID", new DeleteCustomer());
 List<Customer> customers = custservice.getCustomersbyID(currentemployee.getID());
 Pagination(model, customers, nposition, 15);
 currentpage = nposition;
 return "customers";
 }
 
 @RequestMapping(value = "/delete", method = RequestMethod.POST)
 public String DeletePost(@ModelAttribute("ArrayID") DeleteCustomer deletecustomer, Model model) {
	 for(int j=0;j<deletecustomer.customers.length;j++) {
		List<Customer> customers = custservice.getCustomersbyIDcustomer(Integer.parseInt(deletecustomer.customers[j]));
		System.out.println("Customer with ID "+Integer.parseInt(deletecustomer.customers[j])+" selected");
		if(customers.size()!=0) {
			custservice.deleteCustomer(customers.get(0));
		}
		else
			System.out.println("ERROR. Impossible search of customer's ID \n");
	 }
	 return "redirect:customers?page="+currentpage;
 }
	
 
 @RequestMapping(value = "search", method = RequestMethod.GET)
 public String getSearch(Model model) {
	 model.addAttribute("stateS","active");
	 model.addAttribute("currentemployee", currentemployee);
	 model.addAttribute("Searchfields", new SearchFields());
	 return "search";
 }
 
 @RequestMapping(value = "search", method = RequestMethod.POST)
 public String doSearch(@RequestParam(value="page") int nposition, @ModelAttribute("Searchfields") SearchFields sfields, Model model) {
 model.addAttribute("stateS","active");
 model.addAttribute("currentemployee", currentemployee);
 if(sfields!=null){
	 if(sfields.getByname()!=null && sfields.getByagehigh()==0 && sfields.getByagelow()==0 && sfields.getBydatehigh()==null && sfields.getBydatelow()==null){
		 List<Customer> searching = custservice.getCustomersbyName(currentemployee.getID(), sfields.getByname());
		 PaginationSearch(model, searching, sfields, nposition, 10, 1);
	 }
	 else if(sfields.getByagehigh()!=0 && sfields.getByagelow()!=0 && sfields.getByname()==null && sfields.getBydatehigh()==null && sfields.getBydatelow()==null) {
		 List<Customer> searching = custservice.getCustomersbyAge(currentemployee.getID(), sfields.getByagehigh(), sfields.getByagelow());
		 PaginationSearch(model, searching, sfields, nposition, 10, 2);
	 }
	 else if(sfields.getBydatehigh()!=null && sfields.getBydatelow()!=null && sfields.getByname()==null && sfields.getByagehigh()==0 && sfields.getByagelow()==0){
		 Timestamp timehigh = TimestampConverter(sfields.getBydatehigh());
		 Timestamp timelow = TimestampConverter(sfields.getBydatelow());
		 List<Customer> searching = custservice.getCustomersbyDate(currentemployee.getID(), timehigh, timelow);
		 System.out.println(searching.size());
		 PaginationSearch(model, searching, sfields, nposition, 10, 3);
	 }
	 else {
		 List<Customer> searching = custservice.getCustomersbyNameAge(currentemployee.getID(), sfields.getByname(), sfields.getByagehigh(), sfields.getByagelow());
		 PaginationSearch(model, searching, sfields, nposition, 10, 4);
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
	 customer.setCurrentdate(CurrentDate());
	 System.out.printf("Has creado un customer en la hora actual de: %s", CurrentDate());
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
 
 public void Pagination(Model model, List<Customer> customers, int nposition, int numregist){
	 int n = customers.size();
	 int npages;
	 int ncustomers;
	 if(customers.size()==0)
		 ncustomers=0;
	 else
		 ncustomers = customers.size();
	 //Avoid blank pages
	 if(n%numregist==0)
		 npages = n/numregist;
	 else
		 npages = n/numregist+1;
	 model.addAttribute("firstpage", nposition);
	 model.addAttribute("npages", npages);
	 model.addAttribute("ncustomers",ncustomers);
	 if(customers.size()!=0){
	 List<Customer> listing = custservice.getCustomersbyLimit(currentemployee.getID(), numregist*(nposition-1), numregist);
	 model.addAttribute("listing",listing);
	 }
 }
 
 public void PaginationSearch(Model model, List<Customer> searching, SearchFields sfields, int nposition, int numregist, int type){
	 int n = searching.size();
	 int npages = (n/numregist)+1;
	 int ncustomers;
	 if(searching.size()==0)
		 ncustomers=0;
	 else
		 ncustomers = searching.size();
	 model.addAttribute("firstpage", nposition);
	 model.addAttribute("npages", npages);
	 model.addAttribute("ncustomers",ncustomers);
	 if(searching.size()!=0){
		 switch(type){
		 case 1:  List<Customer> listing = custservice.getCustomersbyNameLimit(currentemployee.getID(), sfields.getByname(), numregist*(nposition-1), numregist);
		 		  model.addAttribute("listing",listing); break;
		 case 2: List<Customer> listing2 = custservice.getCustomersbyAgeLimit(currentemployee.getID(), sfields.getByagehigh(), sfields.getByagelow(), numregist*(nposition-1), numregist);
		 		 model.addAttribute("listing",listing2); break;
		 case 3: Timestamp timehigh = TimestampConverter(sfields.getBydatehigh());
		 		 Timestamp timelow = TimestampConverter(sfields.getBydatelow());
			 	 List<Customer> listing3 = custservice.getCustomersbyDateLimit(currentemployee.getID(),timehigh, timelow, numregist*(nposition-1), numregist);
 		 		 model.addAttribute("listing",listing3); break;
		 case 4: List<Customer> listing4 = custservice.getCustomersbyNameAgeLimit(currentemployee.getID(), sfields.getByname(), sfields.getByagehigh(), sfields.getByagelow(), numregist*(nposition-1), numregist);
		 		 model.addAttribute("listing",listing4); break;	 
		 }
		 }
 }
 
 public Timestamp CurrentDate() {
	 Date today = new Date();
	 return new Timestamp(today.getTime());
 }
 
 public Timestamp TimestampConverter(Date date) {
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(date);
	 cal.set(Calendar.MILLISECOND,0);
	 return new Timestamp(cal.getTimeInMillis());
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