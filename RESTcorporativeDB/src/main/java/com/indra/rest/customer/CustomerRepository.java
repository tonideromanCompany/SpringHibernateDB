package com.indra.rest.customer;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
import java.sql.Timestamp;
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
 
 public void createCustomer(Customer customer) {
 sessionFactory.getCurrentSession().save(customer);
 }
 
 public void updateCustomer(Customer customer) {
	 sessionFactory.getCurrentSession().saveOrUpdate(customer);
 }
 
 public void deleteCustomer(Customer customer) {
	 sessionFactory.getCurrentSession().delete(customer);
 }
 
 //QUERY LIST FUNCTIONS
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomers() {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c")
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyLimit(int first, int last) {
	 return sessionFactory.getCurrentSession()
		.createQuery("FROM Customer c")
		.setFirstResult(first)
		.setMaxResults(last)
		.list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyIDcustomer(int IDcustomer) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE id=:ID")
  .setInteger("ID", IDcustomer)
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
 public List<Customer> getCustomerbyIDandIDcustomer(int IDemployee, int IDcustomer) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND id=:IDc")
  .setInteger("ID", IDemployee)
  .setInteger("IDc", IDcustomer)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomerbyIDandIDcustomerbyLimit(int IDemployee, int IDcustomer, int first, int max) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND id=:IDc")
  .setInteger("ID", IDemployee)
  .setInteger("IDc", IDcustomer)
  .setFirstResult(first)
  .setMaxResults(max)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyNameAge(int IDemployee, String byname, int byagehigh, int byagelow) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND age BETWEEN :L AND :H AND name LIKE :N")
  .setInteger("ID", IDemployee)
  .setInteger("H", byagehigh)
  .setInteger("L", byagelow)
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
 public List<Customer> getCustomersbyAge(int IDemployee, int byagehigh, int byagelow) {
// System.out.printf("ID: %s High: %s Low: %s",IDemployee, byagehigh, byagelow);
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:id AND age BETWEEN :L AND :H")
  .setParameter("id", IDemployee)
  .setParameter("L", byagelow)
  .setParameter("H", byagehigh)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyDate(int IDemployee, Timestamp bydatehigh, Timestamp bydatelow) {
// System.out.printf("ID: %s High: %s Low: %s",IDemployee, byagehigh, byagelow);
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:id AND currentdate BETWEEN :L AND :H")
  .setParameter("id", IDemployee)
  .setParameter("L", bydatelow)
  .setParameter("H", bydatehigh)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyNameAgeLimit(int IDemployee, String byname, int byagehigh, int byagelow, int first, int last) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND age BETWEEN :L AND :H AND name LIKE :N")
  .setInteger("ID", IDemployee)
  .setInteger("H", byagehigh)
  .setInteger("L", byagelow)
  .setString("N", "%"+byname+"%")
  .setFirstResult(first)
  .setMaxResults(last)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyNameLimit(int IDemployee, String byname, int first, int last) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:ID AND name LIKE :N")
  .setInteger("ID", IDemployee)
  .setString("N", "%"+byname+"%")
  .setFirstResult(first)
  .setMaxResults(last)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyAgeLimit(int IDemployee, int byagehigh, int byagelow, int first, int last) {
// System.out.printf("ID: %s High: %s Low: %s",IDemployee, byagehigh, byagelow);
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:id AND age BETWEEN :L AND :H")
  .setParameter("id", IDemployee)
  .setParameter("L", byagelow)
  .setParameter("H", byagehigh)
  .setFirstResult(first)
  .setMaxResults(last)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyLimitID(int IDemployee, int first, int last) {
	 return sessionFactory.getCurrentSession()
		.createQuery("FROM Customer c WHERE idemployee=:ID")
		.setInteger("ID", IDemployee)
		.setFirstResult(first)
		.setMaxResults(last)
		.list();
 }
 
 @SuppressWarnings("unchecked")
 public List<Customer> getCustomersbyDateLimit(int IDemployee, Timestamp bydatehigh, Timestamp bydatelow, int first, int last) {
// System.out.printf("ID: %s High: %s Low: %s",IDemployee, byagehigh, byagelow);
 return sessionFactory.getCurrentSession()
  .createQuery("FROM Customer c WHERE idemployee=:id AND currentdate BETWEEN :L AND :H")
  .setParameter("id", IDemployee)
  .setParameter("L", bydatelow)
  .setParameter("H", bydatehigh)
  .setFirstResult(first)
  .setMaxResults(last)
  .list();
 }
}