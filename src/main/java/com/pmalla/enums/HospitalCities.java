package com.pmalla.enums;

/**
 * This is an enum to define city constants within the system.
 * 
 * @author Praveen Malla
 *
 */
public enum HospitalCities {
	SFO("San Francisco"), SJC("San Jose");

	private String name;

	HospitalCities(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
