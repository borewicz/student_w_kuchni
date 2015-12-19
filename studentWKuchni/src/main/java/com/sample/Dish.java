package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Dish{
	public Map<String, ArrayList<String>> attributes = new HashMap<String, ArrayList<String>>();
	
	public boolean czyArgumentJest(String attributeName, String value){
		for (Map.Entry<String, ArrayList<String>> entry : attributes.entrySet()){
		    if (entry.getKey().equals(attributeName)){
		    	return listContain(entry.getValue(), value);
		    }
		}
		return false;
	}
	
	private boolean listContain(ArrayList<String> list, String element){
		for (String x: list){
			if (x.equals(element))
				return true;
		}
		return false;
	}
	
	public boolean attributeExist(String attributeName){
		for (Map.Entry<String, ArrayList<String>> entry : attributes.entrySet()){
			if (entry.getKey().equals(attributeName)){
		    	return false;
		    }
		}
		return true;
	}
	
	public boolean listEmpty(ArrayList<String> list){
		return list.isEmpty();
	}
	
	
}
