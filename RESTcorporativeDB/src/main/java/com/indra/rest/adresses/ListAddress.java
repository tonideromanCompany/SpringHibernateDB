package com.indra.rest.adresses;

import java.util.List;

public class ListAddress {
	
	private List<EmployeeAdress> results;
	
	private int nresults;
	
	public ListAddress(List<EmployeeAdress> results, int nresults) {
		this.results = results;
		this.nresults = nresults;
	}
	
	public List<EmployeeAdress> getResults() {
		return results;
	}
	
	public int getNresults() {
		return nresults;
	}
}
