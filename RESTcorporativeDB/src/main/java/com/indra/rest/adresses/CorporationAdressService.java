package com.indra.rest.adresses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author arommartinez
 *
 */

@Service
public class CorporationAdressService {
	 
	 @Autowired
	 protected CorporationAdressRepository repository;
	 
	 public List<CorporationAdress> getCorporationAdresses() {
	 return repository.getCorporationAdresses();
	 }
	 
	 public List<CorporationAdress> getCorporationAdressesID(int id) {
		 return repository.getCorporationAdressesID(id);
		 }
	 
	 public List<CorporationAdress> getCorporationAdressesbyLimit(int first, int max) {
		 return repository.getCorporationAdressesbyLimit(first, max);
		 }
	 
	 public List<CorporationAdress> getCorporationAdressbyCorporation(String corporation) {
		 return repository.getCorporationAdressbyCorporation(corporation);
		 }
	 
	 public void createCorporationAdress(CorporationAdress corporationadress) {
	 repository.createCorporationAdress(corporationadress);
	 }
	 
	 public void updateCorporationAdress(CorporationAdress corporationadress) {
		 repository.updateCorporationAdress(corporationadress);
		 }
	 public void deleteCorporationAdress(CorporationAdress corporationadress) {
		 repository.deleteCorporationAdress(corporationadress);
		 }
	}