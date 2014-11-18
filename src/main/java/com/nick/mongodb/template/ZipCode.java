package com.nick.mongodb.template;

import org.springframework.data.annotation.Id;

public class ZipCode {

	@Id
	private String zipCode;

	private String city;
	private String state;

	public ZipCode() {
	}

	public ZipCode(String zipCode, String city, String state) {
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format("Zipcode[zipCode=%s, city='%s', state='%s']",
				zipCode, city, state);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}