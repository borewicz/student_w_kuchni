package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Osoba{
	public Map<String, ArrayList<String>> cechy = new HashMap<String, ArrayList<String>>();
	
	public boolean czyArgumentJest(String nazwaCechy, String wartosc){
		for (Map.Entry<String, ArrayList<String>> entry : cechy.entrySet()){
		    if (entry.getKey().equals(nazwaCechy)){
		    	return czyListaZawiera(entry.getValue(), wartosc);
		    }
		}
		return false;
	}
	
	private boolean czyListaZawiera(ArrayList<String> list, String element){
		for (String x: list){
			if (x.equals(element))
				return true;
		}
		return false;
	}
	
	public boolean czyAtrybutJest(String nazwaCechy){
		for (Map.Entry<String, ArrayList<String>> entry : cechy.entrySet()){
			if (entry.getKey().equals(nazwaCechy)){
		    	return false;
		    }
		}
		return true;
	}
	
	public boolean czyListaJestPusta(ArrayList<String> list){
		return list.isEmpty();
	}
	
	
}
