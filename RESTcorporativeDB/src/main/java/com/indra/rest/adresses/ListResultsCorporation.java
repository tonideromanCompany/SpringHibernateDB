package com.indra.rest.adresses;

import java.util.List;



public class ListResultsCorporation {

	private List<CorporationAdress> results;
	
	private int nresults;
	
	public ListResultsCorporation(List<CorporationAdress> results, int nresults) {
		this.results = results;
		this.nresults = nresults;
	}
	
	public List<CorporationAdress> getResults() {
		return results;
	}
	
	public int getNresults() {
		return nresults;
	}
}
