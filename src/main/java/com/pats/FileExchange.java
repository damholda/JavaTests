package com.pats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileExchange {
	
	private String exchangeName;
	private Map<String, List<String>> productClasses;
	
	public FileExchange(String exchange) {
		this.exchangeName = exchange;
	}
	
	public String getName() {
		return exchangeName;
	}

	public Map<String, List<String>> getProductClasses(){
		if (productClasses==null){
			productClasses = new HashMap<String, List<String>>();
		}
		return productClasses;
	}
	
	public void addProductClass(String productClass, String...tags) {
		List<String> newTags = new ArrayList<String>();
		if (tags != null){
			newTags = Arrays.asList(tags);
		}
		if (getProductClasses().get(productClass)!=null){
			List<String> oldTags = getProductClasses().get(productClass);
			newTags.addAll(oldTags);
		}
		getProductClasses().put(productClass, newTags);
	}
	
	public List<String> getTags(String productClass){
		return getProductClasses().get(productClass);
	}
	
	@Override
	public String toString() {
		return getName()+" "+getProductClasses();
	}

}
