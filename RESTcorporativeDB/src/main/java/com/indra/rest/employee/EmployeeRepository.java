package com.indra.rest.employee;

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
 
 public void createEmployee(Employee employee) {
 sessionFactory.getCurrentSession().save(employee);
 }
 
 public void updateEmployee(Employee employee) {
	 sessionFactory.getCurrentSession().saveOrUpdate(employee);
 }
 
 public void deleteEmployee(Employee employee) {
	 sessionFactory.getCurrentSession().delete(employee);
 }
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployees() {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Employee e")
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesbyCorporation(String corporation) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Employee e WHERE corporation=:C")
  .setString("C", corporation)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesbyCorporationbyID(String corporation, int id) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Employee e WHERE corporation=:C AND id=:ID")
  .setString("C", corporation)
  .setInteger("ID", id)
  .list();
 }
 
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesbyLimit(int first, int max) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Employee e")
  .setFirstResult(first)
  .setMaxResults(max)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesbyCorporationbyLimit(String corporation, int first, int max) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Employee e WHERE corporation=:C")
  .setString("C", corporation)
  .setFirstResult(first)
  .setMaxResults(max)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesbyId(int id) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Employee e WHERE id=:ID")
  .setInteger("ID", id)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesLogin(String user) {
 return sessionFactory.getCurrentSession()
 		 .createQuery("FROM Employee e WHERE user=:user" )
 		 .setString("user",user)
 		 .list();
 }
 

 @SuppressWarnings("unchecked")
 public List<Employee> getEmployeesName(int ID) {
 return sessionFactory.getCurrentSession()
 		 .createQuery("FROM Employee e WHERE id=:ID" )
 		 .setInteger("ID",ID)
 		 .list();
 }
}