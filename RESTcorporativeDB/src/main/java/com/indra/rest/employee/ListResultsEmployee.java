package com.indra.rest.employee;

import java.util.List;

public class ListResultsEmployee {

	private List<Employee> results;
	
	private int nresults;
	
	public ListResultsEmployee(List<Employee> results, int nresults) {
		this.results = results;
		this.nresults = nresults;
	}
	
	public List<Employee> getResults() {
		return results;
	}
	
	public int getNresults() {
		return nresults;
	}
	
}