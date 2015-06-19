package com.indra.rest.adresses;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author arommartinez
 *
 */

@Repository
@Transactional
public class EmployeeAdressRepository {
	 
	 @Autowired
	 protected SessionFactory sessionFactory;
	 
	 @SuppressWarnings("unchecked")
	 public List<EmployeeAdress> getEmployeeAdress() {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM EmployeeAdress ea")
	  .list();
	 }
	 
	 @SuppressWarnings("unchecked")
	 public List<EmployeeAdress> getEmployeeAdressbyID(int IDemployee) {
	 return sessionFactory.getCurrentSession()
	  .createQuery("FROM EmployeeAdress ea WHERE idemployee=:ID")
	  .setInteger("ID", IDemployee)
	  .list();
	 }
	 
	 public void createEmployeeAdress(EmployeeAdress employeeadress) {
	 sessionFactory.getCurrentSession().save(employeeadress);
	 }
	 
	 public void updateEmployeeAdress(EmployeeAdress employeeadress) {
		 sessionFactory.getCurrentSession().saveOrUpdate(employeeadress);
		 }
	 public void deleteEmployeeAdress(EmployeeAdress employeeadress) {
		 sessionFactory.getCurrentSession().delete(employeeadress);
		 }
	}