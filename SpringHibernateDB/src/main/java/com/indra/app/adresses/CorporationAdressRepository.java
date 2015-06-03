package com.indra.app.adresses;

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
 public List<CorporationAdress> getCorporationAdressbyCorporation(String corporation) {
 return sessionFactory.getCurrentSession()
  .createQuery("FROM CorporationAdress ca WHERE corporation=:corporation")
  .setString("corporation", corporation)
  .list();
 }
 
 public void createCorporationAdress(CorporationAdress corporationadress) {
 sessionFactory.getCurrentSession().save(corporationadress);
 }
}
