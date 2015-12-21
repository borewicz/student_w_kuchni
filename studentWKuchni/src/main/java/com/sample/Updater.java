package com.sample;

import java.util.concurrent.Semaphore;
import java.util.*;


public class Updater {

	public static Semaphore dataAvailable = new Semaphore(0);

	private String attribute;
	private int numberOfAnswers;
	public Dish d;

	public Updater(Dish d, String attribute, String[] formAnswers) {

		this.numberOfAnswers = formAnswers.length;
		this.d = d;
		this.attribute = attribute;
		update(formAnswers);
	}

	public void update(String[] formAnswers) {
		HashSet<String> attributesSet = new HashSet<String>();
		for (int i = 0; i < this.numberOfAnswers; i++) {
			attributesSet.add(formAnswers[i]);
		}
		d.attributes.put(this.getattribute(), attributesSet);
		dataAvailable.release();
	}

	public String getattribute() {
		return attribute;
	}

	public void setattribute(String attribute) {
		this.attribute = attribute;
	}

}