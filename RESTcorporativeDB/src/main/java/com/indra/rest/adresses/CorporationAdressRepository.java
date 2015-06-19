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
public class CorporationAdressRepository {
 
 @Autowired
 protected SessionFactory sessionFactory;
 
 @SuppressWarnings("unchecked")
 public List<CorporationAdress> getCorporationAdresses() {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM CorporationAdress ca")
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<CorporationAdress> getCorporationAdressesID(int id) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM CorporationAdress ca WHERE id=:ID")
  .setInteger("ID", id)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<CorporationAdress> getCorporationAdressesbyLimit(int first, int max) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM CorporationAdress ca")
  .setFirstResult(first)
  .setMaxResults(max)
  .list();
 }
 
 @SuppressWarnings("unchecked")
 public List<CorporationAdress> getCorporationAdressbyCorporation(String corporation) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM CorporationAdress ca WHERE corporation=:corporation")
  .setString("corporation", corporation)
  .list();
 }
 
 public void createCorporationAdress(CorporationAdress corporationadress) {
 sessionFactory.getCurrentSession().save(corporationadress);
 }
 
 public void updateCorporationAdress(CorporationAdress corporationadress) {
 sessionFactory.getCurrentSession().saveOrUpdate(corporationadress);
 }
 
 public void deleteCorporationAdress(CorporationAdress corporationadress) {
 sessionFactory.getCurrentSession().delete(corporationadress);
 }
}
