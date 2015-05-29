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
 
 public void createCustomer(Customer customer) {
 sessionFactory.getCurrentSession().save(customer);
 }
}