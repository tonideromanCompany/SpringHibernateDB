package com.indra.app.customer;

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
public class CustomerRepository {
 
 @Autowired
 protected SessionFactory sessionFactory;
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomers() {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c")
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyID(int IDemployee) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID")
  .setInteger("ID", IDemployee)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyNameAge(int IDemployee, String byname, int byage) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND age=:A AND name LIKE :N")
  .setInteger("ID", IDemployee)
  .setInteger("A", byage)
  .setString("N", "%"+byname+"%")
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyName(int IDemployee, String byname) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND name LIKE :N")
  .setInteger("ID", IDemployee)
  .setString("N", "%"+byname+"%")
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyAge(int IDemployee, int byage) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND age=:A")
  .setInteger("ID", IDemployee)
  .setInteger("A", byage)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyLimit(int IDemployee, int first, int last) {
	 return sessionFactory.getCurrentSession()
		.createQuery("FROM Customer c WHERE idemployee=:ID")
		.setInteger("ID", IDemployee)
		.setFirstResult(first)
		.setMaxResults(last)
		.list();
 }
 
 public void createCustomer(Customer customer) {
 sessionFactory.getCurrentSession().save(customer);
 }
}