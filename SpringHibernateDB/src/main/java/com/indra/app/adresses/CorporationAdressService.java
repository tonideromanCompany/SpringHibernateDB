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
public class CorporationAdressService {
	 
	 @Autowired
	 protected CorporationAdressRepository repository;
	 
	 public List<CorporationAdress> getCorporationAdresses() {
	 return repository.getCorporationAdresses();
	 }
	 
	 public List<CorporationAdress> getCorporationAdressbyCorporation(String corporation) {
		 return repository.getCorporationAdressbyCorporation(corporation);
		 }
	 
	 public void createCorporationAdress(CorporationAdress corporationadress) {
	 repository.createCorporationAdress(corporationadress);
	 }
	}