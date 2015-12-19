package com.sample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dish {
	public Map<String, HashSet<String>> attributes = new HashMap<String, HashSet<String>>();

	public boolean attributeContain(String attributeName, String value) {
		if (attributes.containsKey(attributeName)) {
			if (attributes.get(attributeName).contains(value)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean attributeExist(String attributeName) {
		if (attributes.containsKey(attributeName)) {
			return true;
		} else {
			return false;
		}
	}

}
