package com.indra.rest.customer;

import java.util.List;


public class ListResults {

	private List<Customer> results;
	
	private int nresults;
	
	public ListResults(List<Customer> results, int nresults) {
		this.results = results;
		this.nresults = nresults;
	}
	
	public List<Customer> getResults() {
		return results;
	}
	
	public int getNresults() {
		return nresults;
	}
	
}
