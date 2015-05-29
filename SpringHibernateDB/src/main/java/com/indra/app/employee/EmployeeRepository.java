package com.indra.app.employee;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 


import java.util.List;

/**
 * 
 * @author arommartinez
 *
 */

@Repository
@Transactional
public class EmployeeRepository {
 
 @Autowired
 protected SessionFactory sessionFactory;
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployees() {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Employee e")
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesLogin(String corp, String user) {
 return sessionFactory.getCurrentSession()
 		 .createQuery("FROM Employee e WHERE corporation=:corporation AND user=:user" )
 		 .setString("corporation",corp)
 		 .setString("user",user)
 		 //.setString("password",pass)
 		 .list();
 }
 

 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesName(int ID) {
 return sessionFactory.getCurrentSession()
 		 .createQuery("FROM Employee e WHERE id=:ID" )
 		 .setInteger("ID",ID)
 		 .list();
 }
 
 public void createEmployee(Employee employee) {
 sessionFactory.getCurrentSession().save(employee);
 }
}