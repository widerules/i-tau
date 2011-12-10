package com.google.unizone.server;

public class university {
	
	private String name;
	private String country;
	
	public university(String name){
		this.name = name;
		this.country = "Israel";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
