package com.indra.app.adresses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author arommartinez
 *
 */

@Service
public class EmployeeAdressService {
	 
	 @Autowired
	 protected EmployeeAdressRepository repository;
	 
	 public List<EmployeeAdress> getEmployeeAdress() {
	 return repository.getEmployeeAdress();
	 }
	 
	 public List<EmployeeAdress> getEmplyoyeeAdressbyID(int IDemployee) {
		 return repository.getEmployeeAdressbyID(IDemployee);
		 }
	 
	 public void createEmployeeAdress(EmployeeAdress employeeadress) {
	 repository.createEmployeeAdress(employeeadress);
	 }
	 
	 public void updateEmployeeAdress(EmployeeAdress employeeadress) {
		 repository.updateEmployeeAdress(employeeadress);
		 }
	}
