package com.indra.rest.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;

/**
 * 
 * @author arommartinez
 *
 */

@Service
public class EmployeeService {
 
 @Autowired
 protected EmployeeRepository repository;
 
 public void createEmployee(Employee employee) {
 repository.createEmployee(employee);
 }
 
 public void updateEmployee(Employee employee) {
	 repository.updateEmployee(employee);
 }
 
 public void deleteEmployee(Employee employee) {
	 repository.deleteEmployee(employee);
 }
 
 public List<Employee> getEmployees() {
 return repository.getEmployees();
 }
 
 public List<Employee> getEmployeesbyCorporation(String corporation) {
	 return repository.getEmployeesbyCorporation(corporation);
 }
 
 public List<Employee> getEmployeesbyCorporationbyID(String corporation, int id) {
	 return repository.getEmployeesbyCorporationbyID(corporation, id);
 }
 
 public List<Employee> getEmployeesbyLimit(int first, int max) {
	 return repository.getEmployeesbyLimit(first, max);
 }
 
 public List<Employee> getEmployeesbyCorporationbyLimit(String corporation, int first, int max) {
	 return repository.getEmployeesbyCorporationbyLimit(corporation, first, max);
 }
 
 public List<Employee> getEmployeesbyId(int id) {
	 return repository.getEmployeesbyId(id);
 }
 
 public List<Employee> getEmployeesLogin(String user) {
 return repository.getEmployeesLogin(user);
 }
 
 public List<Employee> getEmployeesName(int ID) {
	 return repository.getEmployeesName(ID);
	 }
}

/* public List<Employee> getEmployeesLogin(String corp) {
return repository.getEmployeesLogin(corp);
}*/