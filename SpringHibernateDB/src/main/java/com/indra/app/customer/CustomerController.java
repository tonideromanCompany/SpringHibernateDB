package com.indra.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.indra.app.employee.*;
import com.indra.app.adresses.*;

/**
 * 
 * @author arommartinez
 *
 */

@Controller
public class CustomerController {

Employee currentemployee;
 EmployeeAdress currentemployeeadress;
 CorporationAdress currentcorporationadress;

 MessageDigest md;
 byte[] bytesOfPass;
 int nposition, initialpage, nregistros;

 
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
 //Default Values
 setDBValues();
 nposition = 1; 
 initialpage = 1;
 nregistros=12;
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
 public String getCustomers(Model model) {
 model.addAttribute("currentemployee", currentemployee);
 List<Customer> customers = custservice.getCustomersbyID(currentemployee.getID());
 //AQUI EMPIEZA LA FUNCION DE PAGINACION
 int n = customers.size();
 int npages = (n/nregistros)+1;
 model.addAttribute("firstpage", nposition);
 model.addAttribute("secondpage", nposition+1);
 model.addAttribute("thirdpage", nposition+2);
 //model.addAttribute("nposition",nposition);
 switch(nposition){
 case 1: model.addAttribute("state1","active"); model.addAttribute("state2","inactive"); model.addAttribute("state3","inactive"); break;
 case 2: model.addAttribute("state1", "inactive"); model.addAttribute("state2","active"); model.addAttribute("state3","inactive"); break;
 case 3: model.addAttribute("state1", "inactive"); model.addAttribute("state2","inactive"); model.addAttribute("state3","active"); break;
 default: model.addAttribute("state1","inactive"); model.addAttribute("state2","inactive"); model.addAttribute("state3","inactive"); break;
 }
 model.addAttribute("npages", npages);
 if(customers.size()!=0){
 List<Customer> listing = custservice.getCustomersbyLimit(currentemployee.getID(), nregistros*(nposition-1), nregistros*nposition);
 model.addAttribute("listing",listing);
 }
//FIN FUNCION DE PAGINACION
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
	 defaultCreation(customer);
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
 
 /*public List<Customer> Pagination(List<Customer> customers, Model model, int nregistros){
	 List<Customer> sendinglist;
	 int n = customers.size();
	 int npages = (n/nregistros);
	 
	 //model.addAttribute("message", "numero de paginas = "+npages);
	 model.addAttribute("firstpage", nposition);
	 model.addAttribute("secondpage", nposition+1);
	 model.addAttribute("thirdpage", nposition+2);
	 //model.addAttribute("nposition",nposition);
	 switch(nposition){
	 case 1: model.addAttribute("state1","active"); model.addAttribute("state2","inactive"); model.addAttribute("state3","inactive");
	 case 2: model.addAttribute("state1", "inactive"); model.addAttribute("state2","active"); model.addAttribute("state3","inactive");
	 case 3: model.addAttribute("state1", "inactive"); model.addAttribute("state2","inactive"); model.addAttribute("state3","active");
	 default: model.addAttribute("state1","inactive"); model.addAttribute("state2","inactive"); model.addAttribute("state3","inactive");
	 }

	/* if(nposition==npages){
		 model.addAttribute("firstpage", nposition-2);
		 model.addAttribute("secondpage", nposition-1);
		 model.addAttribute("thirdpage", nposition);
	 }
	 else if(nposition==npages-1){
		 model.addAttribute("firstpage", nposition-1);
		 model.addAttribute("secondpage", nposition);
		 model.addAttribute("thirdpage", nposition+1);
	 }
	 else {
		 model.addAttribute("firstpage", nposition);
		 model.addAttribute("secondpage", nposition+1);
		 model.addAttribute("thirdpage", nposition+2);
	 }
	 for (int i = nregistros*(nposition-1); i<nregistros*nposition;i++) {
		 sendinglist.add(customers.get(i));
	 }
	 
	 return sendinglist;
	 /*
	  * Pagination is in groups of three, we need to contemplate what happen when the user arrive at last of each three, 
	  * then we'll go to next group
	  * npages/3=nthreesome; --> nthreesome indicates in which group of three we are.
	  * Look up where we are
	  * if(ntrio!=1)
	  * 	model.addAttribute("firtspag",nthreesome);
	  * 	model.addAttribute("secondpag",nthreesome++);
	  * 	model.addAttribute("thirdpag",nthreesome++);
	  * 	nthreesome, nthreesome++, nthreesome++ --> we'll be the pages
	  * else { --> Only in the first group, the start is in the position(nthreesome) of the group, 
	  * 	   --> after we always start in position(nthreesome)+1
	  * 	model.addAttribute("firtspag",nthreesome);
	  * 	model.addAttribute("secondpag",nthreesome++);
	  * 	model.addAttribute("thirdpag",nthreesome++);
	  * 	nthreesome++, nthreesome++, nthreesome++ --> we'll be the pages
	  * }
	  * if(recibo n) {
	  * 	Muestrame desde 14*(n-1) hasta 14*n
	  * 	i=n;
	  * 	case i = active;
	  * 	resto = inactives;
	  * }
	  * if(recibo forward) {
	  * 	Miro el valor de i, le hago ++
	  * 	i=n;
	  * 	Muestrame desde 14*(n-1) hasta 14*n
	  * 	Case i = active;
	  * }
	  * if(recibo backward) {
	  * 	Miro el valor de i, le hago --
	  *	 	i=n;
	  * 	Muestrame desde 14*(n-1) hasta 14*n
	  * 	Case i = active;
	  * }
	  
 }*/
 
 //PREDETERMINATE DB VALUES
 public void setDBValues() throws NoSuchAlgorithmException, UnsupportedEncodingException {
	 List<CorporationAdress> CorpAdList = corpservice.getCorporationAdressbyCorporation("Indra Software Labs S.L.");
		if(CorpAdList.size()==1){
		}
		else{
			//First Employee
	 Employee employee = new Employee();
	 employee.setCorporation("Indra Software Labs S.L.");
	 employee.setUser("antonio@indra.es");
	 employee.setName("Antonio");
	 employee.setSurname("De Roman Martinez");
	 employee.setPassword(ReturnedHash("antonio"));
	 employee.setPassword2(ReturnedHash("antonio"));
	 employee.setPhone("685 789 458");
	 employee.setRole("Developer");
	 empservice.createEmployee(employee);
	 
		    //Second Employee
	 Employee employee2 = new Employee();
	 employee2.setCorporation("Repsol S.A.");
	 employee2.setUser("alvaro@repsol.es");
	 employee2.setName("Alvaro");
	 employee2.setSurname("Garcia Errejon");
	 employee2.setPassword(ReturnedHash("alvaro"));
	 employee2.setPassword2(ReturnedHash("alvaro"));
	 employee2.setPhone("654 079 564");
	 employee2.setRole("Developer Pro");
	 empservice.createEmployee(employee2);
	 
	 	   //Third Employee
	 Employee employee3 = new Employee();
	 employee3.setCorporation("Deloitte S.L.");
	 employee3.setUser("vicente@deloitte.es");
	 employee3.setName("Vicente");
	 employee3.setSurname("Cervera Sanchez");
	 employee3.setPassword(ReturnedHash("vicente"));
	 employee3.setPassword2(ReturnedHash("vicente"));
	 employee3.setPhone("678 459 248");
	 employee3.setRole("Executive");
	 empservice.createEmployee(employee3);
			
			//Indra Lleida
	 CorporationAdress corpad = new CorporationAdress();
	 corpad.setCorporation("Indra Software Labs S.L.");
	 corpad.setOffice("Lleida-Parque Gardeny");
	 corpad.setDepartment("Software Labs");
	 corpad.setAdress("Parque de Gardeny, Edifio 28");
	 corpad.setCity("Lleida");
	 corpad.setPostalcode("25071");
	 corpad.setCountry("Spain");
	 corpservice.createCorporationAdress(corpad);
	 		//Repsol Madrid
	 CorporationAdress corpad2 = new CorporationAdress();
	 corpad2.setCorporation("Repsol S.A.");
	 corpad2.setOffice("Campus Repsol");
	 corpad2.setDepartment("Informatica Aplicada");
	 corpad2.setAdress("Calle de Mendez Alvaro, 44");
	 corpad2.setCity("Madrid");
	 corpad2.setPostalcode("28045");
	 corpad2.setCountry("Spain");
	 corpservice.createCorporationAdress(corpad2);
	 
	 		//Deloitte Barcelona
	 CorporationAdress corpad3 = new CorporationAdress();
	 corpad3.setCorporation("Deloitte S.L.");
	 corpad3.setOffice("Barcelona-Parque Empresarial");
	 corpad3.setDepartment("Consulting Labs");
	 corpad3.setAdress("Parque Empresarial Metrovacesa 22, Edificio D, 3ª planta");
	 corpad3.setCity("Barcelona");
	 corpad3.setPostalcode("08034");
	 corpad3.setCountry("Spain");
	 corpservice.createCorporationAdress(corpad3);
		}
 }
 
 public void defaultCreation(Customer customer){
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
	 custservice.createCustomer(customer);
 }
}