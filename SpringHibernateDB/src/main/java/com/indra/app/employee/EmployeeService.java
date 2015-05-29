package com.indra.app.employee;

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
 
 public List<Employee> getEmployees() {
 return repository.getEmployees();
 }
 
 public List<Employee> getEmployeesLogin(String corp, String user) {
 return repository.getEmployeesLogin(corp, user);
 }
 
 public List<Employee> getEmployeesName(int ID) {
	 return repository.getEmployeesName(ID);
	 }
 
 public void createEmployee(Employee employee) {
 repository.createEmployee(employee);
 }
}

/* public List<Employee> getEmployeesLogin(String corp) {
return repository.getEmployeesLogin(corp);
}*/